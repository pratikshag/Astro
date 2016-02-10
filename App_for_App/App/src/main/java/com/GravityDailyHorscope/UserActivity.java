package com.GravityDailyHorscope;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.lukedeighton.wheelview.WheelView;
import com.lukedeighton.wheelview.adapter.WheelArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int ITEM_COUNT = 12;
    static Typeface plain;
    ImageButton img_btn_daily_horoscope;
    ImageButton img_btn_weekly;
    ImageButton img_btn_monthly;
    ImageButton img_btn_mood;
    TextView horoscope_sign_button;
    TextView horoscope_sign;
    TextView zodiac_textView,mood1;
    RelativeLayout wheel_relativeLayout;
    LinearLayout resultlinearLayout;
    String zodiac;
    String sign,email;
    String final_sign_name;
    String final_type_search;
    String sessionemail;
    MenuItem menuItem;
    MenuItem menuItem1;
    MenuItem menuItem2;
    MenuItem menuItem3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        // Intent intent = getIntent();
        // Bundle intentBundle = intent.getExtras();
        //String email = intentBundle.getString("EMAIL");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setTitle("Gravity Daily Prediction");
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#3d3d3d'>      Astro360</font>"));


        final WheelView wheelView = (WheelView) findViewById(R.id.wheelview);
        plain = Typeface.createFromAsset(getAssets(), "GEZODIAC.TTF");

        img_btn_daily_horoscope = (ImageButton) findViewById(R.id.img_btn_daily_horoscope_non);

        img_btn_weekly = (ImageButton) findViewById(R.id.img_btn_weekly_non);
        img_btn_monthly = (ImageButton) findViewById(R.id.img_btn_monthly_non);

        img_btn_mood = (ImageButton) findViewById(R.id.img_btn_mood_non);
        zodiac_textView = (TextView) findViewById(R.id.zodiac_name);
        mood1 = (TextView) findViewById(R.id.mood);



        final TextView option_selection_textView = (TextView) findViewById(R.id.option_selection_text);

        wheel_relativeLayout = (RelativeLayout) findViewById(R.id.wheelview_layout);
        resultlinearLayout = (LinearLayout) findViewById(R.id.result_data_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view1);
        View headerview = navigationView.getHeaderView(0);






        img_btn_daily_horoscope.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageButtonBackground();
                option_selection_textView.setText(getResources().getString(R.string.Daily));
                final_type_search = getResources().getString(R.string.Daily);
                mood1.setText("");
            }
        });


        img_btn_weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageButtonBackground();
                option_selection_textView.setText(getResources().getString(R.string.Weekly));
                final_type_search = getResources().getString(R.string.Weekly);
                mood1.setText("");

            }
        });
        img_btn_monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageButtonBackground();
                option_selection_textView.setText("");
                final_type_search = "";
                mood1.setText("");

            }
        });


        img_btn_mood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageButtonBackground();
                option_selection_textView.setText(" ");
                final_type_search = getResources().getString(R.string.Mood);
                 mood1.setText("Mood Pattern");

            }
        });

        img_btn_daily_horoscope.performClick();
        //create data for the adapter
        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(ITEM_COUNT);
        for (int i = 0; i < ITEM_COUNT; i++) {
            Map.Entry<String, Integer> entry = MaterialColor.random(this, "\\D*_500$");
            entries.add(entry);
        }


        //populate the adapter, that knows how to draw each item (as you would do with a ListAdapter)
        wheelView.setAdapter(new MaterialColorAdapter(entries));

        //a listener for receiving a callback for when the item closest to the selection angle changes
        wheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectListener() {
            @Override
            public void onWheelItemSelected(WheelView parent, Drawable itemDrawable, int position) {
                //get the item at this position
                boolean notselected = false;
                setzodiac(position, notselected);
                // Map.Entry<String, Integer> selectedEntry = ((MaterialColorAdapter) parent.getAdapter()).getItem(position);
                // parent.setSelectionColor(getContrastColor(selectedEntry));
            }
        });

        wheelView.setOnWheelItemClickListener(new WheelView.OnWheelItemClickListener() {
            @Override
            public void onWheelItemClick(WheelView parent, int position, boolean isSelected) {


                if( option_selection_textView.getText()==getResources().getString(R.string.Daily)) {
                    Intent intentprofile = new Intent(UserActivity.this, Predictions.class);
                    intentprofile.putExtra("sign", zodiac_textView.getText());
                    intentprofile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentprofile);
                }
     else if( option_selection_textView.getText()==getResources().getString(R.string.Weekly)) {
                    Intent intentprofile = new Intent(UserActivity.this, Weekly.class);
                    intentprofile.putExtra("sign", zodiac_textView.getText());
                    intentprofile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentprofile);
                }
                else if( option_selection_textView.getText()==getResources().getString(R.string.Monthly))
                    {
                    Intent intentprofile = new Intent(UserActivity.this, Monthly.class);
                    intentprofile.putExtra("sign", zodiac_textView.getText());
                    intentprofile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentprofile);
                }
                else {
                    Intent intentprofile = new Intent(UserActivity.this, MoodActivity.class);
                    intentprofile.putExtra("sign", zodiac_textView.getText());
                    intentprofile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intentprofile);
                }
                }


        });
        //wheelView.setSelectionColor(getContrastColor(entries.get(0)));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override


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

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main1, menu);



        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        //noinspection SimplifiableIfStatement
        if (id == R.id.logout1) {
            Intent intentsetting = new Intent(getApplicationContext(), SignInActivity.class);
            intentsetting.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentsetting);


        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.register) {
          /*  if (sharedpreferences.getString("emailkey", null) != null) {
                Intent intentregister = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intentregister);

            }
                else
                { */
            Intent intentregister = new Intent(getApplicationContext(), SignInActivity.class);
            intentregister.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentregister);
            //  }

        } else if (id == R.id.nav_request) {
            Intent intentregisters = new Intent(getApplicationContext(), SignUpActivity.class);
            intentregisters.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentregisters);

        }  else if (id == R.id.nav_rate) {
            Intent intenthome = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.GravityDailyHorscope"));
            startActivity(intenthome);

        } else if (id == R.id.nav_share) {
            Intent shareintent = new Intent(Intent.ACTION_SEND);
            shareintent.setType("text/plain");
            shareintent.putExtra(Intent.EXTRA_SUBJECT, "Astro360");
            shareintent.putExtra(Intent.EXTRA_TEXT,"\u2060\u2060\u2060An amazing Astro app available on Android Platform :\n" +
                    "\n" +
                    "1) Astro Medical Chart(Annual month wise Health Charts) based on your Kundli with remedies \n" +
                    "\n" +
                    "2) Astro Financial Chart (Annual month wise Financial charts)\n" +
                    " based on your Kundali \n" +
                    "\n" +
                    "3) Question Forums ( ask questions on app n get correct answers with point blank solutions on your mail id with remedies & Solutions )\n" +
                    "\n" +
                    "4) Daily Auscipious/InAuscipious Hours\n" +
                    "\n" +
                    "5) Daily/Weekly Predictions\n" +
                    "\n" +
                    "6) Hourly Mood Patterns n emotions matrix\n" +
                    "\n" +
                    "7) Lucky Colours n Astro quote for the day\n" +
                    "\n" +
                    "If this is not enough\n" +
                    "Astro360 Question forum provides you opportunity to refer your Friends Family Acquintance to Question forum of this amazing Astro 360 app n make huge Earning potential \n" +
                    "\n" +
                    "For details follow us \n" +
                    "\n" +
                    "http://astro360horoscope.com/\n" +
                    "\n" +
                    "Facebook \n" +
                    "Www.facebook.com/gravitydailyhoroscope \n" +
                    "\n" +
                    "Twitter \n" +
                    "@Astro360App \n" +
                    "\n" +
                    "More related Gravity logon to\n" +
                    "\n" +
                    "Www.Gravityecom.com");
            shareintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(Intent.createChooser(shareintent,"Share via"));

        }
        else if (id == R.id.wallet) {

            Intent intentwallet = new Intent(getApplicationContext(), Wallets1Activity.class);
            intentwallet.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentwallet);

        }
        else if (id == R.id.refer) {
            Intent intentsupport = new Intent(getApplicationContext(), Referal1Activity.class);
            intentsupport.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentsupport);
        }


        else if (id == R.id.nav_support) {
            Intent intentsupport = new Intent(getApplicationContext(), SupportActivity.class);
            intentsupport.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentsupport);
        }
        else if (id == R.id.about) {
            Intent intentsupport = new Intent(getApplicationContext(), ContentActivity.class);
            intentsupport.putExtra("TERMS", "Vision");
            intentsupport.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentsupport);
        }


        else if (id == R.id.forum) {
            Intent intentsupport = new Intent(getApplicationContext(), ForumeRequest1Activity.class);
            intentsupport.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentsupport);
        }
        else if (id == R.id.medical) {
            Intent intentsupport = new Intent(getApplicationContext(), ForumeAmc1Activity.class);
            intentsupport.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentsupport);
        }
        else if (id == R.id.financial) {
            Intent intentsupport = new Intent(getApplicationContext(),ForumeAfc1Activity.class);
            intentsupport.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentsupport);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void setzodiac(int position, boolean isSelected) {
        sign = "v";

        if (position == 0) {
            zodiac = getResources().getString(R.string.Aries);
            //  sign = "v";

        } else if (position == 1) {
            zodiac = getResources().getString(R.string.Pisces);
            //  sign = "d";

        } else if (position == 2) {
            zodiac = getResources().getString(R.string.Aquarius);
            // sign = "p";

        } else if (position == 3) {
            zodiac = getResources().getString(R.string.Capricorn);
            //  sign = "t";

        } else if (position == 4) {
            zodiac = getResources().getString(R.string.Sagittarius);
            //  sign = "n";

        } else if (position == 5) {
            zodiac = getResources().getString(R.string.Scorpio);
            // sign = "b";

        } else if (position == 6) {
            zodiac = getResources().getString(R.string.Libra);
            //  sign = "l";

        } else if (position == 7) {
            zodiac = getResources().getString(R.string.Virgo);
            // sign = "f";

        } else if (position == 8) {
            zodiac = getResources().getString(R.string.Leo);
            // sign = "h";

        } else if (position == 9) {
            zodiac = getResources().getString(R.string.Cancer);
            // sign = "r";

        } else if (position == 10) {
            zodiac = getResources().getString(R.string.Gemini);
            // sign = "x";

        } else if (position == 11) {
            zodiac = getResources().getString(R.string.Taurus);
            // sign = "j";

        }
        if (isSelected) {


            horoscope_sign.setText(sign);


            //use that value for services
            final_sign_name = zodiac;


        }
        zodiac_textView.setText(zodiac);

    }




    //get the materials darker contrast
   /* private int getContrastColor(Map.Entry<String, Integer> entry) {
       // String colorName = MaterialColor.getColorName(entry);
       // return MaterialColor.getContrastColor(colorName);
    } */


    void setImageButtonBackground() {
        img_btn_daily_horoscope.setBackgroundResource(R.drawable.daily_horoscope_non);

        img_btn_weekly.setBackgroundResource(R.drawable.weekly_non);

        img_btn_mood.setBackgroundResource(R.drawable.mood_non);
    }


    static class MaterialColorAdapter extends WheelArrayAdapter<Map.Entry<String, Integer>> {
        MaterialColorAdapter(List<Map.Entry<String, Integer>> entries) {
            super(entries);
        }

        @Override
        public Drawable getDrawable(int position) {
            Paint paint = new Paint();
            // paint.setTypeface(plain);
            Drawable[] drawable = new Drawable[]{
                    //  createOvalDrawable(getItem(position).getValue()),
                    // new TextDrawable(String.valueOf(position), paint)
            };
            return new LayerDrawable(drawable);
        }

//        private Drawable createOvalDrawable(int color) {
//            ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
//            shapeDrawable.getPaint().setColor(color);
//            return shapeDrawable;
//        }
    }

}
