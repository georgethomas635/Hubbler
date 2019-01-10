package com.geo.hubblerfoam.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by george
 * on 06/01/19.
 */
public class InputFieldModel {

    @JsonProperty("field-name")
    private String fieldName;

    private boolean required;

    private String type;

    private int min;

    private int max;

    private String[] options;

    private boolean validation;

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

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public String[] getOptions() {
        return options;
    }
}
