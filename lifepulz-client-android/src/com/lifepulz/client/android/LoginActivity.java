package com.lifepulz.client.android;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lifepulz.R;
import com.lifepulz.client.android.gateway.Gateway;
import com.lifepulz.client.android.gateway.GatewayContext;
import com.lifepulz.client.android.gateway.GatewayException;
import com.lifepulz.client.android.gateway.model.Profile;

public class LoginActivity extends LifepulzActivity {
    SharedPreferences preferences;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        final EditText login = (EditText) findViewById(R.id.login_value);
        final EditText password = (EditText) findViewById(R.id.password_value);
        
        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                
                // Perform authentication 
                String gatewayUrl = preferences.getString(SettingsActivity.PREF_GATEWAY_URL, "http://www.lifepulz.com");
                Gateway gateway = new Gateway(gatewayUrl);
                try {
                    GatewayContext context = gateway.establishContext("0cd9ff51-edab-40a6-8090-7669646d1f94",  login.getText().toString(), password.getText().toString());
                    Log.d("LoginActivity", "Context successfuly established with Lifepulz backend");
                    
                    // Keep a reference to the current context in our application object
                    getLifepulz().setGatewayContext(context);
                    Log.d("LoginActivity", "Context is stored in application.");

                    // Retrieve the Lifepulz profile for the current member
                    Profile profile = null;
                    
                    try {
                        profile = gateway.loadProfile(context);
                        getLifepulz().setMemberProfile(profile);
                    }
                    catch(GatewayException ge)  {
                        // Just continue without a profile for now. 
                        Log.w("LoginActivity", "New member account. No configured profile.");
                    }
                    
                    getLifepulz().setGateway(gateway);
                    
                    // Update preferences to avoid future authentication
                    Editor editor = preferences.edit();
                    editor.putString(SettingsActivity.PREF_LOGIN, login.getText().toString());
                    editor.putString(SettingsActivity.PREF_PASSWORD, password.getText().toString());
                    
                    // If we have a profile, make sure to update the alias preference
                    if(profile != null)
                        editor.putString(SettingsActivity.PREF_ALIAS, profile.getAlias());
                    
                    editor.commit();
                    
                    setResult(Activity.RESULT_OK);
                    finish();
                } catch (GatewayException e) {
                    Log.e("Lifepulz", "Unable to authenticate.");
                }
                                
            }
        });
    }
}
