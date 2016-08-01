package com.giftcards.giftcoupons.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;

import com.giftcards.giftcoupons.R;

/**
 * Created by Aritra on 01-08-2016.
 */
public class SplashActivity extends BaseActivity {

    private View llSplashActivity;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    public void initialize() {
        llSplashActivity =	baseInflater.inflate(R.layout.activity_splash,null);
        llBody.addView(llSplashActivity, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        llToolbarLayout.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
