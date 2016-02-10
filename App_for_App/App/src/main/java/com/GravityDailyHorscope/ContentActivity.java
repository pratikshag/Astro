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
import android.webkit.WebView;
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



public class ContentActivity extends ActionBarActivity {
    ImageButton back1;
    TextView show1;
    WebView content1;
    String email;
    String res;
    String result = null;
    public ProgressDialog pd;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        Intent intent = getIntent();
        Bundle intentBundle = intent.getExtras();
        String terms = intentBundle.getString("TERMS");
        show1 = (TextView) findViewById(R.id.show2);
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Loading. . .");
        pd.show();
        content1 = (WebView) findViewById(R.id.content4);
        show1.setText(terms);
        back1 = (ImageButton) findViewById(R.id.back);
        if( isNetworkConnected()){
            new Test().execute(intentBundle.getString("TERMS"));}
        else {
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Connectivity")
                    .setMessage("No Network Connection")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setNegativeButton("TryAgain", null).show();
        }


        back1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (globalVariable.getEmail()  == null) {
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
    private class Test extends  AsyncTask<String, String, String> {

        protected void onPreExecute() {
            pd.show();
            super.onPreExecute();

            //here you can some progress dialog or some view
        }


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
                post = new HttpPost("http://astro360horoscope.com/backend/api/static.php");
                nameValuePair = new ArrayList<NameValuePair>();
                mData = new HashMap<>();
                //here you can provide the parameter list to the api like for login username and password
                mData.put("category", Params[0]);

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





        }


    }
}