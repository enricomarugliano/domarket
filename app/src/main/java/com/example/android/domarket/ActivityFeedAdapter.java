package com.example.android.domarket;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ActivityFeedAdapter extends RecyclerView.Adapter<ActivityFeedAdapter.NotificationViewHolder>{

    List<Job> jobs; //TODO change this to messages
    // TODO Import notification list List<> notifications;

    ActivityFeedAdapter(List<Job> jobs){
        this.jobs = jobs;
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView message;

        NotificationViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.notification_card);
            message = (TextView)itemView.findViewById(R.id.message);
        }
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }


    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_card, viewGroup, false);
        return new NotificationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder notificationViewHolder, int i) {
        notificationViewHolder.message.setText(jobs.get(i).title);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
