package com.giftcards.giftcoupons.activity;

import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.internal.util.Predicate;
import com.giftcards.giftcoupons.R;
import com.giftcards.giftcoupons.common.AppPreference;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Aritra on 01-08-2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public LayoutInflater baseInflater;
    public LinearLayout llBody, llToolbarLayout;
    public FrameLayout llTitle;
    public TextView tvTitle;
    public EditText edtSearch;
    public ImageView ivLeft,ivRight;
    public Typeface tfAdventProMedium, tfAdventProRegular,tfAbelRegular;

    public AppPreference preference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_base);

        initialiseBaseControls();

        bindBaseControls();

        initialize();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(tfAdventProRegular == null || tfAdventProMedium == null)
            createTypeFace();

        registerBroadcast();
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterBroadcast();
    }

    private void bindBaseControls(){
        if(preference == null)
            preference = new AppPreference(this);
    }

    public void registerBroadcast(){
        try {
//            if(BaseActivity.this instanceof FarmSearchActivity)
//                registerReceiver(br_CLOSE_FARMSEARCH, new IntentFilter(AppConstants.ACTION_CLOSE_FARMSEARCH));
//
//            if(BaseActivity.this instanceof TourDetailActivity)
//                LocalBroadcastManager.getInstance(BaseActivity.this).registerReceiver(br_UPDATE, new IntentFilter(AppConstants.ACTION_TOUR_UPDATE));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void unregisterBroadcast(){
        try {
//            if(BaseActivity.this instanceof FarmSearchActivity)
//                unregisterReceiver(br_CLOSE_FARMSEARCH);
//
//            if(BaseActivity.this instanceof TourDetailActivity)
//                LocalBroadcastManager.getInstance(BaseActivity.this).unregisterReceiver(br_UPDATE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static <T> Collection<T> filter(Collection<T> col, Predicate<T> predicate) {

        Collection<T> result = new ArrayList<T>();
        if(col!=null)
        {
            for (T element : col) {
                if (predicate.apply(element)) {
                    result.add(element);
                }
            }
        }
        return result;
    }

    public static ViewGroup getParentView(View v) {
        ViewGroup vg = null;

        if(v != null)
            vg = (ViewGroup) v.getRootView();

        return vg;
    }

    public static void applyTypeface(ViewGroup v, Typeface f, int style) {
        if(v != null) {
            int vgCount = v.getChildCount();
            for(int i=0;i<vgCount;i++) {
                if(v.getChildAt(i) == null) continue;
                if(v.getChildAt(i) instanceof ViewGroup)
                    applyTypeface((ViewGroup)v.getChildAt(i), f, style);
                else {
                    View view = v.getChildAt(i);
                    if(view instanceof TextView)
                        ((TextView)(view)).setTypeface(f, style);
                    else if(view instanceof EditText)
                        ((EditText)(view)).setTypeface(f, style);
                    else if(view instanceof Button)
                        ((Button)(view)).setTypeface(f, style);
                }
            }
        }
    }

    public void hideKeyBoard(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public abstract void initialize();

    private void initialiseBaseControls(){
        baseInflater            = 	this.getLayoutInflater();
        llBody                  =   (LinearLayout) findViewById(R.id.llBody);

        ivLeft                  =  (ImageView) findViewById(R.id.ivLeft);
        ivRight                 =  (ImageView) findViewById(R.id.ivRight);

        tvTitle                 =  (TextView) findViewById(R.id.tvTitle);

        edtSearch               =  (EditText) findViewById(R.id.edtSearch);

        llToolbarLayout         = (LinearLayout) findViewById(R.id.llToolbarLayout);
        llTitle                 = (FrameLayout) findViewById(R.id.llTitle);

        llToolbarLayout.setPadding(0,getStatusBarHeight() + (int)getResources().getDimension(R.dimen.margin_10),0,0);
        createTypeFace();
    }

    // A method to find height of the status bar
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void createTypeFace(){
        tfAdventProMedium   = Typeface.createFromAsset(this.getAssets(),"fonts/advent-pro.medium.ttf");
        tfAdventProRegular  = Typeface.createFromAsset(this.getAssets(),"fonts/advent-pro.regular.ttf");
        tfAbelRegular       = Typeface.createFromAsset(this.getAssets(),"fonts/Abel-Regular.ttf");
    }
}
