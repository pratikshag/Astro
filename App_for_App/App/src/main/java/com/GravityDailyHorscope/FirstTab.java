package com.GravityDailyHorscope;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

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

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by pratiksha goyal on 1/15/2016.
 */
public class FirstTab extends Activity {
    WebView afcinfo1;
    ImageView imageDetail;
    PhotoViewAttacher mAttacher;
    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    PointF startPoint = new PointF();
    PointF midPoint = new PointF();
    float oldDist = 1f;
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;
    public ProgressDialog pd;
      public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
          pd = new ProgressDialog(this);
          pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
          pd.setMessage("Loading. . .");
          pd.show();
          AdView mAdView = (AdView) findViewById(R.id.adView);
          AdRequest adRequest = new AdRequest.Builder().build();
          mAdView.loadAd(adRequest);

          final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        afcinfo1 = (WebView) findViewById(R.id.afcinfo);
        imageDetail = (ImageView) findViewById(R.id.imageView1);
          Drawable bitmap = getResources().getDrawable(R.drawable.afc_amc);
          imageDetail.setImageDrawable(bitmap);

          // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
          mAttacher = new PhotoViewAttacher(imageDetail);
          if( isNetworkConnected()){
              new Test().execute("amcinfo");}
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
    private class Test extends AsyncTask<String, String, String> {

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
           afcinfo1.loadData(text, "text/html", "utf-8");
            super.onPostExecute(response);





        }


    }
}

