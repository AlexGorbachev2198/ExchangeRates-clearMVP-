package com.bpc.modulesdk.modulity.facilities;

import android.content.Context;

import com.bpc.modulesdk.rest.dto.pojo.entries.ConfirmRequestDescriptor;
import com.bpc.modulesdk.ui.views.OpConfirmator;

public interface OpConfirmatorDescriptor {
    boolean canHandleType(ConfirmRequestDescriptor.Type confirmationType);
    String getConfirmationTypeText(Context context, ConfirmRequestDescriptor.Type confirmationType);
    OpConfirmator getOpConfirmator(Context context, ConfirmRequestDescriptor confirmRequestDescriptor, String transRef);
}

