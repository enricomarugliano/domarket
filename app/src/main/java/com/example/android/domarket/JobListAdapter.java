package com.example.android.domarket;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobViewHolder>{

    static Context con;

    JobListAdapter(Context con){
        this.con = con;
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView jobName;
        TextView jobDes;
        TextView jobBudget;

        JobViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.job_card);
            jobName = (TextView)itemView.findViewById(R.id.job_name);
            jobDes = (TextView)itemView.findViewById(R.id.job_description);
            jobBudget = (TextView)itemView.findViewById(R.id.budget);

            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int itemPosition = getAdapterPosition();
                    Intent intent = new Intent(con, JobViewActivity.class);
                    intent.putExtra("item_position", itemPosition);
                    con.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return JobsListFragment.jobs.size();
    }


    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job_card, viewGroup, false);
        return new JobViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JobViewHolder jobViewHolder, int i) {
        jobViewHolder.jobName.setText(JobsListFragment.jobs.get(i).title);
        jobViewHolder.jobDes.setText(JobsListFragment.jobs.get(i).description);
        jobViewHolder.jobBudget.setText("Budget: " + JobsListFragment.jobs.get(i).budget);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
