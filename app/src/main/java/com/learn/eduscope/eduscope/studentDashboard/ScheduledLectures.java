package com.learn.eduscope.eduscope.studentDashboard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.learn.eduscope.eduscope.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScheduledLectures.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScheduledLectures#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScheduledLectures extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    String[] Modules;
    String[] Lecture;
    String[] Lecturer;
    SwipeRefreshLayout ScheduleLectureswipeRefreshLayout;
    private RecyclerView.LayoutManager LayoutManager;

    private OnFragmentInteractionListener mListener;

    public ScheduledLectures() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScheduledLectures.
     */
    // TODO: Rename and change types and number of parameters
    public static ScheduledLectures newInstance(String param1, String param2) {
        ScheduledLectures fragment = new ScheduledLectures();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scheduled_lectures, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.RecycleList);
        ScheduleLectureswipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
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
         Modules = new String[]{"CDAP", "Machine Learning", "Proactive Database Administration",
                        "IT Project Management" ,"Image Understanding and Processing"};

         Lecture = new String[]
                {"Introduction", "lecture1", " Introduction to Oracle", "Introduction " ,"Introduction "};

        Lecturer = new String[]
                {"Jayantha Amararachchi", "Dharshana Kasthuriarachi", "Prasanna Sumathipala", "Yasas Mallawarachchi" ,"Sanjeewi Perera"};
        recyclerView.setAdapter(new ScheduleLectureAdapter(Modules,Lecture,Lecturer, getActivity()));
        ScheduleLectureswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                recyclerView.setAdapter(new ScheduleLectureAdapter(Modules,Lecture,Lecturer, getActivity()));
                ScheduleLectureswipeRefreshLayout.setRefreshing(false);


            }
        });
        return view;

    }
    public void SchedulLectureList(){

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

    }
}
