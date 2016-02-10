package com.GravityDailyHorscope;



import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Monthly extends AppCompatActivity {

    ImageButton share1;
    TextView content1;
    String res;
    String sign;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predictions);
        Intent intent = getIntent();
        Bundle intentBundle = intent.getExtras();
        sign = intentBundle.getString("SIGN");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new Test().execute(intentBundle.getString("sign"),"prediction");
        content1.setText(res);







        share1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent shareintent = new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                shareintent.putExtra(Intent.EXTRA_SUBJECT, "Predictions");
                shareintent.putExtra(Intent.EXTRA_TEXT,"www.gmail.com");
                shareintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(Intent.createChooser(shareintent,"Share via"));
            }
        });
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
                    new Test().execute(intentBundle.getString("sign"),"prediction");
                    content1.setText(res);

                }
                if(tab.getPosition()==1)
                {
                    Intent intent = getIntent();
                    Bundle intentBundle = intent.getExtras();
                    new Test().execute(intentBundle.getString("sign"),"love");
                    content1.setText(res);


                }
                if(tab.getPosition()==2)
                {
                    Intent intent = getIntent();
                    Bundle intentBundle = intent.getExtras();
                    new Test().execute(intentBundle.getString("sign"),"health");
                    content1.setText(res);


                }
                if(tab.getPosition()==3)
                {
                    Intent intent = getIntent();
                    Bundle intentBundle = intent.getExtras();
                    new Test().execute(intentBundle.getString("sign"),"work");
                    content1.setText(res);


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
        new android.app.AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", null).show();
    }
    private class Test extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
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
                post = new HttpPost("http://astro360horoscope.com/backend/api/monthly.php");
                nameValuePair = new ArrayList<NameValuePair>();
                mData = new HashMap<String,String>();
                mData.put("sign", Params[0]);
                mData.put("category", Params[1]);


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
            super.onPostExecute(response);
            AlertDialog.Builder builder1 = new AlertDialog.Builder(Monthly.this);
            builder1.setMessage(response);
            builder1.setCancelable(false);
            builder1.setNeutralButton(android.R.string.ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();





        }
    }
}







