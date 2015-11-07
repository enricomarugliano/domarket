package com.example.android.domarket;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class NewJobActivity extends AppCompatActivity {

    private Button postJobButton;
    private EditText JobTitle, JobDes, Bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);

        // Create and configure add new job button listener
        JobTitle = (EditText) findViewById(R.id.job_title);
        JobDes = (EditText) findViewById(R.id.job_desc);
        Bid = (EditText) findViewById(R.id.suggested_bid);
        postJobButton = (Button) findViewById(R.id.post_job_button);
        postJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    postData();
                    System.out.println("Attempting to post Job...");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    private void postData() throws JSONException {

        RequestParams params = new RequestParams();
        params.put("title", JobTitle.getText());
        params.put("description", JobDes.getText());
        params.put("budget", Bid.getText());
        params.put("userID", 1);
        params.put("deadline", "");
        params.put("location", "Maynooth");

        APIClient.post("/jobs", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
               // Intent intent = new Intent(null, MainActivity.class);
               // startActivity(intent);
                finish();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray result) {
                // Pull out the jobs from the results
                //Intent intent = new Intent(null, MainActivity.class);
                //startActivity(intent);
                finish();
            }
        });
    }
}
