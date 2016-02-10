package com.GravityDailyHorscope;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;

public class ForumeAfcActivity extends TabActivity {

    ImageButton back1;
    TextView show1;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forumeafc);
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        show1 = (TextView) findViewById(R.id.show2);
        show1.setText("Astro Financial Chart");
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
        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.getTabWidget().setDividerDrawable(null);
        TabHost.TabSpec firstTabSpec = tabHost.newTabSpec("tid1");
        TabHost.TabSpec secondTabSpec = tabHost.newTabSpec("tid1");
        TabHost.TabSpec thirdTabSpec = tabHost.newTabSpec("tid1");
        firstTabSpec.setIndicator("Inforamtion").setContent(new Intent(this, FirstTabafc.class));
        secondTabSpec.setIndicator("Query Form").setContent(new Intent(this, AfcRequest1Activity.class));
        thirdTabSpec.setIndicator("Status").setContent(new Intent(this, ThirdTabAfc.class));
        tabHost.addTab(firstTabSpec);
        tabHost.addTab(secondTabSpec);
        tabHost.addTab(thirdTabSpec);

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
