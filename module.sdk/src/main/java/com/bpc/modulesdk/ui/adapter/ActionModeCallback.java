package com.bpc.modulesdk.ui.adapter;

import android.support.annotation.MenuRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public abstract class ActionModeCallback implements ActionMode.Callback {

    private AppCompatActivity activity;
    private ActionMode actionMode;
    private int menuResId;
    private SelectableAdapter adapter;
    private boolean isActionModeStarted;

    public ActionModeCallback(AppCompatActivity activity, @MenuRes int menuResId) {
        this.activity = activity;
        this.menuResId = menuResId;
    }

    public abstract boolean onActionItemClicked(List<Integer> selectedPositions, MenuItem item);

    void bind(SelectableAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        mode.getMenuInflater().inflate(menuResId, menu);
        onActionModeStarted();
        return true;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        boolean isHandled = onActionItemClicked(adapter.getSelectedPositions(), item);
        if (isHandled)
            actionMode.finish();
        return isHandled;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        if (activity.getSupportActionBar() != null)
            activity.getSupportActionBar().show();
        adapter.clearSelection();
        isActionModeStarted = false;
        onActionModeFinished();
    }

    void start() {
        if (isActionModeStarted())
            return;
        if (activity.getSupportActionBar() != null)
            activity.getSupportActionBar().hide();
        actionMode = activity.startSupportActionMode(this);
        isActionModeStarted = true;
    }

    public boolean isActionModeStarted() {
        return isActionModeStarted;
    }

    public void onActionModeStarted() {
    }

    public void onActionModeFinished() {
    }

}