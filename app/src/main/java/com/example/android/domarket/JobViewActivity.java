package com.example.android.domarket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JobViewActivity extends AppCompatActivity {

    Job currentJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view);
        Intent intent = getIntent();
        int positionInList = intent.getIntExtra("item_position", -1);
        currentJob = JobsListFragment.jobs.get(positionInList);
    }
}
