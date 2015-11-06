package com.example.android.domarket;

 import java.sql.Timestamp;
 import java.util.Date;

 /**
 * Created by Dylan on 06/11/2015.
 */

public class Job
{

    int ID;
    String title;
    String description;
    double budget;
    int poster;
    //TODO replace poster int with User object
    Timestamp deadline;
    Timestamp posted;
    int winningBid;
    int winningBidder;
    boolean isCompleted;

    public Job(String title, String description){
        this.title = title;
        this.description = description;
    }

    public Date timeToDeadline()
    {
        //TODO add time functionality
        return null;
    }
}