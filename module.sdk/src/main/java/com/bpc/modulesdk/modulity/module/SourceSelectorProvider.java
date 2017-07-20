package com.bpc.modulesdk.modulity.module;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Masloed on 25.02.2016.
 */
@Deprecated
public interface SourceSelectorProvider {

    @NonNull
    List<SourceSelectorUser> getListSelectorSource();
}
