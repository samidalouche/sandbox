package com.lifepulz.client.android;

import static com.lifepulz.client.android.utils.AdapterUtils.selectedValue;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.lifepulz.R;
import com.lifepulz.client.android.gateway.Gateway;
import com.lifepulz.client.android.gateway.GatewayContext;
import com.lifepulz.client.android.gateway.GatewayException;
import com.lifepulz.client.android.gateway.model.Interest;
import com.lifepulz.client.android.gateway.model.Presence;
import com.lifepulz.client.android.location.LocationService;

public class PresenceActivity extends LifepulzActivity {    
    SharedPreferences preferences;
    
    private static final String TAG = "Presence";
    public static final String PREF_MOOD = "PREF_MOOD";

    public static final String PREF_LOGIN = "PREF_LOGIN";
    public static final String PREF_PASSWORD = "PREF_PASSWORD";
    private static int REQUEST_PICK_INTEREST = 0;

    Spinner statusSpinner;
    EditText taglineEditText;
    Spinner languageSpinner;
    Spinner durationSpinner;
    Spinner timeframeSpinner;
    Spinner securitySpinner;
    EditText interestEditText;
    Button okButton;

    LocationManager locationManager;
    String interestSubject = "";
    String interestAction = "";
    String interestMood = "";        

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.presence);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setSpeedRequired(false);

        initStatusSpinner();
        initTaglineEditText();
        initLanguageSpinner();
        initDurationSpinner();
        initTimeframeSpinner();
        initSecuritySpinner();
        initInterestEditText();
        initOkButton();

    }




    private void initTaglineEditText() {
        taglineEditText = (EditText) findViewById(R.id.presence_tagline);
    }

    private void initStatusSpinner() {
        statusSpinner = (Spinner) findViewById(R.id.presence_status_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.presence_status_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

    }


    private void initLanguageSpinner() {
        languageSpinner = (Spinner) findViewById(R.id.presence_language_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.presence_language_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

    }

    private void initInterestEditText() {
        interestEditText = (EditText) findViewById(R.id.presence_interest);
        interestEditText.setOnFocusChangeListener(new OnFocusChangeListener() {

            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    startActivityForResult(new Intent(PresenceActivity.this, InterestActivity.class), REQUEST_PICK_INTEREST);
                }

            }
        });

    }


    private void initDurationSpinner() {
        durationSpinner = (Spinner) findViewById(R.id.presence_duration_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.presence_duration_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setAdapter(adapter);

    }

    private void initTimeframeSpinner() {
        timeframeSpinner = (Spinner) findViewById(R.id.presence_timeframe_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.presence_timeframe_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeframeSpinner.setAdapter(adapter);

    }

    private void initSecuritySpinner() {
        securitySpinner = (Spinner) findViewById(R.id.presence_security_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.presence_security_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        securitySpinner.setAdapter(adapter);

    }

    private void initOkButton() {
        okButton = (Button) findViewById(R.id.presence_ok);
        okButton.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                updatePresence();
            }
        });

    }

    private void updatePresence() {

        final CharSequence status = selectedValue(this.getResources().getTextArray(R.array.presence_status_values), statusSpinner.getSelectedItemId());
        final CharSequence tagline = taglineEditText.getText().toString();
        final CharSequence language = selectedValue(this.getResources().getTextArray(R.array.presence_language_values), languageSpinner.getSelectedItemId());
        final int duration = selectedValue(this.getResources().getIntArray(R.array.presence_duration_values), durationSpinner.getSelectedItemId());
        final int timeframe = selectedValue(this.getResources().getIntArray(R.array.presence_timeframe_values), timeframeSpinner.getSelectedItemId());
        final CharSequence security = selectedValue(this.getResources().getTextArray(R.array.presence_security_values), securitySpinner.getSelectedItemId());

        Log.i(TAG, "Requesting location... (waiting)");
        final ProgressDialog dialog = ProgressDialog.show(this, "", "Retrieving location...", true);
        final LocationManager locationManager = LocationService.getLocationManager(this);
        locationManager.requestLocationUpdates(LocationService.getLocationProvider(locationManager), 0, 0, new LocationListener() {
            
            public void onStatusChanged(String provider, int status, Bundle extras) {
                
            }
            
            public void onProviderEnabled(String provider) {
                
            }
            
            public void onProviderDisabled(String provider) {
                
            }
            
            public void onLocationChanged(Location location) {
                Log.i(TAG, 
                        "Status: " + status
                        + ", Tagline: " + tagline 
                        + ", Language: " + language 
                        + ", Duration: " + duration 
                        + ", Timeframe: "+timeframe 
                        + ", Security: " + security
                        + ", Location: (" + location.getLatitude() + ","  + location.getLongitude() + ")"
                        + " Interest: ( " + interestSubject + ", " + interestAction + " )");
                Log.i(TAG, "Finishing activity");
                locationManager.removeUpdates(this);

				// Create the presence instance
            Presence presence = new Presence();
            presence.setStatus(status.toString());
            presence.setDuration(duration);
            presence.setTagline(tagline.toString());
            presence.setTimeframe(timeframe);
            presence.setLocation(new com.lifepulz.client.android.gateway.model.Location(location));
            
            // Handle presence interest
            Interest interest = new Interest();
            interest.setSubject(interestSubject.toString());
            interest.setAction(interestAction.toString());
            interest.setMood(interestMood.toString());
            presence.setInterest(interest);
            
            // Send presence data to Lifepulz
            Gateway gateway = getLifepulz().getGateway();
            GatewayContext context = getLifepulz().getGatewayContext();
            try {
                gateway.updatePresence(context, presence);
            } catch (GatewayException e) {
                Log.e(PresenceActivity.class.getName(), "Error updating presence", e);
            }

                dialog.dismiss();
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if(requestCode == REQUEST_PICK_INTEREST) {
            if(resultCode == RESULT_OK) {
                interestSubject = data.getStringExtra("subject");
                interestAction = data.getStringExtra("action");
                updateUi();
            }
        }
    }




    private void updateUi() {
        interestEditText.setText(interestSubject + " - " + interestAction);
    }


}
