package com.learn.eduscope.eduscope.studentDashboard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Pikanite_1 on 6/28/2018.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    int mNoOfTabs;
    public FragmentAdapter(FragmentManager fm,int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;

    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {

            case 0:
                ScheduledLectures scheduledLectures = new ScheduledLectures();
                return scheduledLectures;

            case 1:
                LectureSessions lectureSessions = new LectureSessions();
                return  lectureSessions;
            case 2:
                UpcomingEvents upcomingEvents = new UpcomingEvents();
                return upcomingEvents;

            default:
                return null;
        }
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
    @Override
    public int getCount() {
        return mNoOfTabs;
    }


}
