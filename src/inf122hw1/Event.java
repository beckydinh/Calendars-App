//******************************************************************************
// Author      : Becky Dinh
// Project     : Assignment 1-2
// Course      : INF 122 - Software Design: Structure and Implementation
// File        : Event.java
// Description : Represents the events that users will save to a calendar
//******************************************************************************

package inf122hw1;

import java.sql.*;

public class Event 
{
    private String eventTitle;
    private Time startTime;
    private Time endTime;
    private Date startDate;
    private Date endDate;
    
    public Event(String eventTitle, Time startTime, Time endTime, Date startDate, Date endDate)
    {
        this.eventTitle = eventTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    // getter and setter
    public String getEventTitle()
    {
        return eventTitle;
    }
    
    // Code: 'S' = Start Time, 'E' = End Time
    public void setTime(Time newTime, char code)
    {
        if(code == 'S')
        {
            startTime = newTime;
        }
        else if(code == 'E')
        {
            endTime = newTime;
        }
    }
    
    // Code: 'S' = Start Date, 'E' = End Date
    public void setDate(Date newDate, char code)
    {
        if(code == 'S')
        {
            startDate = newDate;
        }
        else if(code == 'E')
        {
            endDate = newDate;
        }
    }
    
    //**************************************************************************
    // Function : viewEvent()
    // Purpose  : Prints out details our the event
    //**************************************************************************
    public void viewEvent()
    {
        System.out.println("Event title: " + eventTitle + "\n" +
                           "Start time: " + startTime.toString() + "\n" +
                           "End time: " + endTime.toString() + "\n" +
                           "Start date: " + startDate.toString() + "\n" +
                           "End date: " + endDate.toString());
    }
}
