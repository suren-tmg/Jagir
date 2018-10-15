package com.suren.jagir.jagir;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import static com.suren.jagir.jagir.TabFragment.int_items;

/**
 * Created by Admin on 3/1/2017.
 */

public class MyAdapter  extends FragmentPagerAdapter {


    public MyAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new SummaryFragment();
            case 1:
                return new StockFragment();
            case 2:
                return new MutualFundFragment();


        }
        return null;
    }

    @Override
    public int getCount() {


        return int_items;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "Hot Jobs";
            case 1:
                return "Recent Jobs";

            case 2:
                return "Categories";

        }

        return null;
    }
}
