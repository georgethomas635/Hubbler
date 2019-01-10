package com.geo.hubblerfoam.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.geo.hubblerfoam.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by george
 * on 06/01/19.
 */
public class CustomeDropDown extends RelativeLayout {

    @BindView(R.id.spinner1)
    Spinner spDropdown;
    @BindView(R.id.tv_heading)
    TextView tvFieldName;

    // region Constructors

    public CustomeDropDown(Context context) {
        super(context);
        initTextInputFieldLayout(context, null);
    }

    // endregion

    void initTextInputFieldLayout(Context context, AttributeSet attributeSet) {
        initTextInputFieldView(context);

    }

    void initTextInputFieldView(Context context) {
        View textInputFieldView = LayoutInflater.from(context)
                .inflate(R.layout.text_dropdown_layout, new RelativeLayout(context), false);
        this.addView(textInputFieldView);
        ButterKnife.bind(this, textInputFieldView);
    }

    public void setFieldName(String fieldName) {
        tvFieldName.setText(fieldName);
    }

    public void setDropdownOptions(String[] options, Context context) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                android.R.layout.simple_expandable_list_item_1, options);
        spDropdown.setAdapter(adapter);
    }

    public void setSpinnerId(int position) {
        spDropdown.setId(position);
    }
}
