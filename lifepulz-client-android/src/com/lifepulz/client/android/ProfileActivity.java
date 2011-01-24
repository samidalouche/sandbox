package com.lifepulz.client.android;

import static com.lifepulz.client.android.utils.AdapterUtils.selectedValue;

import java.util.Calendar;
import java.util.Date;

import com.lifepulz.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class ProfileActivity extends Activity {
    static final int DATE_DIALOG_ID = 1;

    private static final String TAG = "Profile";
    
    // date and time
    private int year;
    private int month;
    private int day;
    
    EditText birthdateEditText;
    Spinner sexSpinner;
    Spinner sexOrientationSpinner;
    Button okButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
        initBirthdateEditText();
        initSexSpinner();
        initSexOrientationSpinner();
        initOkButton();
        updateDisplay();
    }

    private void updateProfile() {
        CharSequence sex = selectedValue(this.getResources().getTextArray(R.array.profile_sex_values), sexSpinner.getSelectedItemId());
        CharSequence sexOrientation = selectedValue(this.getResources().getTextArray(R.array.profile_sex_orientation_values), sexOrientationSpinner.getSelectedItemId());
        
        Log.i(TAG, "Year: " + year + ", Month: " + month+1 + ", Day: " + day + ", Sex: " + sex + ", SexOrientation: " + sexOrientation);
        
        // TODO: connect to lifepulz
    }
    
    private void initOkButton() {
        okButton = (Button) findViewById(R.id.profile_ok);
        okButton.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                updateProfile();
                finish();
            }

        });
        
    }

    private void initBirthdateEditText() {
        birthdateEditText = (EditText) findViewById(R.id.profile_birthdate);
        birthdateEditText.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                
            }
        });
        initDateValues();
    }

    private void initDateValues() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
    }
    
    private void initSexSpinner() {
        sexSpinner = (Spinner) findViewById(R.id.profile_sex_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.profile_sex_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexSpinner.setAdapter(adapter);
    }

    private void initSexOrientationSpinner() {
        sexOrientationSpinner = (Spinner) findViewById(R.id.profile_sex_orientation_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.profile_sex_orientation_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexOrientationSpinner.setAdapter(adapter);
    }
    
    private void updateDisplay() {
        birthdateEditText.setText(
            new StringBuilder()
                    // Month is 0 based so add 1
                    .append(month + 1).append("-")
                    .append(day).append("-")
                    .append(year).append(" "));
    }
    
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                            dateSetListener,
                            year, month, day);
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(year, month, day);
                break;
        }
    }  
    
    private DatePickerDialog.OnDateSetListener dateSetListener =
        new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear,
                    int dayOfMonth) {
                ProfileActivity.this.year = year;
                ProfileActivity.this.month = monthOfYear;
                ProfileActivity.this.day = dayOfMonth;
                updateDisplay();
            }
        };

}
