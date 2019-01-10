package com.geo.hubblerfoam.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.geo.hubblerfoam.R;
import com.geo.hubblerfoam.app.Constants;
import com.geo.hubblerfoam.app.Preference;
import com.geo.hubblerfoam.contracts.activities.NewUserDetailsActivityContracts;
import com.geo.hubblerfoam.presenters.NewUserDetailsActivityPresenter;
import com.geo.hubblerfoam.utils.AppUtils;
import com.geo.hubblerfoam.widgets.CustomeDropDown;
import com.geo.hubblerfoam.widgets.CustomeTextView;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by george
 * on 06/01/19.
 */
public class NewUserDetailsActivity extends AppCompatActivity
        implements NewUserDetailsActivityContracts.View {

    @BindView(R.id.rl_foam)
    LinearLayout rlUserFoam;

    private NewUserDetailsActivityPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_details);
        ButterKnife.bind(this);
        initPresenter();
        mPresenter.setupUserDetailsFoam();
    }

    private void initPresenter() {
        mPresenter = new NewUserDetailsActivityPresenter(
                AppUtils.convertJsonStringToModel(Constants.FOAM_STRUCTURE));
        mPresenter.attach(this);
    }

    @Override
    public void addTextField(String fieldName, boolean required, int position) {
        CustomeTextView customeTextView = createCustomeTextView(fieldName, position);
        customeTextView.setAsTextField();
        rlUserFoam.addView(customeTextView);
    }

    @Override
    public void addNumberField(String fieldName, int min, int max, int position) {
        CustomeTextView customeTextView = createCustomeTextView(fieldName, position);
        customeTextView.setAsNumericField();
        rlUserFoam.addView(customeTextView);
    }

    @Override
    public void addMultilineField(String fieldName, int position) {
        CustomeTextView customeTextView = createCustomeTextView(fieldName, position);
        customeTextView.setAsMultilineTextField();
        rlUserFoam.addView(customeTextView);
    }

    private CustomeTextView createCustomeTextView(String fieldName, int position) {
        CustomeTextView customeTextView = new CustomeTextView(this);
        customeTextView.setFieldName(fieldName);
        customeTextView.setTextViewId(position);
        return customeTextView;
    }

    @Override
    public void addDropdown(String fieldName, String[] options, int position) {
        CustomeDropDown dropDown = new CustomeDropDown(this);
        dropDown.setFieldName(fieldName);
        dropDown.setDropdownOptions(options, this);
        dropDown.setSpinnerId(position);
        rlUserFoam.addView(dropDown);
    }

    @OnClick(R.id.bt_done)
    public void doneButtonClicked() {
        mPresenter.validateData();
    }

    @Override
    public boolean mandatoryFieldValidate(int position) {
        EditText editText = rlUserFoam.findViewById(position);
        return !editText.getText().toString().equals(Constants.EMPTY);
    }

    @Override
    public boolean minMaxValidate(int position, int min, int max) {
        EditText editText = rlUserFoam.findViewById(position);
        return !editText.getText().toString().equals(Constants.EMPTY) && min <=
                Integer.parseInt(editText.getText().toString()) &&
                Integer.parseInt(editText.getText().toString()) <= max;
    }

    @Override
    public String getValueOf(String fieldName, int position) {
        EditText editText = rlUserFoam.findViewById(position);
        if (editText != null) {
            return editText.getText().toString();
        } else
            return null;
    }

    @Override
    public void saveData(JSONObject jsonObject) {
        Preference.getInstance(this).saveUserDetails(jsonObject);
        Log.e(getResources().getString(R.string.user_details), jsonObject.toString());
    }

    @Override
    public String getValueOfDropdown(String fieldName, int index) {
        Spinner spDropdown = rlUserFoam.findViewById(index);
        if (spDropdown != null) {
            return spDropdown.getSelectedItem().toString();
        } else {
            return null;
        }
    }

    @Override
    public void shoeErrorMessage(String fieldName, int errorMessage) {
        Toast.makeText(this, fieldName + Constants.SPACE + getResources().getString(errorMessage), Toast.LENGTH_LONG).show();
    }

    @Override
    public void shoeErrorMessage(String fieldName, int errorMessage, int min, int max) {
        Toast.makeText(this, getResources().getString(errorMessage) + Constants.SPACE +
                fieldName + getResources().getString(R.string.colon) + getResources().getString(R.string.min)
                + min + getResources().getString(R.string.min) + max, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHome() {
        finish();
    }
}
