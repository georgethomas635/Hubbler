package com.geo.hubblerfoam.presenters;

import com.geo.hubblerfoam.R;
import com.geo.hubblerfoam.app.Constants;
import com.geo.hubblerfoam.contracts.activities.NewUserDetailsActivityContracts;
import com.geo.hubblerfoam.model.InputFieldModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.geo.hubblerfoam.app.Constants.ZERO;

/**
 * Created by george
 * on 06/01/19.
 */
public class NewUserDetailsActivityPresenter implements NewUserDetailsActivityContracts.Presenter {


    private NewUserDetailsActivityContracts.View mView;
    private List<InputFieldModel> foamStructureJson;
    private JSONObject jsonObject = new JSONObject();
    private int childNumber;

    public NewUserDetailsActivityPresenter(List<InputFieldModel> foamStructure, int childNumber) {
        foamStructureJson = foamStructure;
        this.childNumber = childNumber;
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
        int index = ZERO;
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
                    case Constants.INPUTFIELD_COMPOSITE:
                        mView.addComposite(fieldModel.getFieldName(), index, fieldModel.getFields());
                        break;
                }
                index++;
            }
        }
    }

    @Override
    public void validateData() {
        int index = ZERO;
        boolean validationCheck = true;
        for (InputFieldModel fieldModel : foamStructureJson) {
            if (fieldModel.isRequired()) {
                if (!mView.mandatoryFieldValidate(index)) {
                    validationCheck = false;
                    mView.shoeErrorMessage(fieldModel.getFieldName(), R.string.field_is_required);
                }
            } else if (fieldModel.getMax() != ZERO) {
                if (!mView.minMaxValidate(index, fieldModel.getMin(), fieldModel.getMax())) {
                    validationCheck = false;
                    mView.shoeErrorMessage(fieldModel.getFieldName(), R.string.set_bounds, fieldModel.getMin(), fieldModel.getMax());
                }
            }
            createJSON(fieldModel, index);
            index++;
        }
        if (validationCheck) {
            if (childNumber == 0) {
                mView.saveData(jsonObject);
            } else {
                mView.saveCompositeData(jsonObject);
            }
            mView.navigateToHome();
        }
    }

    private void createJSON(InputFieldModel fieldModel, int index) {
        switch (fieldModel.getType()) {
            case Constants.INPUTFIELD_DROPDOWN:
                createJsonStucture(fieldModel.getFieldName(), mView.getValueOfDropdown(fieldModel.getFieldName(), index));
                break;
            case Constants.INPUTFIELD_COMPOSITE:
                addCompositeField(fieldModel);
                break;
            default:
                createJsonStucture(fieldModel.getFieldName(), mView.getValueOf(fieldModel.getFieldName(), index));
                break;
        }
    }

    private void addCompositeField(InputFieldModel fieldModel) {
        JSONObject userCompositeDetails = mView.getUserCompositeData();
        if (userCompositeDetails != null) {
            if (!userCompositeDetails.keys().next().equals(jsonObject.keys().next())) {
                createJsonStucture(fieldModel.getFieldName(), userCompositeDetails.toString());
            }
            mView.saveCompositeData(new JSONObject());
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
