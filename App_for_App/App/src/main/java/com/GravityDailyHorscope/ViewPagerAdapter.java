package com.GravityDailyHorscope;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context _context;
    public static int totalPage=5;
    public ViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        _context=context;

    }
    @Override
    public Fragment getItem(int position) {
        Fragment f = new Fragment();
        switch(position){
            case 0:
                f = Introductory_1A.newInstance(_context);
                break;
            case 1:
                f=Introductory_2.newInstance(_context);
                break;
            case 2:
                f = Introductory_3.newInstance(_context);
                break;
            case 3:
                f = Introductory_4.newInstance(_context);
                break;
            case 4:
                f = Introductory6.newInstance(_context);
                break;


        }
        return f;
    }
    @Override
    public int getCount() {
        return totalPage;
    }

}