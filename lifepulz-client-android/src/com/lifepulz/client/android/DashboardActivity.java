package com.lifepulz.client.android;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.lifepulz.R;
import com.lifepulz.client.android.location.LocationService;

public class DashboardActivity extends Activity {
    private static final int MENU_PRESENCE = Menu.FIRST;
    private static final int MENU_SELECTORS = Menu.FIRST+1;
    private static final int MENU_PROFILE = Menu.FIRST+2;
    private static final int MENU_SETTINGS = Menu.FIRST+3;
    private static final int DIALOG_PULZ_DETAILS = 1;
    private static final int SHOW_LOGIN = 1;
    
    Context context;
    ListView pulzOverviewListView;
    Pulz selectedPulz;
    ArrayList<Pulz> pulzes;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        context = getApplicationContext();
        
        askForCredenialsWhenLoginOrPasswordMissing();
        
    }

    private void fetchPulzes() {
        // TODO: connect to lifepulz
        Location newYork = new Location("FakeGPS");
        newYork.setLatitude(40.715017);
        newYork.setLongitude(-74.006141);
        pulzes = new ArrayList<Pulz>();
        
        pulzes.add(new Pulz(new Date(), "Sarah is heading to The Museum of Modern Art", newYork));
        pulzes.add(new Pulz(new Date(), "Max is having a discussion about Skate Boarding", newYork));
        pulzes.add(new Pulz(new Date(), "Drew is feeling like having a philosophical discussion at Starbucks", newYork));
        
    }

    private void initPulzOverviewListView() {
        pulzOverviewListView = (ListView) this.findViewById(R.id.pulzOverviewListView);
        pulzOverviewListView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
                selectedPulz = pulzes.get(index);
                showDialog(DIALOG_PULZ_DETAILS);
            }
        });// ok
        pulzOverviewListView.setAdapter(new ArrayAdapter<Pulz>(context, android.R.layout.simple_list_item_1, pulzes));
    }

    private void askForCredenialsWhenLoginOrPasswordMissing() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        startActivityForResult(new Intent(context, LoginActivity.class), SHOW_LOGIN);
//        if(TextUtils.isEmpty(prefs.getString(SettingsActivity.PREF_LOGIN, ""))
//                || TextUtils.isEmpty(prefs.getString(SettingsActivity.PREF_LOGIN, ""))) {
//            startActivityForResult(new Intent(context, LoginActivity.class), SHOW_LOGIN);
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE, MENU_PRESENCE, Menu.NONE, R.string.dashboard_menu_presence);
        menu.add(Menu.NONE, MENU_SELECTORS, Menu.NONE, R.string.dashboard_menu_selectors);
        menu.add(Menu.NONE, MENU_PROFILE, Menu.NONE, R.string.dashboard_menu_profile);
        menu.add(Menu.NONE, MENU_SETTINGS, Menu.NONE, R.string.dashboard_menu_settings);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        
        switch(item.getItemId()) {
        case MENU_PRESENCE:
            startActivity(new Intent(this,PresenceActivity.class));
            return true;
        case MENU_SELECTORS:
            startActivity(new Intent(this,SelectorsActivity.class));
            return true;
        case MENU_PROFILE:
            startActivity(new Intent(this,ProfileActivity.class));
            return true;
        case MENU_SETTINGS:
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }
        
        return false;
    }// ok

    @Override
    protected Dialog onCreateDialog(int id) {
        switch(id) {
        case DIALOG_PULZ_DETAILS:
            AlertDialog.Builder pulzDetailsDialog = new AlertDialog.Builder(this);
            pulzDetailsDialog.setTitle("Pulz Details");
            pulzDetailsDialog.setView(LayoutInflater.from(this).inflate(R.layout.pulz_details, null));
            return pulzDetailsDialog.create();
        }
        return null;
    }
    
    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch(id) {
        case DIALOG_PULZ_DETAILS:
            AlertDialog alertDialog = (AlertDialog) dialog;
            alertDialog.setTitle("Pulz details");
            TextView pulzDetailsTextView = (TextView) alertDialog.findViewById(R.id.pulzDetailsTextView);
            pulzDetailsTextView.setText(selectedPulz.toString());
            break;
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        switch(requestCode) {
        case SHOW_LOGIN:
            if(resultCode == Activity.RESULT_OK) {

                fetchPulzes();
                initPulzOverviewListView();
                for(int i = 0 ; i < pulzes.size() ; i++) {
                    Toast toast = Toast.makeText(context, pulzes.get(i).getDetails(), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
            break;
        }
    }

}