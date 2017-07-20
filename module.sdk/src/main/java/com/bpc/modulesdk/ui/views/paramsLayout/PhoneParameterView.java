package com.bpc.modulesdk.ui.views.paramsLayout;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.Toast;

import com.bpc.modulesdk.helpers.TextInputLayoutHelper;
import com.bpc.modulesdk.ui.interfaces.ActivityResultReceiver;
import com.bpc.modulesdk.ui.interfaces.ActivityResultRequester;
import com.bpc.modulesdk.ui.interfaces.RequestPermissionReceiver;
import com.bpc.modulesdk.ui.interfaces.RequestPermissionRequester;
import com.bpc.modulesdk.ui.views.PhoneNumberEditText;
import com.bpc.modulesdk.ui.watchers.AfterTextChangedTextWatcher;
import com.bpc.modulesdk.ui.watchers.CharacterCountErrorWatcher;
import com.bpc.modulesdk.utils.PermissionsHelper;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Siarhei Mikevich on 5/24/17.
 */

public class PhoneParameterView extends ParameterView implements ActivityResultReceiver, RequestPermissionReceiver {

    public static final String[] PERMISSIONS_READ_CONTACTS = new String[]{
            Manifest.permission.READ_CONTACTS
    };

    private TextInputLayout inputValue;
    private PhoneNumberEditText editValue;
    private ImageView imagePhoneBook;
    private int minLength;
    private int maxLength;

    public PhoneParameterView(Context context, ParameterRecord parameter, RequestPermissionRequester permissionReceiver,
                              ActivityResultRequester resultRequester, boolean isEditable) {
        super(context, parameter, permissionReceiver, resultRequester, isEditable);
    }

    @Override
    protected void initEditMode() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_parameter_phone, this);

        inputValue = (TextInputLayout) findViewById(R.id.input_value_phone);
        editValue = (PhoneNumberEditText) findViewById(R.id.edit_value_phone);
        imagePhoneBook = (ImageView) findViewById(R.id.image_phone_book);

        inputValue.setHint(getParameter().getDescription());

        minLength = getParameter().getMin();
        maxLength = getParameter().getMax();
        editValue.addTextChangedListener(new CharacterCountErrorWatcher(inputValue, minLength, maxLength));
        editValue.setMaxLength(maxLength > 0 ? maxLength : Integer.MAX_VALUE);

        editValue.addTextChangedListener(new AfterTextChangedTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (valueChangeListener != null)
                    valueChangeListener.onChange(PhoneParameterView.this);
            }
        });

        editValue.setText(getParameter().getValue() != null && !getParameter().getValue().isEmpty() ? getParameter().getValue() : "");

        imagePhoneBook.setOnClickListener(v -> onPhoneBookClick());
    }

    @Override
    public String getEditedValue() {
        if (editValue == null)
            return getParameter().getValue();
        return editValue.getNormalizeText().toString();
    }

    @Override
    public String getUserRepresentationValue() {
        if (editValue == null)
            return getParameter().getValue();
        return editValue.getNormalizeText().toString();
    }

    @Override
    public boolean isValidValue() {
        if (inputValue == null) {
            if (getParameter().getValue() == null)
                return getParameter().getMin() <= 0;
            return TextInputLayoutHelper.hasValidLength(getParameter().getValue().length(), minLength, maxLength);
        }
        return TextInputLayoutHelper.hasValidLength(inputValue, minLength, maxLength);
    }

    private void onPhoneBookClick() {
        if (!PermissionsHelper.isPermissionGranted(getContext(), PERMISSIONS_READ_CONTACTS)) {
            if (permissionReceiver != null)
                permissionReceiver.needRequestPermissions(this, PERMISSIONS_READ_CONTACTS, R.string.request_read_contacts_permission_rationale_title,
                        R.string.request_read_contacts_permission_rationale_message);
            return;
        }
        openPhoneBook();
    }

    private void openPhoneBook() {
        if (resultRequester == null)
            return;

        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        resultRequester.startActivityForResult(this, intent);
    }

    @Override
    public void receive(Intent data) {
        String phoneNumber = getPhone(getContext(), data);
        if (phoneNumber != null)
            editValue.setText(phoneNumber);
        else
            Toast.makeText(getContext(), R.string.error_unable_get_contacts, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void permissionsAllowed() {
        if (PermissionsHelper.isPermissionGranted(getContext(), PERMISSIONS_READ_CONTACTS))
            openPhoneBook();
    }

    public static String getPhone(Context context, Intent data) {
        try {
            Uri contactUri = data.getData();
            String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                    ContactsContract.CommonDataKinds.Phone.NUMBER};
            Cursor cursor = context.getContentResolver().query(contactUri, projection, null, null, null);
            if (cursor == null)
                return null;
            cursor.moveToFirst();
            int indexNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String phoneNumber = cursor.getString(indexNumber);
            cursor.close();
            return phoneNumber;
        } catch (Exception ignored) {
        }
        return null;
    }

}
