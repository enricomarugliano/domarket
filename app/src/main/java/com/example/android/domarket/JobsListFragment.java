package com.example.android.domarket;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import com.loopj.android.http.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Enrico on 03/11/2015.
 *
 * This fragment contains the main jobs list.
 * It is a recycler view that displays new jobs and their information
 * on cards.
 *
 */
public class JobsListFragment extends Fragment {

    private List<Job> jobs;
    private SwipeRefreshLayout swipeContainer;
    private FloatingActionButton fab;


    public static JobsListFragment newInstance() {
        return new JobsListFragment();
    }

    public JobsListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    RecyclerView recList;
    JobListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initializeJobs();

        View rootView = inflater.inflate(R.layout.fragment_jobs_list, container, false);
        recList = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        // Create, configure and set layout manager
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        recList.setItemAnimator(new DefaultItemAnimator());

        // Create and attach adapter
        adapter = new JobListAdapter(jobs);
        recList.setAdapter(adapter);

        // Create and configure refresh listener
        swipeContainer = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeContainer);
        // Setup refresh listener
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Update list
                swipeContainer.setRefreshing(false);
                //initializeJobs();
                JobListAdapter adapter = new JobListAdapter(jobs);
                recList.setAdapter(adapter);
            }
        });

        return rootView;
    }

    // This method creates an ArrayList that has three jobs
    // Checkout the project associated with this tutorial on Github if
    // you want to use the same images.
    private void initializeJobs() {

        jobs = new ArrayList<>();

        try {
            getData();
        }catch(Exception e){

        }

    }

    private void getData() throws JSONException {
        APIClient.get("/jobs", null, new JsonHttpResponseHandler() {
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
                    for(int i = 0; i < resultsLength; i++) {
                        results[i] = (JSONObject) result.get(i);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String tweetText = null;
                try {
                    for(int j = 0; j < resultsLength; j++) {
                        System.out.println("added job");
                        jobs.add(new Job(results[j].getString("title"), results[j].getString("description")));
                    }
                    adapter = new JobListAdapter(jobs);
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

class APIClient {
    private static final String BASE_URL = "http://domarket.divvyapp.co/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}