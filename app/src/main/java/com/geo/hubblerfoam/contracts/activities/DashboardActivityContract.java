package com.geo.hubblerfoam.contracts.activities;

import com.geo.hubblerfoam.contracts.BasePresenter;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by george
 * on 06/01/19.
 */
public interface DashboardActivityContract {

    interface View {

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
