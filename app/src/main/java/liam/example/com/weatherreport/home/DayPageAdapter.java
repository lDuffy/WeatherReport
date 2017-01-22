package liam.example.com.weatherreport.home;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import liam.example.com.weatherreport.dao.Day;
import liam.example.com.weatherreport.utils.DateTimeUtils;

public class DayPageAdapter extends FragmentPagerAdapter {

    private List<Day> days;
    private List<Fragment> fragments = new ArrayList<>();


    public DayPageAdapter(FragmentManager fm, List<Day> days) {
        super(fm);
        this.days = days;
        setupFragments();
    }

    private void setupFragments() {
        for (Day day : days) {
            fragments.add(AdapterItemFragment.newInstance(day));

        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return DateTimeUtils.getDayFromInt(days.get(position).getDate().getDayOfWeek());
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}