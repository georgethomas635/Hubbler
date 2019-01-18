package com.geo.hubblerfoam.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.geo.hubblerfoam.R;
import com.geo.hubblerfoam.adapters.UserReportAdapter;
import com.geo.hubblerfoam.app.Constants;
import com.geo.hubblerfoam.app.Preference;
import com.geo.hubblerfoam.contracts.activities.DashboardActivityContract;
import com.geo.hubblerfoam.presenters.DashboardActivityPresenter;
import com.geo.hubblerfoam.utils.AppUtils;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends AppCompatActivity implements DashboardActivityContract.View {

    @BindView(R.id.tv_no_reports)
    TextView tvEmptyReports;

    @BindView(R.id.rl_user_list)
    RecyclerView rvUserList;

    @BindView(R.id.tv_report_count)
    TextView tvReportCount;

    private DashboardActivityPresenter mPresenter;
    private UserReportAdapter userReportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        initPresenter();
        mPresenter.showRegisteredUserDetails();
    }

    private void initPresenter() {
        mPresenter = new DashboardActivityPresenter(Preference.getInstance(this).getUserDetails());
        mPresenter.attach(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setUserlist(Preference.getInstance(this).getUserDetails());
        mPresenter.showRegisteredUserDetails();
    }

    @OnClick(R.id.fab_add)
    public void addNewPersonDetails() {
        navigateToNewUserDetailsActivity();
    }


    @Override
    public void navigateToNewUserDetailsActivity() {
        Intent intent = new Intent(this, NewUserDetailsActivity.class);
        intent.putParcelableArrayListExtra(Constants.FOAM_DATA, (ArrayList<? extends Parcelable>)
                AppUtils.convertJsonStringToModel(Constants.FOAM_STRUCTURE));
        startActivity(intent);
    }

    @Override
    public void hideEmptyStatus(boolean isUserListEmpty) {
        tvEmptyReports.setVisibility(isUserListEmpty ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setReportCount(int count) {
        tvReportCount.setText(String.valueOf(count));
    }

    @Override
    public void setUserReport(JSONArray userlist) {
        //Setup grid layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvUserList.setLayoutManager(layoutManager);
        userReportAdapter = new UserReportAdapter(userlist);
        rvUserList.setAdapter(userReportAdapter);
    }
}
