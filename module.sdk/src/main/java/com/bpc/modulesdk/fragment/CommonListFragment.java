package com.bpc.modulesdk.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import ru.bpc.mobilebanksdk.R;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Smolyaninov on 21.03.2017.
 */

public abstract class CommonListFragment extends CommonFragment {

    protected TextView emptyText;
    protected ProgressBar progressBar;
    protected CompositeSubscription subscription = new CompositeSubscription();

    protected abstract int getEmptyViewTextId();

    protected abstract void fillScreen(View view);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.common_list, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = (ProgressBar) view.findViewById(R.id.transfers_progressbar);
        emptyText = (TextView) view.findViewById(R.id.empty_text);
        emptyText.setText(getEmptyViewTextId());
        fillScreen(view);
    }

    protected void showProgress(boolean show) {
        if (show) progressBar.setVisibility(View.VISIBLE);
        else progressBar.setVisibility(View.GONE);
    }

    protected void showEmptyView(boolean show) {
        if (show) emptyText.setVisibility(View.VISIBLE);
        else emptyText.setVisibility(View.GONE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        subscription.unsubscribe();
    }
}
