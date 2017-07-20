package com.bpc.modulesdk.net;

import com.bpc.modulesdk.net.ssl.TrustManagerHolder;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;

public class OkHttpClientBuilder {

    private static OkHttpClient sClient = null;

    public static OkHttpClient build() {


        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("https://10.7.34.63:3443/mobilebank/service/", "sha256/Bn9xNG2srEg4PFYTmq/SKa+6iYjAOe1qEw+nyYE/rTA=")
                .build();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder = setSsl(builder)
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .cookieJar(CookieHolder.get().getCookieJar())
                .certificatePinner(certificatePinner);


        StampSignInterceptor stampInterceptor = new StampSignInterceptor();
        builder.addInterceptor(stampInterceptor);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }


    private static OkHttpClient.Builder setSsl(OkHttpClient.Builder builder) {
        SSLContext sslcontext;
        try {
            sslcontext = SSLContext.getInstance("TLSv1.2");
            TrustManager trustManager = TrustManagerHolder.get().getTrustManager();
            sslcontext.init(null, new TrustManager[]{trustManager}, null);
            builder.sslSocketFactory(sslcontext.getSocketFactory());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        builder.followSslRedirects(true)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });

        return builder;
    }

    public static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (OkHttpClientBuilder.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = build();
                }
            }
        }
        return client;
    }


}