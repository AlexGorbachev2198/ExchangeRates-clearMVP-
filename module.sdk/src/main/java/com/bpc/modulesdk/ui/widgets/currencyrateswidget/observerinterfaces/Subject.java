package com.bpc.modulesdk.ui.widgets.currencyrateswidget.observerinterfaces;

/**
 * Created by Smolyaninov on 07.02.2017.
 */

public interface Subject {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
