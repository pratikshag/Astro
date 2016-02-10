package com.GravityDailyHorscope;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by pratiksha goyal on 1/13/2016.
 */
public class ReferalActivity extends ActionBarActivity {
    ImageButton back1;
    String response;
    public ProgressDialog pd;
    TextView show1,promocode1;
    String res;
    String refercode;
    Button details1,sharecode1,tcapply1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Loading. . .");
        pd.show();
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        refercode=globalVariable.getBalance();
        show1 = (TextView) findViewById(R.id.show2);
        show1.setText("ReferFriend");
        back1 = (ImageButton) findViewById(R.id.back);
        promocode1 = (TextView) findViewById(R.id.promocode);
        details1 = (Button) findViewById(R.id.details);
        sharecode1 = (Button) findViewById(R.id.sharecode);
        tcapply1 = (Button) findViewById(R.id.tcapply);
        if( isNetworkConnected()) {
        }
            else{
                new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Connectivity")
                        .setMessage("No Network Connection")
                        .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setNegativeButton("TryAgain", null).show();

        }
        final ProgressDialog progressDialog = new ProgressDialog(ReferalActivity.this
        );
       while(promocode1.getText()==null)
       {progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show(); }
        new Test().execute(globalVariable.getEmail());

      promocode1.setText(res);


        back1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
                intenthome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intenthome);
            }
        });
        details1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intenthome1 = new Intent(ReferalActivity.this, ContentActivity.class);
                intenthome1.putExtra("TERMS", details1.getText());
                intenthome1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intenthome1);
            }
        });
        sharecode1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent shareintent = new Intent(Intent.ACTION_SEND);
                shareintent.setType("text/plain");
                shareintent.putExtra(Intent.EXTRA_SUBJECT, "Astro360");
                shareintent.putExtra(Intent.EXTRA_TEXT, "Use my referral code to sign up for Astro360 and get benefits worth Rs 20. Download the App https://play.google.com/store/apps/details?id=com.GravityDailyHorscope");
               shareintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(Intent.createChooser(shareintent,"Share via"));
            }
        });
        tcapply1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /// Create Intent for SignUpActivity  abd Start The Activity
                Intent intenthome3 = new Intent(ReferalActivity.this, ContentActivity.class);
                intenthome3.putExtra("TERMS","terms");
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
                post = new HttpPost("http://astro360horoscope.com/backend/api/refer.php");
                nameValuePair = new ArrayList<NameValuePair>();
                mData = new HashMap<String,String>();
                mData.put("email", Params[0]);

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
            promocode1.setText(response);
            super.onPostExecute(response);




        }

    }

}
