package com.example.android.domarket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enrico on 03/11/2015.
 *
 * This fragment contains a logged in user's activity feed.
 * It is a recycler view that displays new messages and notifications
 * on cards.
 *
 */
public class ActivityFeedFragment extends Fragment {

    private List<Job> jobs;
    private SwipeRefreshLayout swipeContainer;

    public static ActivityFeedFragment newInstance() {
        return new ActivityFeedFragment();
    }

    public ActivityFeedFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initializeData();

        View rootView = inflater.inflate(R.layout.fragment_activityfeed, container, false);

        RecyclerView recList = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        // Create, configure and set layout manager
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setItemAnimator(new DefaultItemAnimator());

        // Create and attach adapter
        ActivityFeedAdapter adapter = new ActivityFeedAdapter(jobs);
        recList.setAdapter(adapter);

        // Create and configure refresh listener
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        // Setup refresh listener
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO react to refresh
                swipeContainer.setRefreshing(false); // Refresh done
            }
        });

        return rootView;
    }

    private void initializeData(){
        jobs = new ArrayList<>();
        jobs.add(new Job("Buy me beer", "Get me beer ASAP!"));
    }
}