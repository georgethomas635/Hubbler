package com.geo.hubblerfoam.app;

/**
 * Created by george
 * on 06/01/19.
 */
public class Constants {

    public static final String FOAM_STRUCTURE = "[{ \"field-name\": \"name\", \"type\": \"text\", \"required\": true }, { \"field-name\": \"Zone\", \"type\": \"dropdown\", \"options\": [\"A\", \"B\", \"C\", \"D\"] }, { \"field-name\": \"age\", \"type\": \"number\" }, { \"field-name\": \"address\", \"type\": \"composite\", \"fields\": [{ \"field-name\": \"Address line1\", \"type\": \"text\", \"required\": true }, { \"field-name\": \"Address line2\", \"type\": \"text\" }, { \"field-name\": \"City\", \"type\": \"text\", \"required\": true }, { \"field-name\": \"State\", \"type\": \"text\", \"required\": true },{ \"field-name\": \"Job\", \"type\": \"composite\", \"fields\": [{ \"field-name\": \"Company\", \"type\": \"text\", \"required\": true }, { \"field-name\": \"position\", \"type\": \"text\" }, { \"field-name\": \"City_name\", \"type\": \"text\", \"required\": true }, { \"field-name\": \"salary\", \"type\": \"text\", \"required\": true } ], \"required\": false } ], \"required\": false } ]";
    public static final String INPUTFIELD_TEXT = "text";
    public static final String INPUTFIELD_NUMBER = "number";
    public static final String INPUTFIELD_MULTILINE = "multiline";
    public static final String INPUTFIELD_DROPDOWN = "dropdown";
    public static final String INPUTFIELD_COMPOSITE = "composite";
    public static final String CHILD_NUMBER = "child";

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
    public static final String FOAM_DATA = "registration foam";
    public static String SLASH = "/";
}
