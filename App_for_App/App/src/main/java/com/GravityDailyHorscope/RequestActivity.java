package com.GravityDailyHorscope;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.paypal.android.sdk.payments.PayPalService;

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


public class RequestActivity extends AppCompatActivity {

    @InjectView(R.id.request_name)
    EditText _user;
    @InjectView(R.id.request_no)
    EditText _no;
    @InjectView(R.id.request_place)
    EditText _place;
    @InjectView(R.id.request_query)
    EditText _query;
    @InjectView(R.id.submitquery)
    Button _submit;
    CheckBox walletbalance;
    public Spinner sign;

    public GlobalClass globalVariable;
    // note that these credentials will differ between live & sandbox
    // environments.


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        walletbalance=(CheckBox) findViewById(R.id.walletbalance);

       globalVariable = (GlobalClass) getApplicationContext();
        Intent intent = new Intent(this, PayPalService.class);
        ButterKnife.inject(this);

        sign = (Spinner) findViewById(R.id.request_sign);

        _submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // signup();
                // new Test().execute(_user.getText().toString(), _no.getText().toString(), _place.getText().toString()
                //  , _query.getText().toString(), sign.getSelectedItem().toString());
                String name = _user.getText().toString();
                String no = _no.getText().toString();
                String place = _place.getText().toString();
                String query = _query.getText().toString();
                String sign1 = sign.getSelectedItem().toString();

                if (name.length() < 4) {
                    _user.setError("valid name");
                } else {
                    if (no.length() != 10) {
                        _no.setError("enter a valid phone no.");
                    } else {
                        if (place.isEmpty()) {
                            _place.setError("place is empty");
                        } else {
                            new Test().execute(_user.getText().toString(), globalVariable.getEmail(), _no.getText().toString(), _place.getText().toString()
                                    , _query.getText().toString(), GetCountryZipCode(), sign.getSelectedItem().toString(), "1");


                        }
                    }
                }
            }
        });

    }

    public String GetCountryZipCode(){
        String CountryID="";
        String CountryZipCode="";

        TelephonyManager manager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        //getNetworkCountryIso
        CountryID= manager.getSimCountryIso().toUpperCase();
        String[] rl=this.getResources().getStringArray(R.array.CountryCodes);
        for(int i=0;i<rl.length;i++){
            String[] g=rl[i].split(",");
            if(g[1].trim().equals(CountryID.trim())){
                CountryZipCode=g[0];
                break;
            }
        }
        return CountryZipCode;
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
                post = new HttpPost("http://astro360horoscope.com/backend/api/questionforume.php");
                nameValuePair = new ArrayList<NameValuePair>();
                mData = new HashMap<String,String>();
                mData.put("username", Params[0]);
                mData.put("email", Params[1]);
                mData.put("contact", Params[2]);
                mData.put("placeofbirth", Params[3]);
                mData.put("query", Params[4]);
                mData.put("country", Params[5]);
                mData.put("sign", Params[6]);
                mData.put("ask", Params[7]);


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



