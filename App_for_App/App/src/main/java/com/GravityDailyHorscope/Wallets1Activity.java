package com.GravityDailyHorscope;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by pratiksha goyal on 1/26/2016.
 */
public class Wallets1Activity extends ActionBarActivity {

    ImageButton back1;
    TextView show1;
    Button details1,tcapply1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallets1);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        show1 = (TextView) findViewById(R.id.show2);
        show1.setText("My Wallet");
        details1 = (Button) findViewById(R.id.details);
        tcapply1 = (Button) findViewById(R.id.tcapply);
        back1 = (ImageButton) findViewById(R.id.back);
        back1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (globalVariable.getEmail() == null) {
                    Intent intenthome = new Intent(getApplicationContext(), UserActivity.class);
                    intenthome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intenthome);
                } else {
                    Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
                    intenthome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intenthome);
                }
            }
        });
        details1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intenthome1 = new Intent(Wallets1Activity.this, ContentActivity.class);
                intenthome1.putExtra("TERMS", details1.getText());
                intenthome1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intenthome1);
            }
        });

        tcapply1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intenthome3 = new Intent(Wallets1Activity.this, ContentActivity.class);
                intenthome3.putExtra("TERMS", "terms");
                intenthome3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intenthome3);
            }
        });
    }
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", null).show();
    }
}
