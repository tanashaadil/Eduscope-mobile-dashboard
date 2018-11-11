package com.learn.eduscope.eduscope;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.learn.eduscope.eduscope.studentDashboard.UpcomingEvents;
import com.learn.eduscope.eduscope.studentDashboard.FragmentAdapter;
import com.learn.eduscope.eduscope.studentDashboard.LectureSessions;
import com.learn.eduscope.eduscope.studentDashboard.ScheduledLectures;
import com.learn.eduscope.eduscope.studentDashboard.scheduleLectures.session;

public class StudentDashboard extends AppCompatActivity implements ScheduledLectures.OnFragmentInteractionListener,LectureSessions.OnFragmentInteractionListener,UpcomingEvents.OnFragmentInteractionListener,session.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Scheduled Lectures"));
        tabLayout.addTab(tabLayout.newTab().setText("Lecture Sessions"));
        tabLayout.addTab(tabLayout.newTab().setText("Assignments and Exams"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final PagerAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}
