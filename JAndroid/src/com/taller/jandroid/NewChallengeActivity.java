package com.taller.jandroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NewChallengeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_challenge);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_challenge, menu);
        return true;
    }
}
