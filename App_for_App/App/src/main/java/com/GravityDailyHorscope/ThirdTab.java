package com.GravityDailyHorscope;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ThirdTab extends Activity {

    String myJSON;
    public ProgressDialog pd;
    private static final String TAG_NAME = "user_id";
    private static final String TAG_time = "time";
    private static final String TAG_status = "status";
ListView list;
    JSONArray peoples = null;
    ArrayList<HashMap<String, String>> personList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        pd = new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("Loading. . .");
        pd.show();
        list=(ListView) findViewById(R.id.list1);
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        personList = new ArrayList<HashMap<String, String>>();
        if( isNetworkConnected()){
            new GetDataJSON().execute(globalVariable.getEmail(),"question"); }
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
    public class GetDataJSON extends AsyncTask<String, String, String> {
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
                post = new HttpPost("http://astro360horoscope.com/backend/api/status_amc_afc.php");
                nameValuePair = new ArrayList<NameValuePair>();
                mData = new HashMap<>();
                //here you can provide the parameter list to the api like for login username and password
                mData.put("email", Params[0]);
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

        @Override
        protected void onPostExecute(String response) {
            pd.dismiss();
            myJSON = response;

            try {
                JSONArray jsonarray = new JSONArray(response);



                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject c = jsonarray.getJSONObject(i);
                   String  name = c.getString("user_id");
                    String time = c.getString("time");
                    String status = c.getString("status");

                 HashMap<String, String> persons = new HashMap<String, String>();

                    persons.put(TAG_NAME, name);
                    persons.put(TAG_time, time);
                    persons.put(TAG_status, status);

                    personList.add(persons);
                }

                ListAdapter adapter = new SimpleAdapter(
                        ThirdTab.this, personList, R.layout.list_item,
                        new String[]{TAG_NAME, TAG_time, TAG_status},
                        new int[]{R.id.status_name, R.id.status_time, R.id.status_status
                        }
                );

                list.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(response);



        }
    }
}





