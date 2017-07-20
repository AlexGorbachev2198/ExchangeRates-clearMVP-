package com.bpc.modulesdk.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;

import com.bpc.modulesdk.fragment.CommonFragment;

import ru.bpc.mobilebanksdk.R;


/**
 * Created by masloed on 15.07.2015.
 */
public class DialogHelper {

    /**
     * Создаёт диалог с прогрессом, который нельзя закрыть по кнопке Назад.
     *
     * @param context - конетекст для инициализации;
     * @return
     */
    @Deprecated //FIXME Перестать использовать
    public static ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = getProgressDialog(context, null);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    /**
     * Создаёт диалог с прогрессом, который можно закрыть по кнопке Назад. При этом вызовется слушатель onCancelListener
     *
     * @param context          - конетекст для инициализации;
     * @param onCancelListener - слушатель, который будет вызван при отмене диалога.
     * @return
     */
    @Deprecated //FIXME Перестать использовать
    public static ProgressDialog getProgressDialog(Context context, DialogInterface.OnCancelListener onCancelListener) {
        return getProgressDialog(context, -1, onCancelListener);
    }

    /**
     * Создаёт диалог с прогрессом, который можно закрыть по кнопке Назад. При этом вызовется слушатель onCancelListener
     *
     * @param context          - конетекст для инициализации;
     * @param messageId        - текст сообщения;
     * @param onCancelListener - слушатель, который будет вызван при отмене диалога.
     * @return
     */
    @Deprecated //FIXME Перестать использовать
    public static ProgressDialog getProgressDialog(Context context, @StringRes int messageId, DialogInterface.OnCancelListener onCancelListener) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(messageId > 0 ? messageId : R.string.please_wait));
        progressDialog.setOnCancelListener(onCancelListener);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static void showInfoDialog(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    public static void showInfoDialog(Context context, @StringRes int titleResID, @StringRes int messageResID) {
        showInfoDialog(context, titleResID > 0 ? context.getString(titleResID) : null,
                context.getString(messageResID));
    }

    public static void showOkDialog(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        showOkDialog(context, title, message, true, listener);
    }

    public static void showNotOutsideCancellableOkDialog(Context context, String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, listener);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void showOkDialog(Context context,
                                    String title,
                                    String message,
                                    boolean cancelable,
                                    DialogInterface.OnClickListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.ok, listener);
        builder.setCancelable(cancelable);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showOkDialog(Context context,
                                    @StringRes int titleResID,
                                    @StringRes int messageResID,
                                    DialogInterface.OnClickListener listener) {

        showOkDialog(context, titleResID, messageResID, true, listener);
    }

    public static void showOkDialog(Context context,
                                    @StringRes int titleResID,
                                    @StringRes int messageResID,
                                    boolean cancelable,
                                    DialogInterface.OnClickListener listener) {

        showOkDialog(context,
                titleResID > 0 ? context.getString(titleResID) : null,
                messageResID > 0 ? context.getString(messageResID) : null,
                cancelable,
                listener);
    }

    public static void showYesNoDialog(Context context, String title, String message,
                                       DialogInterface.OnClickListener onYesListener, DialogInterface.OnClickListener onNoListener,
                                       DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.yes, onYesListener);
        builder.setNegativeButton(R.string.no, onNoListener);
        AlertDialog dialog = builder.create();
        if (onCancelListener != null) {
            dialog.setOnCancelListener(onCancelListener);
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();
    }

    public static void showNotClosableYesNoDialog(Context context, String title, String message,
                                       DialogInterface.OnClickListener onYesListener, DialogInterface.OnClickListener onNoListener,
                                       DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(R.string.yes, onYesListener);
        builder.setNegativeButton(R.string.no, onNoListener);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        if (onCancelListener != null) {
            dialog.setOnCancelListener(onCancelListener);
            dialog.setCanceledOnTouchOutside(false);
        }
        dialog.show();

    }

    public static void showYesNoDialog(Context context, @StringRes int titleResID, @StringRes int messageResID,
                                       DialogInterface.OnClickListener onYesListener, DialogInterface.OnClickListener onNoListener) {
        showYesNoDialog(context,
                titleResID > 0 ? context.getString(titleResID) : null,
                messageResID > 0 ? context.getString(messageResID) : null,
                onYesListener, onNoListener, null);
    }

    public static void showNotClosableYesNoDialog(Context context, @StringRes int titleResID, @StringRes int messageResID,
                                                  DialogInterface.OnClickListener onYesListener, DialogInterface.OnClickListener onNoListener) {
        showNotClosableYesNoDialog(context,
                titleResID > 0 ? context.getString(titleResID) : null,
                messageResID > 0 ? context.getString(messageResID) : null,
                onYesListener, onNoListener, null);
    }

    public static void showYesNoDialog(Context context, int titleResID, int messageResID,
                                       DialogInterface.OnClickListener onYesListener, DialogInterface.OnClickListener onNoListener,
                                       DialogInterface.OnCancelListener onCancelListener) {
        showYesNoDialog(context,
                titleResID > 0 ? context.getString(titleResID) : null,
                messageResID > 0 ? context.getString(messageResID) : null,
                onYesListener, onNoListener, onCancelListener);
    }

    public static void showCancelCustomDialog(Context context, @StringRes int titleResID, @StringRes int messageResID, @StringRes int customResID,
                                              DialogInterface.OnClickListener onCustomListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titleResID);
        builder.setMessage(messageResID);
        builder.setPositiveButton(customResID, onCustomListener);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    public static DialogInterface.OnCancelListener createFinishOnCancelListener(final Activity activity) {
        return new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (activity != null)
                    activity.finish();
            }
        };
    }

    public static DialogInterface.OnCancelListener createFinishOnCancelListener(final CommonFragment fragment) {
        return new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (fragment != null)
                    fragment.popBackStack();
            }
        };
    }
}
