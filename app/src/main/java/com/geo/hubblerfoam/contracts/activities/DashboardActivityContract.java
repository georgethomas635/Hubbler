package com.geo.hubblerfoam.contracts.activities;

import com.geo.hubblerfoam.contracts.BasePresenter;
import com.geo.hubblerfoam.contracts.BaseView;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by george
 * on 06/01/19.
 */
public interface DashboardActivityContract {

    interface View extends BaseView {

        void navigateToNewUserDetailsActivity();

        void hideEmptyStatus(boolean isUserListEmpty);

        void setReportCount(int count);

        void setUserReport(JSONArray userlist);
    }

    interface Presenter extends BasePresenter<View> {
        void showRegisteredUserDetails();

        void setUserlist(JSONObject userDetails);
    }
}
