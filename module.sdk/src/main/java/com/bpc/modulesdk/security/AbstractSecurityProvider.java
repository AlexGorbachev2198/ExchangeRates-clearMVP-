package com.bpc.modulesdk.security;

import android.support.annotation.Nullable;
import android.util.Log;

import com.bpc.modulesdk.BaseApp;


import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.UUID;


public abstract class AbstractSecurityProvider implements SecurityProvider {

    private static final int KEY_SIZE = 512;
    private static final String ALGORITHM = "RSA";
    private static final String PRIVATE_KEY_TAG = "private_key";
    private static final String PUBLIC_KEY_TAG = "public_key";
    private static final String DEVICE_ID_TAG = "device_id";
    private static final String DEVICE_ID_ENCRYPTED_TAG = "device_id_encrypted"; //Since 1.9 should store device ID here

    @Nullable
    private LocalEncryptor localEncryptor;
    private KeyPair keyPair;
    private String deviceId;

    protected AbstractSecurityProvider() {
        try {
            localEncryptor = new LocalEncryptor(BaseApp.getContext());
        } catch (Exception e) {
            Log.e("SecurityProvider", "Error while creating LocalEncryptor. Encryption unavailable!", e);
        }

        keyPair = readKeyPair();
        if (keyPair == null) {
            generateKeyPair();
        }
        deviceId = readDeviceId();
        if (deviceId == null) {
            generateDeviceId();
        }
    }

    protected abstract void writeBytes(String tag, @Nullable byte[] bytes);

    protected abstract byte[] readBytes(String tag);

    @Override
    public KeyPair getKeyPair() {
        return keyPair;
    }

    @Override
    public String getPublicKey() {
        //return keyPair.getPublic();
        byte[] key = getKeyPair().getPublic().getEncoded();
        byte[] keyShrinked = Arrays.copyOfRange(key, 20, key.length);
        return String.valueOf(Hex.encodeHex(keyShrinked));
    }

    @Override
    public PrivateKey getPrivateKey() {
        return keyPair.getPrivate();
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    private void generateKeyPair() {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
            generator.initialize(KEY_SIZE, new SecureRandom());
            keyPair = generator.generateKeyPair();
            writeKeyPair(keyPair);
        } catch (NoSuchAlgorithmException e) {
//			Log.e("", this.getClass().getName(), e);
            throw new RuntimeException(e);
        }
    }

    private void writeKeyPair(KeyPair pair) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pair.getPublic().getEncoded());
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(pair.getPrivate().getEncoded());
        writeBytes(PRIVATE_KEY_TAG, pkcs8EncodedKeySpec.getEncoded());
        writeBytes(PUBLIC_KEY_TAG, x509EncodedKeySpec.getEncoded());
    }

    private KeyPair readKeyPair() {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            byte[] encodedPublicKey = readBytes(PUBLIC_KEY_TAG);
            byte[] encodedPrivateKey = readBytes(PRIVATE_KEY_TAG);
            if (encodedPrivateKey.length == 0 || encodedPublicKey.length == 0) {
                return null;
            }
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(encodedPublicKey);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            return new KeyPair(publicKey, privateKey);
        } catch (GeneralSecurityException e) {
//			Log.e("", e.getLocalizedMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private String readDeviceId() {
        byte[] data = readBytes(DEVICE_ID_ENCRYPTED_TAG);
        if (data == null || data.length == 0) {
            //Try to get not encrypted deviceID
            byte[] dataOld = readBytes(DEVICE_ID_TAG);
            if (dataOld == null || dataOld.length == 0)
                return null;
            writeBytes(DEVICE_ID_TAG, null);
            String deviceId = new String(dataOld);
            writeDeviceId(deviceId);
            return deviceId;
        }

        byte[] deviceIdArray = data;
        if (localEncryptor != null)
            deviceIdArray = localEncryptor.decrypt(data);
        if (deviceIdArray == null)
            return null;
        String deviceId = new String(deviceIdArray);

        if (!isUUID(deviceId))
            return null;
        return deviceId;
    }

    private void writeDeviceId(String devId) {
        byte[] encryptedDeviceID = null;
        if (localEncryptor != null)
            encryptedDeviceID = localEncryptor.encrypt(devId.getBytes());

        if (encryptedDeviceID == null)
            writeBytes(DEVICE_ID_TAG, devId.getBytes());
        else
            writeBytes(DEVICE_ID_ENCRYPTED_TAG, encryptedDeviceID);
    }

    private void generateDeviceId() {
        String deviceId = UUID.randomUUID().toString();
        this.deviceId = deviceId;
        writeDeviceId(deviceId);
    }

    private boolean isUUID(String UUIDString) {
        try {
            UUID.fromString(UUIDString);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    @Override
    public String generateStamp(byte[] message) {
        try {
            Signature sig = Signature.getInstance("SHA1withRSA");
            sig.initSign(getPrivateKey());
            sig.update(message);
            byte[] signature = sig.sign();
            return String.valueOf(Hex.encodeHex(signature));
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }



}