package com.lifepulz.client.android;

import static com.lifepulz.client.android.utils.AdapterUtils.selectedValue;

import com.lifepulz.R;
import com.lifepulz.client.android.location.LocationService;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class InterestActivity extends Activity {
    EditText subjectEditText;
    EditText actionEditText;
    Button okButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interest);
       initEditTexts();
       initOkButton();
    }
    
    private void initEditTexts() {
        subjectEditText = (EditText) findViewById(R.id.interest_subject);
        actionEditText = (EditText) findViewById(R.id.interest_action);
    }
    
    private void initOkButton() {
        okButton = (Button) findViewById(R.id.interest_ok);
        okButton.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("subject", subjectEditText.getText().toString());
                i.putExtra("action", actionEditText.getText().toString());
                setResult(RESULT_OK, i);
                finish();
            }
        });
        
    }
    
}
