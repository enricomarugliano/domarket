package com.example.android.domarket;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class NewJobActivity extends AppCompatActivity {

    private Button postJobButton;
    private EditText JobTitle, JobDes, Bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);

        // Create and configure add new job button listener
        postJobButton = (Button) findViewById(R.id.post_job_button);
        postJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO update database here
            }
        });

        JobTitle = (EditText) findViewById(R.id.job_title);
        JobDes = (EditText) findViewById(R.id.job_description);
        Bid = (EditText) findViewById(R.id.suggested_bid);
    }
}
