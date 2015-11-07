package com.example.android.domarket;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

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
    RecyclerView recList;
    ActivityFeedAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initializeData();

        View rootView = inflater.inflate(R.layout.fragment_activityfeed, container, false);

        recList = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        // Create, configure and set layout manager
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setItemAnimator(new DefaultItemAnimator());

        // Create and attach adapter
        adapter = new ActivityFeedAdapter(jobs);
        recList.setAdapter(adapter);

        // Create and configure refresh listener
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        // Setup refresh listener
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // TODO react to refresh
                swipeContainer.setRefreshing(false); // Refresh done
                ActivityFeedAdapter adapter = new ActivityFeedAdapter(jobs);
                recList.setAdapter(adapter);
            }
        });

        return rootView;
    }

    private void initializeData(){
        jobs = new ArrayList<>();
        try {
            getData();
        }catch(Exception e){

        }
    }

    private void getData() throws JSONException {
        APIClient.get("/messages", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("Response", response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray result) {
                // Pull out the jobs from the results
                JSONObject[] results = null;
                int resultsLength = 0;
                try {
                    resultsLength = result.length();
                    results = new JSONObject[resultsLength];
                    for (int i = 0; i < resultsLength; i++) {
                        results[i] = (JSONObject) result.get(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String tweetText = null;
                try {
                    for (int j = 0; j < resultsLength; j++) {
                        System.out.println("added job in Activity Feed");
                        jobs.add(new Job(results[j].getString("content"), results[j].getString("sender_id")));
                    }
                    adapter = new ActivityFeedAdapter(jobs);
                    recList.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Do something with the response
                //System.out.println(tweetText);
            }
        });
    }
}

