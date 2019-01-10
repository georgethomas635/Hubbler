package com.geo.hubblerfoam.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by george
 * on 07/01/19.
 */
public class Preference {

    private static final String KEY_USERFOAM_DATA = "FOAM_DATA";

    @SuppressLint("StaticFieldLeak")
    private static Preference sSharedManager;
    private Context context;
    private SharedPreferences sSharedPref;

    private Preference(Context context) {
        this.context = context;
        setUpDefaultSharedPreferences(context);
    }

    public static Preference getInstance(Context context) {
        if (sSharedManager == null) {
            sSharedManager = new Preference(context);
        }
        return sSharedManager;
    }

    private void setUpDefaultSharedPreferences(Context context) {
        sSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Save Data As Json in Preference
     *
     * @param key:  the key value
     * @param data: the data to be stored
     */
    private void saveDataAsJson(String key, Object data) {
        if (sSharedPref != null) {
            SharedPreferences.Editor editor = sSharedPref.edit();
            editor.putString(key, data.toString());
            editor.apply();
        }
    }


    public void saveUserDetails(JSONObject jsonObject) {
        JSONObject userList = getUserDetails();
        JSONArray userArray = null;
        if (userList == null) {
            userList = new JSONObject();
        }
        try {
            userArray = (JSONArray) userList.get(Constants.USER_LIST);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (userArray == null) {
            userArray = new JSONArray();
        }
        userArray.put(jsonObject);
        try {
            userList.put(Constants.USER_LIST, userArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveDataAsJson(KEY_USERFOAM_DATA, userList);
    }

    public JSONObject getUserDetails() {
        String userDetails = sSharedPref.getString(KEY_USERFOAM_DATA, Constants.EMPTY);
        try {
            return new JSONObject(userDetails);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
