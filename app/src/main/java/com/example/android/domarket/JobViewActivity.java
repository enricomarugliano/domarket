package com.example.android.domarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class JobViewActivity extends AppCompatActivity {

    Job currentJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view);
        Intent intent = getIntent();
        int positionInList = intent.getIntExtra("item_position", -1);
        currentJob = JobsListFragment.jobs.get(positionInList);
        try {
            getJob();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getJob() throws JSONException {
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

                try {
                    //for(int j = 0; j < resultsLength; j++) {
                    //    jobs.add(new Job(results[j].getString("title"), results[j].getString("description"), results[j].getDouble("budget")));
                    //}
                    String title = results[0].getString("title");
                    String description = results[0].getString("description");
                    String budget = "Budget: " + results[0].getString("budget");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Do something with the response
                //System.out.println(tweetText);
            }
        });
    }
}
