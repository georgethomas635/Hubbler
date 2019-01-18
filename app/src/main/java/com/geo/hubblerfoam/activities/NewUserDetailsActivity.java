package com.geo.hubblerfoam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.geo.hubblerfoam.R;
import com.geo.hubblerfoam.app.Constants;
import com.geo.hubblerfoam.app.Preference;
import com.geo.hubblerfoam.contracts.activities.NewUserDetailsActivityContracts;
import com.geo.hubblerfoam.model.InputFieldModel;
import com.geo.hubblerfoam.presenters.NewUserDetailsActivityPresenter;
import com.geo.hubblerfoam.widgets.CustomeDropDown;
import com.geo.hubblerfoam.widgets.CustomeTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.geo.hubblerfoam.app.Constants.ONE;
import static com.geo.hubblerfoam.app.Constants.ZERO;

/**
 * Created by george
 * on 06/01/19.
 */
public class NewUserDetailsActivity extends AppCompatActivity
        implements NewUserDetailsActivityContracts.View {

    @BindView(R.id.rl_foam)
    LinearLayout rlUserFoam;

    private NewUserDetailsActivityPresenter mPresenter;
    private int childNumber = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_details);
        ButterKnife.bind(this);
        initPresenter();
        mPresenter.setupUserDetailsFoam();
    }

    private void initPresenter() {
        childNumber = getIntent().getIntExtra(Constants.CHILD_NUMBER, 0);
        mPresenter = new NewUserDetailsActivityPresenter(getIntent().getParcelableArrayListExtra(Constants.FOAM_DATA));
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

    @Override
    public void addComposite(String fieldName, int index, List<InputFieldModel> fields) {
        Button buttonField = createButton(index, fieldName);
        rlUserFoam.addView(buttonField);
        buttonField.setOnClickListener(v -> navigateToNewPage(fields));
    }

    @Override
    public JSONObject getUserDetails() {
        return Preference.getInstance(this).getUserDetails();
    }

    @Override
    public void removeLastItem(JSONArray userList) {
        JSONObject userListObject = new JSONObject();
        try {
            userListObject.put(Constants.USER_LIST, userList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Preference.getInstance(this).reWriteUserDetails(userListObject);
    }

    private void navigateToNewPage(List<InputFieldModel> fields) {
        Intent intent = new Intent(this, NewUserDetailsActivity.class);
        intent.putExtra(Constants.CHILD_NUMBER, childNumber + ONE);
        intent.putParcelableArrayListExtra(Constants.FOAM_DATA, (ArrayList<? extends Parcelable>)
                fields);
        startActivity(intent);
    }

    private Button createButton(int index, String fieldName) {
        Button btnTag = new Button(this);
        btnTag.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btnTag.getLayoutParams();
        params.setMargins(ZERO, 20, ZERO, 10);
        btnTag.setLayoutParams(params);
        String buttonName = getResources().getString(R.string.add) + fieldName;
        btnTag.setText(buttonName);
        btnTag.setId(index);
        btnTag.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        return btnTag;
    }
}
