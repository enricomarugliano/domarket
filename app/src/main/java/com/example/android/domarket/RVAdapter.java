package com.example.android.domarket;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.JobViewHolder>{

    List<Job> jobs;

    RVAdapter(List<Job> jobs){
        this.jobs = jobs;
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView jobName;
        TextView jobDes;

        JobViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jobs_list_card_view, viewGroup, false);
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
