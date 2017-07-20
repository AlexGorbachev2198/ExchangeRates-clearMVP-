package com.bpc.modulesdk.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;


public abstract class CommonFragment extends Fragment {


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    protected void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) this.getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(this.getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e(this.getClass().getName() + "," + "hideSoftKeyboard()", e.toString());
        }
    }

    protected void setTitle(CharSequence title) {
        Activity activity = getActivity();
        if (activity != null)
            activity.setTitle(title);
    }

    protected void setTitle(@StringRes int title) {
        Activity activity = getActivity();
        if (activity != null)
            activity.setTitle(title);
    }

    protected void replace(Fragment fragment, boolean isSaveToBackStackHistory) {
        View view = getView();
        if (view == null)
            return;
        int containerId = ((ViewGroup) view.getParent()).getId();
        Fragment oldFragment = getFragmentManager().findFragmentById(containerId);
        if (isSaveToBackStackHistory && oldFragment.getClass() == fragment.getClass())
            return;

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (oldFragment != null)
            transaction.hide(oldFragment);
        transaction.replace(containerId, fragment);
        if (isSaveToBackStackHistory)
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        transaction.commitAllowingStateLoss();
    }


    public void popBackStack() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            Activity activity = getActivity();
            if (activity != null)
                activity.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }

    protected void clearBackStack() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = getFragmentManager().getBackStackEntryAt(0);
            getFragmentManager().popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

}