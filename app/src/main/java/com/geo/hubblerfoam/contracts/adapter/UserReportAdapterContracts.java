package com.geo.hubblerfoam.contracts.adapter;

import com.geo.hubblerfoam.contracts.BasePresenter;

/**
 * Created by george
 * on 09/01/19.
 */
public interface UserReportAdapterContracts {

    interface View {
        void setUserDetails(String firstRecord, String secondRecord);

        void hideTopView();

        void hideBottomView();
    }

    interface Presenter extends BasePresenter<View> {
        void bindView(int position);

        int getArraySize();
    }
}
