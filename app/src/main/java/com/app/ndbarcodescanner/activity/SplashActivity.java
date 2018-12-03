package com.app.ndbarcodescanner.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.ndbarcodescanner.R;

public class SplashActivity extends Activity {
    private static final long TIMEOUT = 1000;
    private Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spl);
        holdScreen();
    }
    private void holdScreen() {
        mWaitHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                //The following code will execute after the 5 seconds.
                try {
                    //Go to next page i.e, start the next activity.
                    Intent intent = new Intent(getApplicationContext(),ScannerActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, TIMEOUT);

    }
}
