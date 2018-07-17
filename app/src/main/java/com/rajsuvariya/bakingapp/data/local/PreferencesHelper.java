package com.rajsuvariya.bakingapp.data.local;

import android.content.Context;

import com.rajsuvariya.bakingapp.injection.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by rajsuvariya on 6/28/2016.
 */

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "_pref_file";

    private final ObscuredSharedPreferences mPref;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        //mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        mPref = ObscuredSharedPreferences.getPrefs(context, PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public String getString(String KEY, String DEFAULT) {
        return mPref.getString(KEY, DEFAULT);
    }

    public void putString(String KEY, String VALUE) {
        mPref.edit().putString(KEY, VALUE).apply();
    }

    public void putDouble(String KEY, Double VALUE) {
        mPref.edit().putLong(KEY, Double.doubleToRawLongBits(VALUE)).apply();
    }
    public Double getDouble(String KEY, Double DEFAULT){
        return Double.longBitsToDouble(mPref.getLong(KEY, Double.doubleToLongBits(DEFAULT)));
    }

    public Boolean getBoolean(String KEY, Boolean DEFAULT) {
        return mPref.getBoolean(KEY, DEFAULT);
    }

    public void putBoolean(String KEY, Boolean VALUE) {
        mPref.edit().putBoolean(KEY, VALUE).apply();
    }

}
