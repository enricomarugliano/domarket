package com.example.android.domarket;

import java.sql.Timestamp;

/**
 * Created by Dylan on 06/11/2015.
 */
public class Message {

    int ID;
    //Chat chat; //Circular nesting???
    //User sender;
    Timestamp sentTime;
    String content;

    public Message(String c, Timestamp t)
    {
        content = c;
        sentTime = t;
    }
}
