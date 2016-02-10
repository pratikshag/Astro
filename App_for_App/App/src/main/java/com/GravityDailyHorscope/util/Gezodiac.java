package com.GravityDailyHorscope.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class Gezodiac extends TextView {


    public Gezodiac(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public Gezodiac(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Gezodiac(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "GEZODIAC.TTF");
        setTypeface(tf);
    }


}
