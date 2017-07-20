package com.bpc.modulesdk.net.ssl;

public interface CertificateACLHandler {

    void addToBlackList(String encoded);

    void addToWhiteList(String encoded);

    void removeToWhiteList();

}