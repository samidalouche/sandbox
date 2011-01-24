package com.lifepulz.client.android;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.lifepulz.R;

public class SettingsActivity extends PreferenceActivity {
    public static final String PREF_MOOD = "PREF_MOOD";
    
    public static final String PREF_LOGIN = "PREF_LOGIN";
    public static final String PREF_PASSWORD = "PREF_PASSWORD";
    public static final String PREF_GATEWAY_URL = "PREF_GATEWAY_URL";
    public static final String PREF_ALIAS = "PREF_ALIAS";

    @Override
    public void onCreate(Bundle icicle) {
      super.onCreate(icicle);
      addPreferencesFromResource(R.xml.userpreferences);
    }  
    
}
