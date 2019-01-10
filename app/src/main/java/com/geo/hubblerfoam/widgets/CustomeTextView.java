package com.geo.hubblerfoam.widgets;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.geo.hubblerfoam.R;
import com.geo.hubblerfoam.app.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by george
 * on 06/01/19.
 */
public class CustomeTextView extends RelativeLayout {

    @BindView(R.id.tv_heading)
    TextView tvFieldName;
    @BindView(R.id.edt_input_field)
    EditText etvInputField;
    @BindView(R.id.rlv_input_field_container)
    LinearLayout rlvInputFieldContainer;
    private InputFilter alphabetOnlyFilter;
    private InputFilter alphaNumericFilter;
    private InputFilter numericFilter;
    private boolean mIsNameField;
    private boolean mIsNumberField;
    private boolean mIsMultiLineField;
    private boolean mIsAlphaNumeric;

    // region Constructors

    public CustomeTextView(Context context) {
        super(context);
        initTextInputFieldLayout(context, null);
    }

    // endregion
    void initTextInputFieldLayout(Context context, AttributeSet attributeSet) {
        initTextInputFieldView(context);
        initFilters();
        applyFiltersForInputField();

    }

    void initTextInputFieldView(Context context) {
        View textInputFieldView = LayoutInflater.from(context)
                .inflate(R.layout.text_edit_field_layout, new RelativeLayout(context), false);
        this.addView(textInputFieldView);
        ButterKnife.bind(this, textInputFieldView);
    }

    private void setAsAlphaNumericField() {
        etvInputField.setFilters(new InputFilter[]{alphaNumericFilter});
    }

    private void setAsNameOnlyField() {
        //Filter to prevent user from entering characters other than alphabets
        etvInputField.setFilters(new InputFilter[]{alphabetOnlyFilter});
    }

    private void setAsNumberOnlyField() {
        //Filter to prevent user from entering characters other than alphabets
        etvInputField.setFilters(new InputFilter[]{numericFilter});
    }

    public void setAsDropdown() {

    }

    /**
     * Apply enabled filters to the edit text field
     */
    private void applyFiltersForInputField() {
        if (mIsNameField) {
            setAsNameOnlyField();
        } else if (mIsAlphaNumeric) {
            setAsAlphaNumericField();
        } else if (mIsNumberField) {
            setAsNumberOnlyField();
        }

    }

    private void setIsAlphaNumeric(boolean isAlphaNumericField) {
        mIsAlphaNumeric = isAlphaNumericField;
    }

    public void setFieldName(String fieldName) {
        tvFieldName.setText(fieldName);
    }

    public void setAsNumericField() {
        mIsNumberField = true;
        etvInputField.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    public void setAsTextField() {
        etvInputField.setInputType(InputType.TYPE_CLASS_TEXT);
    }

    public void setAsMultilineTextField() {
        etvInputField.getLayoutParams().height = 350;
        etvInputField.setSingleLine(false);
        etvInputField.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
    }

    public String getUserInput() {
        return etvInputField.getText().toString().trim();
    }

    private void initFilters() {
        alphabetOnlyFilter = (src, start, end, dst, dStart, dEnd) -> {
            if (src.equals(Constants.EMPTY)) {
                return src;
            }
            if (src.toString().matches(Constants.REGEX_NAME)) {
                return src;
            }
            return Constants.EMPTY;
        };
        alphaNumericFilter = (src, start, end, dst, dStart, dEnd) -> {
            if (src.equals(Constants.EMPTY)) {
                return src;
            }
            if (src.toString().matches(Constants.REGEX_ALPHA_NUMERIC)) {
                return src;
            }
            return Constants.EMPTY;
        };
        numericFilter = (src, start, end, dst, dStart, dEnd) -> {
            if (src.equals(Constants.EMPTY)) {
                return src;
            }
            if (src.toString().matches(Constants.REGEX_NUMBERS)) {
                return src;
            }
            return Constants.EMPTY;
        };

    }

    public void setTextViewId(int textFieldId) {
        etvInputField.setId(textFieldId);
    }
}
