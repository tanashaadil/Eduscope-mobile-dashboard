package com.learn.eduscope.eduscope.studentDashboard.scheduleLectures;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.learn.eduscope.eduscope.R;
import com.learn.eduscope.eduscope.studentDashboard.ScheduleLectureAdapter;
import com.learn.eduscope.eduscope.studentDashboard.ScheduledLectures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link session.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link session#newInstance} factory method to
 * create an instance of this fragment.
 */
public class session extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView imageView;
    TextView Title;
     String ti = "";
    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    String[] StudentQuestion;
    String[] StudentName;
    LineChart chart;
    PieChart chart2;
    SwipeRefreshLayout ScheduleLectureswipeRefreshLayout;
    private RecyclerView.LayoutManager LayoutManager;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDbRef;


    public session() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment session.
     */
    // TODO: Rename and change types and number of parameters
    public static session newInstance(String param1, String param2) {
        session fragment = new session();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_session, container, false);
        View view = inflater.inflate(R.layout.fragment_session, container, false);
        imageView = (ImageView)view.findViewById(R.id.backToOffers);
        Title = (TextView)view.findViewById(R.id.title);

        if (getArguments() != null) {
            ti = getArguments().getString("title");
        }


        String role = sharedpreferences.getString("role", "");

        System.out.println("role");
        System.out.println(role);
        if(role.equals("Lecturer"))
        {
            chart = (LineChart) view.findViewById(R.id.chart);
            chart2 = (PieChart) view.findViewById(R.id.chart2);


            mFirebaseDatabase =  FirebaseDatabase.getInstance();
            mDbRef = mFirebaseDatabase.getReference("PieChart");


            mDbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    List<PieEntry> entries2 = new ArrayList<PieEntry>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        entries2.add(new PieEntry(Float.parseFloat(snapshot.getValue().toString()), 3535));
                        Log.d("RETime_RESPONSE", "Value is: " + snapshot.getValue().toString());
                    }

                    PieDataSet dataSet2 = new PieDataSet(entries2, "Label");
                    PieData piedata = new PieData(dataSet2);
                    chart2.setData(piedata);
                    chart2.invalidate();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.d("REAL_TIME_DB_ERROR", "An error occured");
                }
            });

            List<Entry> entries=new ArrayList<Entry>();


            entries.add(new Entry(500, 3535));
            entries.add(new Entry(550, 1235));
            entries.add(new Entry(600, 3235));
            entries.add(new Entry(650, 1235));


            LineDataSet dataSet = new LineDataSet(entries, "Label");

            LineData lineData = new LineData(dataSet);

            chart.setData(lineData);
            chart.invalidate();

        }

        recyclerView = (RecyclerView) view.findViewById(R.id.RecycleList);
        //        Log.e("Back My ---->", "Back1");
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(LayoutManager);
        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        // adding custom divider line with padding 16dp
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        StudentQuestion = new String[]
                {"How to digitize an image ?", "What is digitization ?", "Why do we need digitization ?", "How we get a Negative image?",  "How we get a Threshold image?" };

         StudentName = new String[]
                {"Achini", "Hiranthi", "Prabashi",  "Vindya", "Rochelle" };
        recyclerView.setAdapter(new StudentQuestionAdapter(StudentQuestion,StudentName, getActivity()));

        Title.setText(ti);


        imageView.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {

                Log.e("EditOffer ---->", "EditOffer");
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                ScheduledLectures scheduledLectures = new ScheduledLectures();
//                Bundle arguments = new Bundle();
//                arguments.putString("title",Modules[position]);

//                session.setArguments(arguments);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.Details_Main, scheduledLectures).commit();


            }
        });
        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void showSuccess(View view)
    {
        Toast toast=Toast.makeText(getActivity().getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }
}
