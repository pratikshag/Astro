package com.GravityDailyHorscope;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by pratiksha goyal on 1/26/2016.
 */
public class MoodActivity extends Activity {
    String myJSON;
    ListView list;
    ImageButton back1;
    TextView show1;
    JSONArray peoples = null;
    private static final String TAG_HOURS = "hours";
    private static final String TAG_INFO = "information";
    ArrayList<HashMap<String, String>> personList;
   public ProgressDialog pd;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        show1 = (TextView) findViewById(R.id.show2);
        list=(ListView) findViewById(R.id.list2);
        show1.setText("Your Mood");
        back1 = (ImageButton) findViewById(R.id.back);
        personList = new ArrayList<HashMap<String, String>>();
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Loading. . .");
        pd.show();
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
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
        Intent intent = getIntent();
        Bundle intentBundle = intent.getExtras();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if( isNetworkConnected()){
            new GetDataJSON().execute(intentBundle.getString("sign"),sdf.format(new Date()));}
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
    private class GetDataJSON extends AsyncTask<String, String, String> {
        String res = "";

        protected void onPreExecute() {
            pd.show();
            super.onPreExecute();

            //here you can some progress dialog or some view
        }


        protected String doInBackground(String... Params) {

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
                post = new HttpPost("http://astro360horoscope.com/backend/api/mood.php");
                nameValuePair = new ArrayList<NameValuePair>();
                mData = new HashMap<>();
                //here you can provide the parameter list to the api like for login username and password
                mData.put("sign", Params[0]);
                mData.put("date", Params[1]);

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
            myJSON = response;

            try {
                JSONArray jsonarray = new JSONArray(response);



                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject c = jsonarray.getJSONObject(i);
                    String  hours = c.getString("hours");
                    String info = c.getString("information");


                    HashMap<String, String> persons = new HashMap<String, String>();

                    persons.put(TAG_HOURS, hours);
                    persons.put(TAG_INFO, info);


                    personList.add(persons);
                }

                ListAdapter adapter = new SimpleAdapter(
                        MoodActivity.this, personList, R.layout.list_item1,
                        new String[]{TAG_HOURS, TAG_INFO},
                        new int[]{R.id.status1_time, R.id.status1_status
                        }
                );

                list.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(response);
            pd.dismiss();


        }
    }
}