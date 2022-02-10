//******************************************************************************
// Author      : Becky Dinh
// Project     : Assignment 1-2
// Course      : INF 122 - Software Design: Structure and Implementation
// File        : Calendar.java
// Description : Represents the calenars that user will interact with
//******************************************************************************

package inf122hw1;

import java.util.ArrayList;
import java.sql.*;

public class Calendar 
{
    private String calendarTitle;
    private ArrayList<Event> myEvents;
    
    public Calendar(String calendarTitle)
    {
        this.calendarTitle = calendarTitle;
        myEvents = new ArrayList();
    }
    
    // getters and setters
    public void setTitle(String calendarTitle)
    {
        this.calendarTitle = calendarTitle;
    }
    public String getCalendarTitle()
    {
        return calendarTitle;
    }
    public ArrayList<Event> getEvents()
    {
        return myEvents;
    }
    
    //**************************************************************************
    // Function : addEvent()
    // Purpose  : Add event to the list of events
    //**************************************************************************
    public void addEvent(Event event)
    {
        myEvents.add(event);
    }
    
    //**************************************************************************
    // Function : removeEvent()
    // Purpose  : Remove the specified event
    //**************************************************************************
    public void removeEvent(String eventTitle)
    {
        for(int i = 0; i < myEvents.size(); ++i)
        {
            if(myEvents.get(i).getEventTitle().equals(eventTitle))
            {
                myEvents.remove(i);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : updateEventTime()
    // Purpose  : - Searches for the specified event, then updates the event time
    //            - Code: 'S' = Start Time, 'E' = End Time, 'B' = both
    //**************************************************************************
    public void updateEventTime(String eventTitle, Time newTime, char code)
    {
        for(int i = 0; i < myEvents.size(); ++i)
        {
            if(myEvents.get(i).getEventTitle().equals(eventTitle))
            {
                myEvents.get(i).setTime(newTime, code);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : updateEventTime()
    // Purpose  : - Searches for the specified event, then updates the event date
    //            - Code: 'S' = Start Time, 'E' = End Time, 'B' = both
    //**************************************************************************
    public void updateEventDate(String eventTitle, Date newDate, char code)
    {
        for(int i = 0; i < myEvents.size(); ++i)
        {
            if(myEvents.get(i).getEventTitle().equals(eventTitle))
            {
                myEvents.get(i).setDate(newDate, code);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : viewEvent()
    // Purpose  : Searches for the specified event, then displays details 
    //            about the event
    //**************************************************************************
    public void viewEvent(String eventTitle)
    {
        for(int i = 0; i < myEvents.size(); ++i)
        {
            if(myEvents.get(i).getEventTitle().equals(eventTitle))
            {
                myEvents.get(i).viewEvent();
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : searchEvents()
    // Purpose  : Display all events that contain some substring
    //**************************************************************************
    public void searchEvents(String eventSubstring)
    {
        for(int i = 0; i < myEvents.size(); ++i)
        {
            if(myEvents.get(i).getEventTitle().contains(eventSubstring))
            {
                System.out.println(myEvents.get(i).getEventTitle());
            }
        }
    }
    
    //**************************************************************************
    // Function : showEvents()
    // Purpose  : Display all event titles from the events list
    //**************************************************************************
    public void showEvents()
    {
        for(int i = 0; i < myEvents.size(); ++i)
        {
            System.out.println(myEvents.get(i).getEventTitle());
        }
    }
    
    //**************************************************************************
    // Function : eventExists()
    // Purpose  : Return true if the specified event exists in the list of events,
    //            return false if it does not
    //**************************************************************************
    public boolean eventExists(String eventTitle)
    {
        for(int i = 0; i < myEvents.size(); ++i)
        {
            if(myEvents.get(i).getEventTitle().equals(eventTitle))
            {
                return true;
            }
        }
        return false;
    }
}
