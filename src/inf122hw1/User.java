//******************************************************************************
// Author      : Becky Dinh
// Project     : Assignment 1-2
// Course      : INF 122 - Software Design: Structure and Implementation
// File        : User.java
// Description : Represents the user that interact with the application
//******************************************************************************

package inf122hw1;

import java.util.ArrayList;
import java.sql.*;

public class User 
{
    private String username;
    private ArrayList<Calendar> myCalendars;
    
    public User(String username)
    {
        this.username = username;
        myCalendars = new ArrayList();
    }
    
    // getter and setters
    public String getUsername()
    {
        return username;
    }
    
    public ArrayList<Calendar> getCalendars()
    {
        return myCalendars;
    }
    
    //**************************************************************************
    // Function : addCalendar()
    // Purpose  : Adds calendar to list of calendars
    //**************************************************************************
    public void addCalendar(Calendar calendar)
    {
        myCalendars.add(calendar);
    }
    
    //**************************************************************************
    // Function : removeCalendar()
    // Purpose  : Removes the specified calendar from the list of calendars
    //**************************************************************************
    public boolean removeCalendar(String calendarTitle)
    {
        if(calendarExists(calendarTitle))
        {
            for(int i = 0; i < myCalendars.size(); ++i)
            {
                if(myCalendars.get(i).getCalendarTitle().equals(calendarTitle))
                {
                    myCalendars.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    //**************************************************************************
    // Function : renameCalendar()
    // Purpose  : Searches for specified calendar, then renames it to the new title 
    //**************************************************************************
    public void renameCalendar(String calendarTitle, String newTitle)
    {
        for(int i = 0;  i < myCalendars.size(); ++i)
        {
            if(myCalendars.get(i).getCalendarTitle().equals(calendarTitle))
            {
                myCalendars.get(i).setTitle(newTitle);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : calendarExists()
    // Purpose  : Returns true if the specified calendar exists in the list of 
    //            calendars, return false if it does not exist
    //**************************************************************************
    public boolean calendarExists(String calendarTitle)
    {
        for(int i = 0; i < myCalendars.size(); ++i)
        {
            if(myCalendars.get(i).getCalendarTitle().equals(calendarTitle))
            {
                return true;
            }
        }
        return false;
    }
    
    //**************************************************************************
    // Function : addEvent()
    // Purpose  : Searches the specified calendar, then adds the event to that calendar
    //**************************************************************************
    public void addEvent(String calendarTitle, Event event)
    {
        for(int i = 0;  i < myCalendars.size(); ++i)
        {
            if(myCalendars.get(i).getCalendarTitle().equals(calendarTitle))
            {
                myCalendars.get(i).addEvent(event);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : removeEvent()
    // Purpose  : Searches for the specified calendar, then removes the event
    //            from that calendar
    //**************************************************************************
    public void removeEvent(String calendarTitle, String eventTitle)
    {
        for(int i = 0;  i < myCalendars.size(); ++i)
        {
            if(myCalendars.get(i).getCalendarTitle().equals(calendarTitle))
            {
                myCalendars.get(i).removeEvent(eventTitle);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : updateEventTime()
    // Purpose  : - Searches for the specified calendar, then updates the event 
    //              time from that calendar
    //            - Code: 'S' = Start Time, 'E' = End Time, 'B' = both
    //**************************************************************************
    public void updateEventTime(String calendarTitle, String eventTitle, Time newTime, char code)
    {
        for(int i = 0;  i < myCalendars.size(); ++i)
        {
            if(myCalendars.get(i).getCalendarTitle().equals(calendarTitle))
            {
                myCalendars.get(i).updateEventTime(eventTitle, newTime, code);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : updateEventDate()
    // Purpose  : - Searches for the specified calendar, then updates the event 
    //              date from that calendar
    //            - Code: 'S' = Start Date, 'E' = End Date, 'B' = both
    //**************************************************************************
    public void updateEventDate(String calendarTitle, String eventTitle, Date newDate, char code)
    {
        for(int i = 0;  i < myCalendars.size(); ++i)
        {
            if(myCalendars.get(i).getCalendarTitle().equals(calendarTitle))
            {
                myCalendars.get(i).updateEventDate(eventTitle, newDate, code);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : viewEvent()
    // Purpose  : Searches for the specified calendar, then displays details 
    //            about the specified event
    //**************************************************************************
    public void viewEvent(String calendarTitle, String eventTitle)
    {
        for(int i = 0;  i < myCalendars.size(); ++i)
        {
            if(myCalendars.get(i).getCalendarTitle().equals(calendarTitle))
            {
                myCalendars.get(i).viewEvent(eventTitle);
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
        for(int i = 0; i < myCalendars.size(); ++i)
        {
            myCalendars.get(i).searchEvents(eventSubstring);
        }
    }
    
    //**************************************************************************
    // Function : eventExists()
    // Purpose  : Return true if the event exists in the specified calendar,
    //            return false if it does not
    //**************************************************************************
    public boolean eventExists(String calendarTitle, String eventTitle)
    {
        boolean exists = false;
        for(int i = 0;  i < myCalendars.size(); ++i)
        {
            if(myCalendars.get(i).getCalendarTitle().equals(calendarTitle))
            {
                exists = myCalendars.get(i).eventExists(eventTitle);
            }
        }
        return exists;
    }
    
    //**************************************************************************
    // Function : calendarHasNoEvents()
    // Purpose  : Return true if the specified calendar has no events saved, 
    //            return false if there is at least one event saved
    //**************************************************************************
    public boolean calendarHasNoEvents(String calendarTitle)
    {
        boolean noEvents = false;
        for(int i = 0;  i < myCalendars.size(); ++i)
        {
            if(myCalendars.get(i).getCalendarTitle().equals(calendarTitle))
            {
                noEvents = myCalendars.get(i).getEvents().isEmpty();
            }
        }
        return noEvents;
    }
    
    //**************************************************************************
    // Function : showEvents()
    // Purpose  : Show all events from the specified calendar
    //**************************************************************************
    public void showEvents(String calendarTitle)
    {
        for(int i = 0;  i < myCalendars.size(); ++i)
        {
            if(myCalendars.get(i).getCalendarTitle().equals(calendarTitle))
            {
                myCalendars.get(i).showEvents();
            }
        }
    }
}
