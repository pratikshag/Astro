package com.GravityDailyHorscope;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                LayOutone tab1 = new LayOutone();
                return tab1;
            case 1:
                LayOuttwo tab2 = new LayOuttwo();
                return tab2;
            case 2:
                LayOutthree tab3 = new LayOutthree();
                return tab3;
            case 3:
                LayOutfour tab4 = new LayOutfour();
                return tab4;
            default:
                return null;
        }
    }


    public int getCount() {
        return mNumOfTabs;
    }
}