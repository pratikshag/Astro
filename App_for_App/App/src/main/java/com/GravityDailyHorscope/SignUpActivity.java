package com.GravityDailyHorscope;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SignUpActivity extends AppCompatActivity
       // implements View.OnClickListener
{
    private static final String TAG = "SignupActivity";
    ImageButton back1;
    TextView show1;
    Date testDate = null;
    String result = null;
    private ImageButton ib;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private EditText et;
    String macAddress;
    public GlobalClass globalVariable;
    int code = 0;
    String name, email, password, dateofbirth, refer;
    @InjectView(R.id.input_name)
    EditText _nameText;
    @InjectView(R.id.input_email)
    EditText _emailText;
    @InjectView(R.id.input_password)
    EditText _passwordText;
    @InjectView(R.id.input_date)
    EditText _dateText;
    @InjectView(R.id.input_refer)
    EditText _referText;
    @InjectView(R.id.btn_signup)
    Button _signupButton;
    @InjectView(R.id.link_login)
    TextView _loginLink;

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(10);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        globalVariable = (GlobalClass) getApplicationContext();
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ButterKnife.inject(this);
        show1 = (TextView) findViewById(R.id.show2);
        show1.setText("SignUp");
        back1 = (ImageButton) findViewById(R.id.back);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);


        //  ib.setOnClickListener(this);




        back1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (globalVariable.getEmail() == null)
                {
                    Intent intenthome = new Intent(getApplicationContext(), UserActivity.class);
                    intenthome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intenthome);
                }
                else {
                    Intent intenthome = new Intent(getApplicationContext(), MainActivity.class);
                    intenthome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intenthome);
                }
            }
        });
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                WifiInfo wInfo = wifiManager.getConnectionInfo();
                // signup();
                name = _nameText.getText().toString();
                email = _emailText.getText().toString();
                password = _passwordText.getText().toString();
                dateofbirth = _dateText.getText().toString();
                refer = _referText.getText().toString();
                if (name.isEmpty() || name.length() < 3) {
                    _nameText.setError("at least 3 characters");
                } else {
                    if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        _emailText.setError("enter a valid email address");
                    } else {
                        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                            _passwordText.setError("between 4 and 10 alphanumeric characters");
                        } else {
                            if(dateofbirth.length() - dateofbirth.replace("/", "").length()==2) {
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                try {
                                    testDate = sdf.parse(dateofbirth);
                                } catch (ParseException e) {
                                    _dateText.setError("Invalid date");
                                }

                                if (!sdf.format(testDate).equals(dateofbirth)) {
                                    _dateText.setError("Invalid date");
                                } else {
                                    final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this
                                    );
                                    progressDialog.setIndeterminate(true);
                                    progressDialog.setMessage("Creating...");
                                    progressDialog.show();
                                    new Test().execute(_nameText.getText().toString(), _emailText.getText().toString(), _passwordText.getText().toString()
                                            , _dateText.getText().toString(), _referText.getText().toString(), UUID.randomUUID().toString().substring(0, 5), wInfo.getMacAddress());

                                }
                            }
                            else
                            {
                                _dateText.setError("Invalid date");
                            }
                        }


                    }
                }
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intentsetting = new Intent(getApplicationContext(), SignInActivity.class);
                intentsetting.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentsetting);

            }
        });
    }


    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this
        );

        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        name = _nameText.getText().toString();
        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();
        dateofbirth = _dateText.getText().toString();
        refer = _referText.getText().toString();




        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        name = _nameText.getText().toString();
        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
  /*  public void onClick(View v) {
        showDialog(0);
    }


    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            et.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
                    + selectedYear);
        }
    };*/
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
                post = new HttpPost("http://astro360horoscope.com/backend/api/signup.php");
                nameValuePair = new ArrayList<NameValuePair>();
                mData = new HashMap<String,String>();
                mData.put("username", Params[0]);
                mData.put("email", Params[1]);
                mData.put("password", Params[2]);
                mData.put("dateofbirth", Params[3]);
                mData.put("referal", Params[4]);
                mData.put("code", Params[5]);
                mData.put("mac", Params[6]);
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
            if (response.equals("11")) {
                Toast.makeText(getApplicationContext(), "Successfully Registered",
                        Toast.LENGTH_LONG).show();

                Intent intentcourse = new Intent(getApplicationContext(), UserActivity.class);
                intentcourse.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentcourse);
            }
            else {
                Toast.makeText(getApplicationContext(), "Registration fail",
                        Toast.LENGTH_LONG).show();
            }

        }
    }
}
