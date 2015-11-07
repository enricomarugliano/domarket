package com.example.android.domarket;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Dylan on 06/11/2015.
 */

public class Bid
{

    int ID;
    String bidder;
    int jobID;
    double amount;
    String message;
    boolean isCWinner;

    public Bid(String bidder, String message, double amount){
        this.bidder = bidder;
        this.message = message;
        this.amount = amount;
    }

}