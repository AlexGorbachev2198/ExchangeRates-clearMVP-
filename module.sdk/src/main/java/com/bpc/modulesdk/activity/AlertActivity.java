package com.bpc.modulesdk.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.bpc.modulesdk.net.ssl.CertificateUnknownHandler;

import ru.bpc.mobilebanksdk.R;

public class AlertActivity extends Activity {

    private static final String EXTRA_KEY_TYPE = "type";
    private static final int TYPE_DEFAULT = 0;
    private static final int TYPE_UNKNOWN_CERTIFICATE = 1;

    public static void startForUnknownCertificate(Context context) {
        Intent starter = new Intent(context, AlertActivity.class);
        starter.putExtra(EXTRA_KEY_TYPE, TYPE_UNKNOWN_CERTIFICATE);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        if (getIntent().getIntExtra(EXTRA_KEY_TYPE, TYPE_DEFAULT) == TYPE_UNKNOWN_CERTIFICATE) {
            UnknownCertificateDialog dialog;
            dialog = new UnknownCertificateDialog();
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                }
            });
            dialog.setCancelable(false);
            dialog.show(this.getFragmentManager(), null);
        }
    }

    @Override
    public void onBackPressed() {
    }

    public static class UnknownCertificateDialog extends DialogFragment {

        private DialogInterface.OnDismissListener listener;

        public void setOnDismissListener(DialogInterface.OnDismissListener listener) {
            this.listener = listener;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setCancelable(false);
            builder.setTitle(R.string.unknown_server_certificate);
            builder.setMessage(R.string.unknown_server_certificate_description);
            builder.setPositiveButton(R.string.ok, (dialog, which) -> {
                CertificateUnknownHandler.handler.addToWhiteList(CertificateUnknownHandler.encoded);
                CertificateUnknownHandler.activeDialog.set(false);
                if (listener != null)
                    listener.onDismiss(dialog);
            });
            builder.setNegativeButton(R.string.cancel, (dialog, which) -> {
                CertificateUnknownHandler.handler.addToBlackList(CertificateUnknownHandler.encoded);
                CertificateUnknownHandler.activeDialog.set(false);
                if (listener != null)
                    listener.onDismiss(dialog);
            });
            return builder.create();
        }
    }
}