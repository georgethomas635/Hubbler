package com.geo.hubblerfoam.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by george
 * on 06/01/19.
 */
public class InputFieldModel implements Parcelable {

    @JsonProperty("field-name")
    private String fieldName;

    private boolean required;

    private String type;

    private int min;

    private int max;

    private String[] options;

    public static final Creator<InputFieldModel> CREATOR = new Creator<InputFieldModel>() {
        @Override
        public InputFieldModel createFromParcel(Parcel in) {
            return new InputFieldModel(in);
        }

        @Override
        public InputFieldModel[] newArray(int size) {
            return new InputFieldModel[size];
        }
    };
    private List<InputFieldModel> fields;


    public InputFieldModel() {
    }

    private InputFieldModel(Parcel in) {
        fieldName = in.readString();
        required = in.readByte() != 0;
        type = in.readString();
        min = in.readInt();
        max = in.readInt();
        options = in.createStringArray();
        fields = in.createTypedArrayList(InputFieldModel.CREATOR);
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getType() {
        return type;
    }

    public boolean isRequired() {
        return required;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String[] getOptions() {
        return options;
    }

    public List<InputFieldModel> getFields() {
        return fields;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(fieldName);
        dest.writeByte((byte) (required ? 1 : 0));
        dest.writeString(type);
        dest.writeInt(min);
        dest.writeInt(max);
        dest.writeStringArray(options);
        dest.writeTypedList(fields);
    }
}
