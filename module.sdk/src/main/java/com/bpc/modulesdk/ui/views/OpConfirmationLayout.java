package com.bpc.modulesdk.ui.views;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bpc.modulesdk.rest.dto.pojo.entries.ConfirmRequestDescriptor;
import com.bpc.modulesdk.fragment.dialogs.SelectDialogFragment;
import com.bpc.modulesdk.modulity.ModulesManager;
import com.bpc.modulesdk.modulity.facilities.OpConfirmatorDescriptor;
import com.bpc.modulesdk.modulity.module.OpConfirmationUser;
import com.bpc.modulesdk.security.SharedPreferencesHelper;
import com.bpc.modulesdk.utils.callback.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.bpc.mobilebanksdk.R;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Smolyaninov on 24.01.2017.
 */

public class OpConfirmationLayout extends FrameLayout {

    private OpConfirmator opConfirmator;
    private LayoutInflater layoutInflater;
    private CardView confirmatorContainerView;
    private TextView changeTypeView;
    private Map<ConfirmRequestDescriptor.Type, OpConfirmatorDescriptor> confirmatorDescriptorsMap;
    private ConfirmRequestDescriptor currentConfirmRequestDescriptor;
    private String currentTransRef;
    private String currentConfirmVal;
    private Callback currentOnCompleteCallback;
    private Callback currentOnChangeCallback;

    public OpConfirmationLayout(Context context) {
        super(context);
        init();
    }

    public OpConfirmationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OpConfirmationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (isInEditMode()) {
            setBackgroundColor(Color.parseColor("#ffee55"));
            TextView textView = new TextView(getContext());
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            textView.setLayoutParams(params);
            textView.setText(getClass().getSimpleName());

            float scale = getResources().getDisplayMetrics().density;
            textView.setPadding((int) (30 * scale + 0.5f),
                    (int) (10 * scale + 0.5f),
                    (int) (30 * scale + 0.5f),
                    (int) (10 * scale + 0.5f));
            addView(textView);
            return;
        }

        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_op_confirmation_layout, this);
        confirmatorContainerView = (CardView) rootView.findViewById(R.id.confirmator_container);
        changeTypeView = (TextView) rootView.findViewById(R.id.change_type);

        changeTypeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChangeTypeDialog();
            }
        });
    }

    public void initConfirmation(ConfirmRequestDescriptor descriptor, String transRef) {
        currentConfirmRequestDescriptor = descriptor;
        currentTransRef = transRef;

        // TODO: по идее у дескриптора должен быть список возможных подтверждений, и надо выбирать по нему.
        final List<ConfirmRequestDescriptor.Type> availableConfirmationTypes = new ArrayList<>();
        availableConfirmationTypes.add(descriptor.getType());
        availableConfirmationTypes.add(ConfirmRequestDescriptor.Type.ONEPASS);

        List<Observable<OpConfirmatorDescriptor>> descriptorObsertables = new ArrayList<>();
        descriptorObsertables.add(Observable.just(new AgreementOpConfirmatorDescriptor()));
        descriptorObsertables.add(Observable.just(new StandardOpConfirmationDescriptor()));

        for (OpConfirmationUser opConfirmationUser : ModulesManager.opConfirmationUsers) {
            descriptorObsertables.add(opConfirmationUser
                    .getOpConfirmatorDescriptorObservable(getContext())
                    .onErrorReturn(new Func1<Throwable, OpConfirmatorDescriptor>() {
                        @Override
                        public OpConfirmatorDescriptor call(Throwable throwable) {
                            return null;
                        }
                    }));
        }

        //TODO

        /*
        Observable.zip(descriptorObsertables, new FuncN<List<OpConfirmatorDescriptor>>() {
            @Override
            public List<OpConfirmatorDescriptor> call(Object[] args) {
                List<OpConfirmatorDescriptor> result = new ArrayList<>();

                for (int i = 0, len = args.length; i < len; i++) {
                    if (args[i] != null) {
                        result.add((OpConfirmatorDescriptor) args[i]);
                    }
                }

                return result;
            }
        }).subscribe(new Action1<List<OpConfirmatorDescriptor>>() {
            @Override
            public void call(List<OpConfirmatorDescriptor> result) {
                confirmatorDescriptorsMap = new LinkedHashMap<>();

                for (ConfirmRequestDescriptor.Type confirmationType : availableConfirmationTypes) {
                    for (OpConfirmatorDescriptor confirmatorDescriptor : result) {
                        if (confirmatorDescriptor.canHandleType(confirmationType)) {
                            confirmatorDescriptorsMap.put(confirmationType, confirmatorDescriptor);
                        }
                    }
                }

                updateOpConfirmatorView();
            }
        });*/
    }

    private void updateOpConfirmatorView() {
        changeTypeView.setVisibility(confirmatorDescriptorsMap.size() < 2 ? View.GONE : View.VISIBLE);

        boolean hasPrevOpConfirmator = (opConfirmator != null);
        confirmatorContainerView.removeAllViews();
        opConfirmator = createOpConfirmator();

        if (opConfirmator == null) {
            return;
        }

        View view = opConfirmator.getView();

        if (view == null) {
            return;
        }

        confirmatorContainerView.addView(view);
        setVisibility(View.VISIBLE);

        if (currentConfirmVal != null) {
            opConfirmator.setConfirmVal(currentConfirmVal);
        }

        if (currentOnCompleteCallback != null || currentOnChangeCallback != null) {
            opConfirmator.setActions(currentOnCompleteCallback, currentOnChangeCallback);
        }

        if (hasPrevOpConfirmator && currentOnChangeCallback != null) {
            currentOnChangeCallback.action();
        }
    }

    private OpConfirmator createOpConfirmator() {
        ConfirmRequestDescriptor.Type preferredType = ConfirmRequestDescriptor.Type
                .fromString(SharedPreferencesHelper.getPreferredConfirmationType());

        ConfirmRequestDescriptor.Type confirmationType = currentConfirmRequestDescriptor.getType();

        // TODO: сейчас подмена типа подтверждения срабатывает только для OnePass.
        if (confirmationType != ConfirmRequestDescriptor.Type.AGREEMENT
                && preferredType == ConfirmRequestDescriptor.Type.ONEPASS
                && confirmatorDescriptorsMap.containsKey(preferredType)) {

            confirmationType = preferredType;
        }

        OpConfirmatorDescriptor confirmatorDescriptor = confirmatorDescriptorsMap.get(confirmationType);

        if (confirmatorDescriptor == null) {
            return null;
        }

        return confirmatorDescriptor.getOpConfirmator(getContext(), currentConfirmRequestDescriptor, currentTransRef);
    }

    public boolean hasOpConfirmator() {
        return (opConfirmator != null);
    }

    public String getConfirmVal() {
        return (opConfirmator != null ? opConfirmator.getConfirmVal() : null);
    }

    public void setConfirmVal(String confirmVal) {
        if (opConfirmator != null) {
            currentConfirmVal = confirmVal;
            opConfirmator.setConfirmVal(confirmVal);
        }
    }

    public boolean isValid() {
        return (opConfirmator != null && opConfirmator.isValid());
    }

    public void setActions(Callback onCompleteCallback, Callback onChangeCallback) {
        if (opConfirmator != null) {
            currentOnCompleteCallback = onCompleteCallback;
            currentOnChangeCallback = onChangeCallback;

            opConfirmator.setActions(onCompleteCallback, onChangeCallback);
        }
    }

    public int getErrorMessage() {
        return (opConfirmator != null ? opConfirmator.getErrorMessage() : 0);
    }

    public boolean handleConfirmationError() {
        return (opConfirmator != null && opConfirmator.handleConfirmationError());
    }

    private void openChangeTypeDialog() {
        if (!(getContext() instanceof Activity)) {
            return;
        }

        SelectDialogFragment selectDialogFragment = new SelectDialogFragment();
        final List<ConfirmRequestDescriptor.Type> availableConfirmationTypes = new ArrayList<>(confirmatorDescriptorsMap.keySet());

        selectDialogFragment.setListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which >= 0 && which < availableConfirmationTypes.size()) {
                    SharedPreferencesHelper.setPreferredConfirmationType(availableConfirmationTypes.get(which).toString());
                    updateOpConfirmatorView();
                }
            }
        });

        List<String> values = new ArrayList<>();

        for (Map.Entry<ConfirmRequestDescriptor.Type, OpConfirmatorDescriptor> entry : confirmatorDescriptorsMap.entrySet()) {
            values.add(entry.getValue().getConfirmationTypeText(getContext(), entry.getKey()));
        }

        selectDialogFragment.setValues(values);
        selectDialogFragment.setTitle(getContext().getString(R.string.op_confirm_change_type_dialog_title));
        selectDialogFragment.show(((Activity) getContext()).getFragmentManager(), null);
    }

    private static class AgreementOpConfirmatorDescriptor implements OpConfirmatorDescriptor {
        @Override
        public boolean canHandleType(ConfirmRequestDescriptor.Type confirmationType) {
            return (ConfirmRequestDescriptor.Type.AGREEMENT == confirmationType);
        }

        @Override
        public String getConfirmationTypeText(Context context, ConfirmRequestDescriptor.Type confirmationType) {
            return null;
        }

        @Override
        public OpConfirmator getOpConfirmator(Context context, ConfirmRequestDescriptor confirmRequestDescriptor, String transRef) {
            return new AgreementOpConformator(context, confirmRequestDescriptor);
        }
    }

    private static class StandardOpConfirmationDescriptor implements OpConfirmatorDescriptor {
        @Override
        public boolean canHandleType(ConfirmRequestDescriptor.Type confirmationType) {
            return (ConfirmRequestDescriptor.Type.OTP == confirmationType
                    || ConfirmRequestDescriptor.Type.PASSWORD == confirmationType
                    || ConfirmRequestDescriptor.Type.SCRATCHCODE == confirmationType);
        }

        @Override
        public String getConfirmationTypeText(Context context, ConfirmRequestDescriptor.Type confirmationType) {
            switch (confirmationType) {
                case OTP:
                    return context.getString(R.string.op_confirm_type_otp);

                case PASSWORD:
                    return context.getString(R.string.op_confirm_type_password);

                case SCRATCHCODE:
                    return context.getString(R.string.op_confirm_type_scratchcode);

                default:
                    return null;
            }
        }

        @Override
        public OpConfirmator getOpConfirmator(Context context, ConfirmRequestDescriptor confirmRequestDescriptor, String transRef) {
            OpConfirmationView confirmationView = new OpConfirmationView(context);
            confirmationView.setType(confirmRequestDescriptor);

            if (confirmRequestDescriptor.getType() == ConfirmRequestDescriptor.Type.SCRATCHCODE) {
                confirmationView.setScratchCard(confirmRequestDescriptor.getScardNum(), confirmRequestDescriptor.getScodeId());
            }

            confirmationView.setTransRef(transRef);
            return confirmationView;
        }
    }
}
