package com.GravityDailyHorscope;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by pratiksha goyal on 1/23/2016.
 */
public class Introductory_5 extends AppCompatActivity {

int position=0;
    private ViewPager _mViewPager;
    private ViewPagerAdapter _adapter;
    private View _btn1, _btn2,_btn3,_btn4,_btn5,done1;
    Button btnSignUp;
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);
        setUpView();
        setTab();

        btnSignUp = (Button) findViewById(R.id.skip);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intentSignUP = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(intentSignUP);
            }
        });

    }



    private void setUpView() {
        _mViewPager = (ViewPager) findViewById(R.id.viewPager);
        _adapter = new ViewPagerAdapter(getApplicationContext(), getSupportFragmentManager());
        _mViewPager.setAdapter(_adapter);
        _mViewPager.setCurrentItem(0);
        initButton();
    }

    private void setTab() {
        _mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int position) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                btnAction(position);
            }

        });

    }


    private void btnAction(int action) {
        switch (action) {

            case 0:
                _btn1.getBackground().setColorFilter(Color.parseColor("#79CDCD"), PorterDuff.Mode.DARKEN);
                _btn2.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn3.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn4.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn5.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                               break;

            case 1:
                _btn1.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn2.getBackground().setColorFilter(Color.parseColor("#79CDCD"), PorterDuff.Mode.DARKEN);
                _btn3.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn4.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn5.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);

                break;
            case 2:
                _btn1.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn2.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn3.getBackground().setColorFilter(Color.parseColor("#79CDCD"), PorterDuff.Mode.DARKEN);
                _btn4.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn5.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                break;
            case 3:
                _btn1.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn2.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn3.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn4.getBackground().setColorFilter(Color.parseColor("#79CDCD"), PorterDuff.Mode.DARKEN);
                _btn5.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                break;
            case 4:
                _btn1.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn2.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn3.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn4.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.DARKEN);
                _btn5.getBackground().setColorFilter(Color.parseColor("#79CDCD"), PorterDuff.Mode.DARKEN);
               break;


        }

    }

    private void initButton() {
        _btn1 = (View) findViewById(R.id.btn1);
        _btn2 = (View) findViewById(R.id.btn2);
        _btn3 = (View) findViewById(R.id.btn3);
        _btn4 = (View) findViewById(R.id.btn4);
        _btn5 = (View) findViewById(R.id.btn5);




    }


    protected void onDestroy() {
        super.onDestroy();
        // Close The Database

    }
}