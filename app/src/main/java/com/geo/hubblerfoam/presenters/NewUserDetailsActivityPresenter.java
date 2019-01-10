package com.geo.hubblerfoam.presenters;

import com.geo.hubblerfoam.R;
import com.geo.hubblerfoam.app.Constants;
import com.geo.hubblerfoam.contracts.activities.NewUserDetailsActivityContracts;
import com.geo.hubblerfoam.model.InputFieldModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by george
 * on 06/01/19.
 */
public class NewUserDetailsActivityPresenter implements NewUserDetailsActivityContracts.Presenter {


    private NewUserDetailsActivityContracts.View mView;
    private List<InputFieldModel> foamStructureJson;
    private JSONObject jsonObject = new JSONObject();

    public NewUserDetailsActivityPresenter(List<InputFieldModel> foamStructure) {
        foamStructureJson = foamStructure;
    }

    @Override
    public void attach(NewUserDetailsActivityContracts.View view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public void setupUserDetailsFoam() {
        int index = 0;
        if (foamStructureJson != null) {
            for (InputFieldModel fieldModel : foamStructureJson) {
                switch (fieldModel.getType()) {
                    case Constants.INPUTFIELD_TEXT:
                        mView.addTextField(fieldModel.getFieldName(), fieldModel.isRequired(), index);
                        break;
                    case Constants.INPUTFIELD_NUMBER:
                        mView.addNumberField(fieldModel.getFieldName(), fieldModel.getMin(), fieldModel.getMax(), index);
                        break;
                    case Constants.INPUTFIELD_MULTILINE:
                        mView.addMultilineField(fieldModel.getFieldName(), index);
                        break;
                    case Constants.INPUTFIELD_DROPDOWN:
                        mView.addDropdown(fieldModel.getFieldName(), fieldModel.getOptions(), index);
                        break;
                }
                index++;
            }
        }
    }

    @Override
    public void validateData() {
        int index = 0;
        boolean validationCheck = true;
        for (InputFieldModel fieldModel : foamStructureJson) {
            if (fieldModel.isRequired()) {
                if (!mView.mandatoryFieldValidate(index)) {
                    validationCheck = false;
                    mView.shoeErrorMessage(fieldModel.getFieldName(), R.string.field_is_required);
                }
            } else if (fieldModel.getMax() != Constants.ZERO) {
                if (!mView.minMaxValidate(index, fieldModel.getMin(), fieldModel.getMax())) {
                    validationCheck = false;
                    mView.shoeErrorMessage(fieldModel.getFieldName(), R.string.set_bounds, fieldModel.getMin(), fieldModel.getMax());
                }
            }
            if (fieldModel.getType().equals(Constants.INPUTFIELD_DROPDOWN)) {
                createJsonStucture(fieldModel.getFieldName(), mView.getValueOfDropdown(fieldModel.getFieldName(), index));
            } else {
                createJsonStucture(fieldModel.getFieldName(), mView.getValueOf(fieldModel.getFieldName(), index));
            }
            index++;
        }
        if (validationCheck) {
            mView.saveData(jsonObject);
            mView.navigateToHome();
        }
    }

    private void createJsonStucture(String key, String item) {
        try {
            jsonObject.put(key, item);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
