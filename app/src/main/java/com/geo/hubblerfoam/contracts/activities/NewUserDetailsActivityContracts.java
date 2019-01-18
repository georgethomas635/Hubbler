package com.geo.hubblerfoam.contracts.activities;

import com.geo.hubblerfoam.contracts.BasePresenter;
import com.geo.hubblerfoam.model.InputFieldModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by george
 * on 06/01/19.
 */
public interface NewUserDetailsActivityContracts {

    interface View {

        void addTextField(String fieldName, boolean required, int position);

        void addNumberField(String fieldName, int min, int max, int position);

        void addMultilineField(String fieldName, int position);

        void addDropdown(String fieldName, String[] Options, int position);

        boolean mandatoryFieldValidate(int position);

        boolean minMaxValidate(int position, int min, int max);

        String getValueOf(String fieldName, int position);

        void saveData(JSONObject jsonObject);

        String getValueOfDropdown(String fieldName, int index);

        void shoeErrorMessage(String fieldName, int errorMessage);

        void shoeErrorMessage(String fieldName, int errorMessage, int min, int max);

        void navigateToHome();

        void addComposite(String fieldName, int index, List<InputFieldModel> fields);

        JSONObject getUserDetails();

        void removeLastItem(JSONArray userList);
    }

    interface Presenter extends BasePresenter<View> {
        void setupUserDetailsFoam();

        void validateData();

    }
}
