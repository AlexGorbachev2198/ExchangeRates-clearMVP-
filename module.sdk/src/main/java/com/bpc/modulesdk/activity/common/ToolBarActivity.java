package com.bpc.modulesdk.activity.common;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.AnimatorRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import ru.bpc.mobilebanksdk.R;

public abstract class ToolBarActivity extends CommonActivity {


    private Toolbar toolbar;
    private OnBackPressListener onBackPressListener;

    protected Snackbar loadingSnack;

    /**
     * Prevents crashes on pre-Lollipop devices
     * when vector drawables used in source selectors
     */

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
        initToolbar();
    }

    @LayoutRes
    protected abstract int getContentLayoutId();

    protected void setOnBackPressListener(OnBackPressListener listener) {
        this.onBackPressListener = listener;
    }

    protected void showProgressSnackbar(@StringRes int messageId) {
        Log.d("ToolBarActivity", "showProgressSnackbar: ");
        hideSnackBar();
        loadingSnack = Snackbar.make(findViewById(android.R.id.content), messageId, Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) loadingSnack.getView();
        View snackView = LayoutInflater.from(this).inflate(R.layout.loading_snackbar, null);
        layout.addView(snackView, 0);
        loadingSnack.show();
    }


    protected void hideSnackBar() {
        if (loadingSnack != null && loadingSnack.isShown()) loadingSnack.dismiss();
    }

    @Override
    public void onBackPressed() {
        if (onBackPressListener == null || !onBackPressListener.onBackPressed())
            super.onBackPressed();
    }

    /**
     * Close software keyboard.
     * Required that {@link Activity#getCurrentFocus()} return not null value
     */
    protected void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public interface OnBackPressListener {
        /**
         * @return true - если не нужно вызывать onBackPressed в активности
         */
        boolean onBackPressed();
    }

    protected void replace(@IdRes int containerId, Fragment fragment) {
        getFragmentManager().beginTransaction()
                .replace(containerId, fragment)
                .commit();
    }

    protected void replace(@IdRes int containerId, Fragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(containerId, fragment);
        if (addToBackStack) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    protected void replace(@IdRes int containerId, Fragment fragment, boolean addToBackStack, @AnimatorRes  int leftAnimation, @AnimatorRes int rightAnimation) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(leftAnimation, rightAnimation, 0, 0);
        ft.replace(containerId, fragment);
        if (addToBackStack) {
            ft.addToBackStack(fragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
            }
        }
    }

    @Nullable
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            closeKeyBoard();
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
