package com.lifepulz.client.android;

import android.app.Activity;

public class LifepulzActivity extends Activity {

    protected Lifepulz getLifepulz()    {
        return (Lifepulz)getApplication();
    }
}
