package com.example.android.domarket;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ActivityFeedAdapter extends RecyclerView.Adapter<ActivityFeedAdapter.JobViewHolder>{

    List<Job> jobs;

    ActivityFeedAdapter(List<Job> jobs){
        this.jobs = jobs;
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView jobName;
        TextView jobDes;

        JobViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.job_card);
            jobName = (TextView)itemView.findViewById(R.id.job_name);
            jobDes = (TextView)itemView.findViewById(R.id.job_description);
        }
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }


    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.job_card, viewGroup, false);
        return new JobViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JobViewHolder jobViewHolder, int i) {
        jobViewHolder.jobName.setText(jobs.get(i).title);
        jobViewHolder.jobDes.setText(jobs.get(i).description);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
