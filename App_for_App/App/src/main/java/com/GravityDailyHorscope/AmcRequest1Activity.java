package com.GravityDailyHorscope;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * Created by Aaron Softech on 1/15/2016.
 */
public class AmcRequest1Activity extends ActionBarActivity {
    ImageButton back1;
    TextView show1;

    String name, timeofbirth, placeofbirth;

    @InjectView(R.id.names)
    EditText _names;
    @InjectView(R.id.placeofbirth)
    EditText _placeofbirth;
    @InjectView(R.id.timeofbirth)
    EditText _timeofbirth;
    @InjectView(R.id.request1)
    Button _request3;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afcl);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        ButterKnife.inject(this);
        _request3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // signup();
                name = _names.getText().toString();
                timeofbirth = _timeofbirth.getText().toString();
                placeofbirth = _placeofbirth.getText().toString();
                if (name.isEmpty() || name.length() < 4) {
                    _names.setError("at least 4 characters");
                } else {
                    if (placeofbirth.isEmpty()) {
                        _placeofbirth.setError("fill all details");
                    } else {
                        if (timeofbirth.contains("[a-zA-Z]+") == true) {
                            _timeofbirth.setError("Invalid format");
                        } else {
                            new Test().execute(globalVariable.getEmail(), _names.getText().toString(), _placeofbirth.getText().toString(), _timeofbirth.getText().toString()
                                    , "amc", "1");


                        }
                    }
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
                post = new HttpPost("http://astro360horoscope.com/backend/api/form_amc_afc.php");
                nameValuePair = new ArrayList<NameValuePair>();
                mData = new HashMap<String, String>();
                mData.put("email", Params[0]);
                mData.put("username", Params[1]);
                mData.put("placeofbirth", Params[2]);
                mData.put("timeofbirth", Params[3]);
                mData.put("category", Params[4]);
                mData.put("ask", Params[5]);
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
            if (response.equals("1")) {
                Intent intentcourse = new Intent(getApplicationContext(), PayMentActivity.class);
                intentcourse.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentcourse);
            }
        }
    }





}