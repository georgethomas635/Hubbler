package com.geo.hubblerfoam.presenters;

import com.geo.hubblerfoam.app.Constants;
import com.geo.hubblerfoam.contracts.activities.DashboardActivityContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george
 * on 06/01/19.
 */
public class DashboardActivityPresenter implements DashboardActivityContract.Presenter {

    private DashboardActivityContract.View mView;
    private JSONArray userlist;

    public DashboardActivityPresenter(JSONObject userDetails) {
        setUserlist(userDetails);
    }

    @Override
    public void setUserlist(JSONObject userDetails) {
        try {
            if (userDetails != null) {
                userlist = (JSONArray) userDetails.get(Constants.USER_LIST);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void attach(DashboardActivityContract.View view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public void showRegisteredUserDetails() {
        mView.setUserReport(userlist);
        if (userlist == null || userlist.length() == 0) {
            mView.hideEmptyStatus(true);
            mView.setReportCount(Constants.ZERO);
        } else {
            mView.hideEmptyStatus(false);
            mView.setReportCount(userlist.length());
        }
    }
}
