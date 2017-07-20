package com.bpc.modulesdk.net.ssl;

import java.security.cert.CertificateException;

public class CertificateUnknownException extends CertificateException {
	private CertificateACLHandler handler;
	private String encoded;

	public CertificateUnknownException(String encoded, CertificateACLHandler handler){
		this.encoded = encoded;
		this.handler = handler;
	}

	public CertificateACLHandler getHandler(){
		return handler;
	}

	public String getEncoded() {
		return encoded;
	}
}
