package com.bpc.modulesdk.security;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.security.auth.x500.X500Principal;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Samoylov on 02.06.2016.
 * <p/>
 * Class store key local and encrypt/decrypt by it.
 */
public class LocalEncryptor {

    private static final String TAG = "LocalEncryptor";

    private static final String ALIAS = "local_encryptor";
    private final char[] password = "sdklf7".toCharArray();

    private KeyStore.PrivateKeyEntry keyEntry;

    public LocalEncryptor(Context context) throws NoSuchAlgorithmException, CertificateException, UnrecoverableEntryException, KeyStoreException, IOException {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2)
            try {
                keyEntry = getKeyFromKeyStore(context);
            } catch (Exception e) {
                Log.e(TAG, "Cannot get key from key store", e);
                keyEntry = getKeyFromKeyStorePreJELLY_BEAN(context);
            }
        else
            keyEntry = getKeyFromKeyStorePreJELLY_BEAN(context);
    }

    /**
     * Get key pair for after Jelly Bean Android versions
     *
     * @param context - any context
     * @return key pair from Android KeyStore System
     * @throws KeyStoreException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws UnrecoverableEntryException
     * @throws NoSuchProviderException
     * @throws InvalidAlgorithmParameterException
     */
    private KeyStore.PrivateKeyEntry getKeyFromKeyStore(Context context) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableEntryException, NoSuchProviderException, InvalidAlgorithmParameterException {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(ALIAS, null);

        if (privateKeyEntry == null) {
            createNewKeyInKeyStore(context, ALIAS);
            keyStore.load(null);
            privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(ALIAS, null);
        }
        return privateKeyEntry;
    }

    /**
     * Generate new key pair and store it to Android KeyStore System
     *
     * @param context - any context
     * @param alias   alias name that used for creating key pair
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void createNewKeyInKeyStore(Context context, String alias) throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        end.add(Calendar.YEAR, 10);

        KeyPairGenerator generator;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            generator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore");
            KeyGenParameterSpec keyGenSpec = new KeyGenParameterSpec.Builder(alias, KeyProperties.PURPOSE_DECRYPT | KeyProperties.PURPOSE_ENCRYPT)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                    .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                    .setCertificateSubject(new X500Principal("CN=Sample Name, O=Android Authority"))
                    .setCertificateSerialNumber(BigInteger.ONE)
                    .setCertificateNotBefore(start.getTime())
                    .setCertificateNotAfter(end.getTime())
                    .build();
            generator.initialize(keyGenSpec);
        } else {
            generator = KeyPairGenerator.getInstance("RSA", "AndroidKeyStore");
            generator.initialize(new KeyPairGeneratorSpec.Builder(context)
                    .setAlias(alias)
                    .setSubject(new X500Principal("CN=Sample Name, O=Android Authority"))
                    .setSerialNumber(BigInteger.ONE)
                    .setStartDate(start.getTime())
                    .setEndDate(end.getTime())
                    .build());
        }
        generator.generateKeyPair();
    }

    /**
     * Get key pair for pre Jelly Bean Android versions
     *
     * @param context - any context
     * @return key pair from not secure storage
     * @throws KeyStoreException
     * @throws UnrecoverableEntryException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws CertificateException
     */
    private KeyStore.PrivateKeyEntry getKeyFromKeyStorePreJELLY_BEAN(Context context) throws KeyStoreException, UnrecoverableEntryException, NoSuchAlgorithmException, IOException, CertificateException {
        int bksVersion;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            bksVersion = R.raw.localencryptor; //The BKS file
        } else {
            bksVersion = R.raw.localencryptorv1; //The BKS (v-1) file
        }
        KeyStore keyStore = KeyStore.getInstance("BKS");
        InputStream in = context.getResources().openRawResource(bksVersion);
        keyStore.load(in, password);
        return (KeyStore.PrivateKeyEntry) keyStore.getEntry(ALIAS, new KeyStore.PasswordProtection(password));
    }

    /**
     * Encrypt {@code value} by local stored key
     *
     * @param value any string
     * @return encrypted value or null, if something went wrong
     */
    public
    @Nullable
    String encrypt(String value) {
        try {
            byte[] result = encrypt(value.getBytes("UTF-8"));
            if (result != null)
                return Base64.encodeToString(result, Base64.DEFAULT);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * Encrypt {@code value} by local stored key
     *
     * @param value any array
     * @return encrypted value or null, if something went wrong
     */
    public
    @Nullable
    byte[] encrypt(byte[] value) {
        try {
            Cipher input = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            RSAPublicKey publicKey = (RSAPublicKey) keyEntry.getCertificate().getPublicKey();
            input.init(Cipher.ENCRYPT_MODE, publicKey);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, input);
            cipherOutputStream.write(value);
            cipherOutputStream.close();

            return outputStream.toByteArray();
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * Decrypt {@code value} by local stored key
     *
     * @param value any string that encrypted by {@link LocalEncryptor#encrypt(String)}
     * @return decrypted value or null, if something went wrong
     */
    public
    @Nullable
    String decrypt(String value) {
        byte[] result = decrypt(Base64.decode(value, Base64.DEFAULT));
        try {
            if (result != null)
                return new String(result, 0, result.length, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * Decrypt {@code value} by local stored key
     *
     * @param value any byte array that encrypted by {@link LocalEncryptor#encrypt(byte[])}
     * @return decrypted value or null, if something went wrong
     */
    public
    @Nullable
    byte[] decrypt(byte[] value) {
        try {
            Cipher output = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            PrivateKey privateKey = keyEntry.getPrivateKey();
            output.init(Cipher.DECRYPT_MODE, privateKey);

            CipherInputStream cipherInputStream = new CipherInputStream(
                    new ByteArrayInputStream(value), output);
            ArrayList<Byte> values = new ArrayList<>();
            int nextByte;
            while ((nextByte = cipherInputStream.read()) != -1) {
                values.add((byte) nextByte);
            }

            byte[] bytes = new byte[values.size()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = values.get(i).byteValue();
            }

            return bytes;

        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return null;
    }

}