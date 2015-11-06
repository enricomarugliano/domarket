package com.example.android.domarket;

import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enrico on 03/11/2015.
 *
 * This fragment contains the main jobs list.
 * It is a recycler view that displays new jobs and their information
 * on cards.
 *
 */
public class JobsListFragment extends Fragment {

    private static final String TAG = "JobsListFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private List<Job> jobs;

    public static JobsListFragment newInstance() {
        return new JobsListFragment();
    }

    public JobsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initializeData();

        View rootView = inflater.inflate(R.layout.fragment_jobs_list, container, false);

        RecyclerView recList = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setItemAnimator(new DefaultItemAnimator());

        RVAdapter adapter = new RVAdapter(jobs);
        recList.setAdapter(adapter);

        return rootView;
    }

    // This method creates an ArrayList that has three jobs
    // Checkout the project associated with this tutorial on Github if
    // you want to use the same images.
    private void initializeData(){
        jobs = new ArrayList<>();
        jobs.add(new Job("Buy me beer", "Get me beer ASAP!"));
        jobs.add(new Job("Buy me beer", "Get me beer ASAP!"));
        jobs.add(new Job("Buy me beer", "Get me beer ASAP!"));
        jobs.add(new Job("Buy me beer", "Get me beer ASAP!"));
        jobs.add(new Job("Buy me beer", "Get me beer ASAP!"));
        jobs.add(new Job("Buy me beer", "Fuck me in the bum"));
        jobs.add(new Job("Buy me beer", "Get me beer ASAP!"));
        jobs.add(new Job("Buy me beer", "Get me beer ASAP!"));
        jobs.add(new Job("Buy me beer", "Get me beer ASAP!"));
        jobs.add(new Job("Buy me beer", "Get me beer ASAP!"));
    }
}