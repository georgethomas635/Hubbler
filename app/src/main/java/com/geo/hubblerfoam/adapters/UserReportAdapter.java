package com.geo.hubblerfoam.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geo.hubblerfoam.R;
import com.geo.hubblerfoam.contracts.adapter.UserReportAdapterContracts;
import com.geo.hubblerfoam.presenters.adapter.UserReportAdapterPresenter;

import org.json.JSONArray;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by george
 * on 08/01/19.
 */
public class UserReportAdapter extends RecyclerView.Adapter<UserReportAdapter.ViewHolder> {

    private UserReportAdapterPresenter mPresenter;

    public UserReportAdapter(JSONArray userlist) {
        mPresenter = new UserReportAdapterPresenter(userlist);
    }

    @NonNull
    @Override
    public UserReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user_details,
                        viewGroup,
                        false);
        return new UserReportAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        mPresenter.attach(viewHolder);
        mPresenter.bindView(position);
        mPresenter.detach();
    }


    @Override
    public int getItemCount() {
        return mPresenter.getArraySize();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements UserReportAdapterContracts.View {
        @BindView(R.id.tv_first_item)
        TextView tvFirstRecord;

        @BindView(R.id.tv_second_item)
        TextView tvSecondRecord;

        @BindView(R.id.view1)
        View viewTop;

        @BindView(R.id.view2)
        View viewBottom;

        private ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setUserDetails(String firstRecord, String secondRecord) {
            tvFirstRecord.setText(firstRecord);
            tvSecondRecord.setText(secondRecord);
        }

        @Override
        public void hideTopView() {
            viewTop.setVisibility(View.GONE);
        }

        @Override
        public void hideBottomView() {
            viewBottom.setVisibility(View.GONE);
        }

    }
}
