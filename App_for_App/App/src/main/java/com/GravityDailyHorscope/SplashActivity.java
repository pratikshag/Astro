package com.GravityDailyHorscope;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash_activity_layout);
		final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

		new CountDownTimer(2000, 200) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (globalVariable.getEmail() != null) {
					Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
					intenthome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
					startActivity(intenthome);
				}
				else {
					if(globalVariable.getName() != null) {

                        Intent intenthome1 = new Intent(getApplicationContext(),UserActivity.class);
						intenthome1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intenthome1);
					}
					else {
						globalVariable.setName("use");
						Intent intenthome = new Intent(getApplicationContext(), Introductory_5.class);
						intenthome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
						startActivity(intenthome);
					}


				}

				finish();
			}
		}.start();

		//ImageView logoImageView = (ImageView) findViewById(R.id.logo_imageview);
		//logoImageView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER);

	}



}
