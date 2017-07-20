package com.bpc.modulesdk.security;

import java.security.KeyPair;
import java.security.PrivateKey;

public interface SecurityProvider {

    KeyPair getKeyPair();

    String getPublicKey();

    PrivateKey getPrivateKey();

    String getDeviceId();

    String generateStamp(byte[] message);
}