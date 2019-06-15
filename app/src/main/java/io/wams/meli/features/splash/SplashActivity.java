package io.wams.meli.features.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.wams.meli.features.country.CountryActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Start home activity
        startActivity(CountryActivity.getStartIntent(this));
        // close splash activity
        finish();
    }
}
