package com.bpc.modulesdk.net.ssl;

import java.security.KeyStore;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class ACLTrustManager implements X509TrustManager, CertificateACLHandler {

    private ArrayList<X509TrustManager> x509TrustManagers = new ArrayList<>();
    private Map<String, Long> blackList = new HashMap<>();
    private Map<String, Long> whiteList = new HashMap<>();
    private long whitelistTimeoutMs = 180000L;
    private long blacklistTimeoutMs = 2000L;

    private List<KeyStore> additionalkeyStores = new ArrayList<>();

    public ACLTrustManager() {
        init();

    }



    public ACLTrustManager(KeyStore... additionalkeyStores) {
        this.additionalkeyStores= Arrays.asList(additionalkeyStores);
        init();
    }

    private void init() {
        final ArrayList<TrustManagerFactory> factories = new ArrayList<>();

        try {
            // The default Trustmanager with default keystore
            final TrustManagerFactory original = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            original.init((KeyStore) null);
            factories.add(original);

            if (additionalkeyStores != null) for (KeyStore keyStore : additionalkeyStores) {
                final TrustManagerFactory additionalCerts = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                additionalCerts.init(keyStore);
                factories.add(additionalCerts);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (TrustManagerFactory tmf : factories) {
            for (TrustManager tm : tmf.getTrustManagers()) {
                if (tm instanceof X509TrustManager) {
                    x509TrustManagers.add((X509TrustManager) tm);
                }
            }
        }

        if (x509TrustManagers.size() == 0) {
            throw new RuntimeException("Couldn't find any X509TrustManagers");
        }
    }

    public void setWhitelistTimeoutMs(long whitelistTimeoutMs) {
        this.whitelistTimeoutMs = whitelistTimeoutMs;
    }

    public void setBlacklistTimeoutMs(long blacklistTimeoutMs) {
        this.blacklistTimeoutMs = blacklistTimeoutMs;
    }

    /*
    * Delegate to the default trust manager.
    */
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        final X509TrustManager defaultX509TrustManager = x509TrustManagers.get(0);
        defaultX509TrustManager.checkClientTrusted(chain, authType);
    }

    /*
     * Loop over the trustmanagers until we find one that accepts our server
     */
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        for (X509TrustManager tm : x509TrustManagers) {
            try {
                tm.checkServerTrusted(chain, authType);
                return;
            } catch (CertificateException e) {
                // ignore
            }
        }
        String encoded = new String(chain[0].getEncoded());
        if (isWhitelisted(encoded)) {
            return;
        }
        if (isBlacklisted(encoded)) {
            throw new CertificateException();
        }
        throw new CertificateUnknownException(encoded, this);
    }

    private boolean isWhitelisted(String encoded) throws CertificateEncodingException {
        return isInAcl(encoded, whiteList, whitelistTimeoutMs);
    }

    private boolean isBlacklisted(String encoded) throws CertificateEncodingException {
        return isInAcl(encoded, blackList, blacklistTimeoutMs);
    }

    private boolean isInAcl(String encoded, Map<String, Long> acl, long timeout) {
        Long time = acl.get(encoded);
        if (time == null) {
            return false;
        }
        if (System.currentTimeMillis() - time > timeout) {
            acl.remove(encoded);
            return false;
        }
        return true;
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        final ArrayList<X509Certificate> list = new ArrayList<>();
        for (X509TrustManager tm : x509TrustManagers)
            list.addAll(Arrays.asList(tm.getAcceptedIssuers()));
        return list.toArray(new X509Certificate[list.size()]);
    }

    @Override
    public void addToBlackList(String encoded) {
        blackList.put(encoded, System.currentTimeMillis());
    }

    @Override
    public void addToWhiteList(String encoded) {
        whiteList.put(encoded, System.currentTimeMillis());
    }

    @Override
    public void removeToWhiteList() {
        whiteList.clear();
    }
}