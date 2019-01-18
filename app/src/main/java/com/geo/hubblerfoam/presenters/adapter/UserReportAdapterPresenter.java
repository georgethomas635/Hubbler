package com.geo.hubblerfoam.presenters.adapter;

import com.geo.hubblerfoam.app.Constants;
import com.geo.hubblerfoam.contracts.adapter.UserReportAdapterContracts;
import com.geo.hubblerfoam.model.InputFieldModel;
import com.geo.hubblerfoam.utils.AppUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.geo.hubblerfoam.app.Constants.ONE;

/**
 * Created by george
 * on 09/01/19.
 */
public class UserReportAdapterPresenter implements UserReportAdapterContracts.Presenter {

    private UserReportAdapterContracts.View mView;
    private JSONArray userlist;

    public UserReportAdapterPresenter(JSONArray userlist) {
        this.userlist = userlist;
    }

    @Override
    public void attach(UserReportAdapterContracts.View view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public void bindView(int position) {
        JSONObject userRecord;
        try {
            userRecord = (JSONObject) userlist.get(position);
            List<InputFieldModel> userFoam = AppUtils.convertJsonStringToModel(Constants.FOAM_STRUCTURE);
            mView.setUserDetails(userFoam.get(Constants.ZERO).getFieldName() + Constants.COLON + Constants.SPACE +
                            userRecord.get(userFoam.get(Constants.ZERO).getFieldName()),
                    userFoam.get(ONE).getFieldName() + Constants.COLON + Constants.SPACE +
                            userRecord.get(userFoam.get(ONE).getFieldName()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        handleViews(position);
    }

    private void handleViews(int position) {
        if (position == Constants.ZERO) {
            mView.hideTopView();
        }
        if (position == userlist.length() - ONE) {
            mView.hideBottomView();
        }
    }

    @Override
    public int getArraySize() {
        if (userlist != null) {
            return userlist.length();
        } else {
            return Constants.ZERO;
        }
    }
}
