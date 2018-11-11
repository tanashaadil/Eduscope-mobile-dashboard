package com.learn.eduscope.eduscope.studentDashboard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.learn.eduscope.eduscope.R;
import com.learn.eduscope.eduscope.studentDashboard.scheduleLectures.session;

import java.util.List;

/**
 * Created by Pikanite_1 on 6/29/2018.
 */

public class ScheduleLectureAdapter extends RecyclerView.Adapter<ScheduleLectureAdapter.OfferViewHolder> implements session.OnFragmentInteractionListener  {
    String[] Modules;
    String[] Lecture;
    String[] Lecturer;
    View v;
    Context context;
    @Override
    public ScheduleLectureAdapter.OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_lecture_session, parent, false);
        return new ScheduleLectureAdapter.OfferViewHolder(v);
    }

    public ScheduleLectureAdapter(String[] list, String[] list2, String[] list3, Context mcontext){
        this.Modules = list;
        this.Lecture = list2;
        this.Lecturer = list3;
        this.context = mcontext;



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class OfferViewHolder extends RecyclerView.ViewHolder {
        TextView modName, TeacherName, LectureName,viewClick;
        public OfferViewHolder(View itemView) {
            super(itemView);
            modName = (TextView) itemView.findViewById(R.id.modName);
            TeacherName = (TextView) itemView.findViewById(R.id.teacherName);
            LectureName = (TextView) itemView.findViewById(R.id.lectureName);
            viewClick = (TextView) itemView.findViewById(R.id.view_btn);
        }
    }
    @Override
    public void onBindViewHolder(ScheduleLectureAdapter.OfferViewHolder holder, final int position) {
        holder.modName.setText(Modules[position]);
        holder.TeacherName.setText(Lecturer[position]);
        holder.LectureName.setText(Lecture[position]);

        holder.viewClick.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {

                Log.e("EditOffer ---->", "EditOffer");
                AppCompatActivity activity = (AppCompatActivity) context;
                session session = new session();
                Bundle arguments = new Bundle();
                arguments.putString("title",Modules[position]);

                session.setArguments(arguments);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.Details_Main, session).commit();


            }
        });
    }


    @Override
    public int getItemCount() {
        return  Modules.length;
    }


}
