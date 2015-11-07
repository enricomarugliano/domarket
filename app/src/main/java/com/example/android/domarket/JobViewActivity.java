package com.example.android.domarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class JobViewActivity extends AppCompatActivity {

    Job currentJob;
    TextView jobTitle, jobDescription, suggestedBid, bid1, bid2, bid3;
    Button placeBid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view);
        Intent intent = getIntent();
        int positionInList = intent.getIntExtra("item_position", -1);
        currentJob = JobsListFragment.jobs.get(positionInList);
        jobTitle = (TextView) findViewById(R.id.job_view_title);
        jobDescription = (TextView) findViewById(R.id.job_view_description);
        suggestedBid = (TextView) findViewById(R.id.job_view_bid);
        bid1 = (TextView) findViewById(R.id.bid_1);
        bid2 = (TextView) findViewById(R.id.bid_2);
        bid3 = (TextView) findViewById(R.id.bid_3);
        placeBid = (Button) findViewById(R.id.place_bid_button);

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

            }
        });

    }
}
