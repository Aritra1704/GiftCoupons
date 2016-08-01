package com.giftcards.giftcoupons.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.giftcards.giftcoupons.R;

/**
 * Created by Aritra on 01-08-2016.
 */
public class LoginActivity extends BaseActivity {
    private View llLoginActivity;
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    public void initialize() {
        llLoginActivity =	baseInflater.inflate(R.layout.activity_login,null);
        llBody.addView(llLoginActivity, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        llToolbarLayout.setVisibility(View.GONE);

    }
}
