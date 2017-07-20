package com.bpc.modulesdk.net.ssl;

import com.bpc.modulesdk.BaseApp;
import com.bpc.modulesdk.activity.AlertActivity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.net.ssl.SSLHandshakeException;

import rx.Observable;
import rx.functions.Func1;


/**
 * Created by Masloed on 29.11.2016.
 */

public class CertificateUnknownHandler {

    private static String TAG = "CertificateUnknownHandler";

    public static AtomicBoolean activeDialog = new AtomicBoolean(false);
    public static CertificateACLHandler handler;
    public static String encoded;

    public static final Func1 funcCertificateDialog = new Func1<Observable<? extends Throwable>, Observable<?>>() {
        @Override
        public Observable<?> call(Observable<? extends Throwable> o) {
            return o.flatMap(new Func1<Throwable, Observable<?>>() {
                @Override
                public Observable<?> call(Throwable t) {
                    if (t instanceof SSLHandshakeException) {
                        if (!CertificateUnknownHandler.activeDialog.get())
                            CertificateUnknownHandler.processException((SSLHandshakeException) t);
                        return Observable.timer(5, TimeUnit.SECONDS);
                    }
                    // For anything else, don't retry
                    return Observable.error(t);
                }
            });
        }
    };


    public static boolean processException(SSLHandshakeException t) {
        Throwable c = t.getCause();
        if (c instanceof CertificateUnknownException) {
            CertificateUnknownException certEx = (CertificateUnknownException) c;
            showUnknownCertDialog(certEx.getHandler(), certEx.getEncoded());
            return true;
        }

        return false;
    }

    private static void showUnknownCertDialog(CertificateACLHandler handler, String encoded) {
        activeDialog.set(true);
        CertificateUnknownHandler.handler = handler;
        CertificateUnknownHandler.encoded = encoded;
        AlertActivity.startForUnknownCertificate(BaseApp.getContext());
    }

}
