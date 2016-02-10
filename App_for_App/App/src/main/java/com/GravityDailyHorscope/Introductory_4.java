package com.GravityDailyHorscope;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pratiksha goyal on 1/23/2016.
 */
public class Introductory_4  extends Fragment {


    public static Fragment newInstance(Context context) {
        Introductory_4 f = new Introductory_4();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.introductory_4, null);
        return root;
    }

}