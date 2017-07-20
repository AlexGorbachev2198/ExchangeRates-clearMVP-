package com.bpc.modulesdk;

import android.os.Bundle;

/**
 * Created by Masloed on 31.03.2017.
 */

public class Event {

    private String message;
    private Bundle bundle = new Bundle();

    public Event(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        if (bundle != null) this.bundle = bundle;
    }
}
