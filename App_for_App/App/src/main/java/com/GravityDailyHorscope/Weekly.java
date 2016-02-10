package com.GravityDailyHorscope;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


public class Weekly extends AppCompatActivity {

    ImageButton share1;
    Button mood1;
    WebView content1;
    ImageButton back1;
    TextView show1;
    String res;
    String sign;
    public ProgressDialog pd;
    public GlobalClass globalVariable;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictions);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        globalVariable = (GlobalClass) getApplicationContext();
        Intent intent = getIntent();
        Bundle intentBundle = intent.getExtras();
        sign = intentBundle.getString("SIGN");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        content1 = (WebView) findViewById(R.id.content);
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Loading. . .");
        pd.show();
        show1 = (TextView) findViewById(R.id.show2);
        show1.setText("Weekly Predicitons");
        back1 = (ImageButton) findViewById(R.id.back);
        mood1 = (Button) findViewById(R.id.mood);
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
        mood1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intenthome = new Intent(getApplicationContext(), MoodActivity.class);
                intenthome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intenthome);

            }
        });
        if( isNetworkConnected()==false){
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Connectivity")
                    .setMessage("No Network Connection")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setNegativeButton("TryAgain", null).show();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        new Test().execute(intentBundle.getString("sign"), "prediction", sdf.format(new Date()));

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#3d3d3d'>Prediction</font>"));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Predictions"));
        tabLayout.addTab(tabLayout.newTab().setText("work"));
        tabLayout.addTab(tabLayout.newTab().setText("health"));
        tabLayout.addTab(tabLayout.newTab().setText("Love"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0)
                {
                    Intent intent = getIntent();
                    Bundle intentBundle = intent.getExtras();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    new Test().execute(intentBundle.getString("sign"), "prediction", sdf.format(new Date()));



                }
                if(tab.getPosition()==1)
                {
                    Intent intent = getIntent();
                    Bundle intentBundle = intent.getExtras();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    new Test().execute(intentBundle.getString("sign"), "work",sdf.format(new Date()));



                }
                if(tab.getPosition()==2)
                {
                    Intent intent = getIntent();
                    Bundle intentBundle = intent.getExtras();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    new Test().execute(intentBundle.getString("sign"), "health", sdf.format(new Date()));



                }
                if(tab.getPosition()==3)
                {
                    Intent intent = getIntent();
                    Bundle intentBundle = intent.getExtras();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    new Test().execute(intentBundle.getString("sign"), "love", sdf.format(new Date()));



                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_prediction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed() {
        if (globalVariable.getEmail() == null) {
            Intent intenthome = new Intent(getApplicationContext(), UserActivity.class);
            intenthome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intenthome);
        } else {
            Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
            intenthome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intenthome);
        } }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
    private class Test extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            pd.show();
            super.onPreExecute();

            //here you can some progress dialog or some view
        }

        @Override
        protected String doInBackground(String... Params) {
            String res = "";
            try {
                byte[] result = null;
                String str = "";
                HttpClient client;
                HttpPost post;
                ArrayList<NameValuePair> nameValuePair;
                HashMap<String, String> mData;
                Iterator<String> it;
                HttpResponse response;
                StatusLine statusLine;

                //here is url api call url
                post = new HttpPost("http://astro360horoscope.com/backend/api/weekly.php");
                nameValuePair = new ArrayList<NameValuePair>();
                mData = new HashMap<String,String>();
                mData.put("sign", Params[0]);
                mData.put("category", Params[1]);
                mData.put("date", Params[2]);

                //for now nothing is there

                it = mData.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    nameValuePair.add(new BasicNameValuePair(key, mData.get(key)));
                }
                post.setEntity(new UrlEncodedFormEntity(nameValuePair, "utf-8"));
                client = new DefaultHttpClient();
                response = client.execute(post);
                statusLine = response.getStatusLine();
                result = EntityUtils.toByteArray(response.getEntity());
                str = new String(result, "utf-8");
                if (statusLine.getStatusCode() == HttpURLConnection.HTTP_OK) {
                    //here we get the response if all is correct
                    res = str;
                } else {
                    res = str;
                    return res;
                }

            } catch (Exception e1) {
                res = "error:" + e1.getMessage().toString();
                e1.printStackTrace();
            }
            return res;
        }

        protected void onPostExecute(String response) {
            pd.dismiss();
            String text = "<html><body>"
                    + "<p align=\"justify\">"
                    + response
                    + "</p> "
                    + "</body></html>";
            content1.loadData(text, "text/html", "utf-8");
            super.onPostExecute(response);






        }
    }
}







