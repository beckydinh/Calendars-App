//******************************************************************************
// Author      : Becky Dinh
// Project     : Assignment 1-2
// Course      : INF 122 - Software Design: Structure and Implementation
// File        : Application.java
// Description : The main appliction that all the components run on
//******************************************************************************

package inf122hw1;

import java.util.ArrayList;
import java.sql.*;

public class Application 
{
    private ArrayList<User> users;
    private String currentUser;
    private String currentCalendar;
    private String currentEvent;
    private boolean darkTheme;
    private String timezone;
    
    public Application()
    {
        users = new ArrayList();
        currentUser = "";
        currentCalendar = "";
        currentEvent = "";
        darkTheme = true;
        timezone = "PST";
    }
    
    // current user getter and setter
    public void setCurrentUser(String currentUser)
    {
        this.currentUser = currentUser;
    }
    public String getCurrentUser()
    {
        return currentUser;
    }
    
    // current calendar getter and setter
    public void setCurrentCalendar(String currentCalendar)
    {
        this.currentCalendar = currentCalendar;
    }
    public String getCurrentCalendar()
    {
        return currentCalendar;
    }
    
    // current event getter and setter
    public void setCurrentEvent(String currentEvent)
    {
        this.currentEvent = currentEvent;
    }
    public String getCurrentEvent()
    {
        return currentEvent;
    }
    
    //**************************************************************************
    // Function : addUser()
    // Purpose  : Adds new user to the list of users
    //**************************************************************************
    public void addUser(User user)
    {
        users.add(user);
    }
    
    //**************************************************************************
    // Function : userExists()
    // Purpose  : Returns true if the username already exists, returns false if 
    //            the username does not exist
    //**************************************************************************
    public boolean userExists(String username)
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(username))
            {
                return true;
            }
        }
        return false;
    }
    
    //**************************************************************************
    // Function : showUsers()
    // Purpose  : Displays all usernames saved
    //**************************************************************************
    public void showUsers()
    {
        for(int i = 0; i < users.size(); ++i)
        {
            System.out.println(users.get(i).getUsername());
        }
    }
    
    //**************************************************************************
    // Function : noUsers()
    // Purpose  : Returns true if there are not users saved, returns false if 
    //            there is at least one user saved
    //**************************************************************************
    public boolean noUsers()
    {
        return users.isEmpty();
    }
    
    //**************************************************************************
    // Function : addCalendar()
    // Purpose  : Adds calendar associated with the current user
    //**************************************************************************
    public void addCalendar(Calendar calendar)
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                users.get(i).addCalendar(calendar);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : removeCalendar()
    // Purpose  : Removes current calendar from the current user's list of calendars
    //**************************************************************************
    public void removeCalendar(String calendarTitle)
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                users.get(i).removeCalendar(calendarTitle);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : renameCalendar()
    // Purpose  : Renames current calendar from the current user's list of calendars
    //**************************************************************************
    public void renameCalendar(String newTitle)
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                users.get(i).renameCalendar(currentCalendar, newTitle);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : showCalendars()
    // Purpose  : Display all available calendar associated with the current user
    //**************************************************************************
    public void showCalendars()
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                ArrayList<Calendar> currUserCalendar = users.get(i).getCalendars();
                for(int j = 0; j < currUserCalendar.size(); ++j)
                {
                    System.out.println(currUserCalendar.get(j).getCalendarTitle());
                }
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : calendarExists()
    // Purpose  : Returns true if the current user already has the calendar 
    //            saved, return false if the current user does not have the 
    //            calendar saved
    //**************************************************************************
    public boolean calendarExists(String calendarTitle)
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                return users.get(i).calendarExists(calendarTitle);
            }
        }
        return false;
    }
    
    //**************************************************************************
    // Function : currUserHasNoCalendars()
    // Purpose  : Returns true if the current user has no calendars saved, 
    //            return false if the current user has at least one calendar saved
    //**************************************************************************
    public boolean currUserHasNoCalendars()
    {
        boolean noCalendars = false;
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                noCalendars = users.get(i).getCalendars().isEmpty();
                break;
            }
        }
        return noCalendars;
    }
    
    //**************************************************************************
    // Function : addEvent()
    // Purpose  : Adds event to the current calendar associated with the current user
    //**************************************************************************
    public void addEvent(Event event)
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                users.get(i).addEvent(currentCalendar, event);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : removeEvent()
    // Purpose  : Remove a specific event from the current calendar associated 
    //            with current user
    //**************************************************************************
    public void removeEvent(String eventTitle)
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                users.get(i).removeEvent(currentCalendar, eventTitle);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : updateEventTime()
    // Purpose  : - Update time of the current event from the current calendar 
    //              associated with current user
    //            - Code: 'S' = Start Time, 'E' = End Time, 'B' = both
    //**************************************************************************
    public void updateEventTime(Time newTime, char code)
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                users.get(i).updateEventTime(currentCalendar, currentEvent, newTime, code);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : updateEventDate()
    // Purpose  : - Update date of the current event from the current calendar 
    //              associated with current user
    //            - Code: 'S' = Start Date, 'E' = End Date, 'B' = both
    //**************************************************************************
    public void updateEventDate(Date newDate, char code)
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                users.get(i).updateEventDate(currentCalendar, currentEvent, newDate, code);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : viewEvent()
    // Purpose  : Display details about an event from the current calendar from 
    //            the current user
    //**************************************************************************
    public void viewEvent(String eventTitle)
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                users.get(i).viewEvent(currentCalendar, eventTitle);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : searchEvents()
    // Purpose  : Display all events from the current calendar that contain 
    //            some substring
    //**************************************************************************
    public void searchEvents(String eventSubstring)
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                users.get(i).searchEvents(eventSubstring);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : currCalendarHasNoEvents()
    // Purpose  : Return true if the current calendar from the current user has 
    //            no events saved, return false if there is at least one event 
    //            in the current calendar from the current user
    //**************************************************************************
    public boolean currCalendarHasNoEvents()
    {
        boolean noEvents = false;
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                noEvents = users.get(i).calendarHasNoEvents(currentCalendar);
                break;
            }
        }
        return noEvents;
    }
    
    //**************************************************************************
    // Function : eventExists()
    // Purpose  : Return true if the event exists in the current calendar from
    //            the current user, return false if it does not
    //**************************************************************************
    public boolean eventExists(String eventTitle)
    {
        boolean exists = false;
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                exists = users.get(i).eventExists(currentCalendar, eventTitle);
                break;
            }
        }
        return exists;
    }
    
    //**************************************************************************
    // Function : showEvents()
    // Purpose  : Displays all events in the current calendar from the current user
    //**************************************************************************
    public void showEvents()
    {
        for(int i = 0; i < users.size(); ++i)
        {
            if(users.get(i).getUsername().equals(currentUser))
            {
                users.get(i).showEvents(currentCalendar);
                break;
            }
        }
    }
    
    //**************************************************************************
    // Function : showSettings()
    // Purpose  : Displays the current theme and timezone
    //**************************************************************************
    public void showSettings()
    {
        System.out.println("Theme: " + (darkTheme ? "Dark" : "Light") + "\n" +
                           "Timezone: " + timezone);
    }
    
    //**************************************************************************
    // Function : changeTheme()
    // Purpose  : - Changes the theme of the app
    //            - Dark -> Light or Light -> Dark  
    //**************************************************************************
    public void changeTheme()
    {
        darkTheme = !darkTheme;
    }
    
    //**************************************************************************
    // Function : setTimezone()
    // Purpose  : Sets timezone using the argument passed 
    //**************************************************************************
    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }
    
    //**************************************************************************
    // Function : printCurrents()
    // Purpose  : Displays the current user, calendar, and event
    //**************************************************************************
    public void printCurrents()
    {
        System.out.println("Current user: " + currentUser + "\n" +
                           "Current calendar: " + currentCalendar + "\n" +
                           "Current event: " + currentEvent);
    }
}
