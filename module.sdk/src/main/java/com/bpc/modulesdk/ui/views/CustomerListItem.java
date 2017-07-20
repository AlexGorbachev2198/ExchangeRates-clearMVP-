package com.bpc.modulesdk.ui.views;

import android.content.Context;
import android.view.View;

/**
 * Created by Smolyaninov on 31.03.2017.
 */

public interface CustomerListItem {

    String getSectionTitle();

    View getItemContent(Context context);

}
