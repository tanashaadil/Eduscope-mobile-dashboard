package com.learn.eduscope.eduscope.studentDashboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learn.eduscope.eduscope.R;

/**
 * Created by Pikanite_1 on 6/29/2018.
 */

public class UpcomingEventAdapter extends RecyclerView.Adapter<UpcomingEventAdapter.OfferViewHolder> {
    String[] UpcomingEvents;
    String[] remainingDays;
    String[] Modules;
    View v;
    Context context;
    @Override
    public UpcomingEventAdapter.OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_lecture_upcoming_event, parent, false);
        return new UpcomingEventAdapter.OfferViewHolder(v);
    }

    public UpcomingEventAdapter(String[] list, String[] list2,String[] list3, Context mcontext){
        this.UpcomingEvents = list;
        this.remainingDays = list2;
        this.Modules = list3;

        this.context = mcontext;



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
    public void onBindViewHolder(UpcomingEventAdapter.OfferViewHolder holder, int position) {
        holder.modName.setText(UpcomingEvents[position]);
        holder.TeacherName.setText(remainingDays[position]);
        holder.LectureName.setText(Modules[position]);


        holder.viewClick.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {

                Log.e("EditOffer ---->", "EditOffer");


            }
        });
    }


    @Override
    public int getItemCount() {
        return  UpcomingEvents.length;
    }


}
