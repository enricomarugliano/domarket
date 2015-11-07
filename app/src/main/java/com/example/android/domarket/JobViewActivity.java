package com.example.android.domarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

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
    }
}
