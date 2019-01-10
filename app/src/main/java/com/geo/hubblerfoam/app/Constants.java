package com.geo.hubblerfoam.app;

/**
 * Created by george
 * on 06/01/19.
 */
public class Constants {

    public static final String FOAM_STRUCTURE = "[ {\"field-name\":\"Name\", \"type\":\"text\"}, {\"field-name\":\"Age\", \"type\":\"number\"}, {\"field-name\":\"Gender\", \"type\":\"dropdown\", \"options\":[\"male\", \"female\", \"other\"]}, {\"field-name\":\"Address\", \"type\":\"multiline\"},{\"field-name\":\"School\", \"type\":\"text\"} ]";
    public static final String INPUTFIELD_TEXT = "text";
    public static final String INPUTFIELD_NUMBER = "number";
    public static final String INPUTFIELD_MULTILINE = "multiline";
    public static final String INPUTFIELD_DROPDOWN = "dropdown";

    //Validation
    public static final String REGEX_NAME = "[a-zA-Z]+";
    public static final String REGEX_ALPHA_NUMERIC = "[a-zA-Z0-9]+";
    public static final String REGEX_NUMBERS = "[0-9]+";
    public static final String EMPTY = "";
    public static final String SPACE = " ";

    public static final int ZERO = 0;
    public static final int ONE = 1;

    public static final String USER_LIST = "user_list";
    public static final String USER = "user";
    public static final String COLON = ":";
}
