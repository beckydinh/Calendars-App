//******************************************************************************
// Author      : Becky Dinh
// Project     : Assignment 1-2
// Course      : INF 122 - Software Design: Structure and Implementation
// File        : INF122Hw1.java
// Description : Console application to simulate CRUD of users, calendars, and events
//******************************************************************************

package inf122hw1;

import java.util.Scanner;
import java.sql.*;

public class INF122Hw1 
{
    public static Application app = new Application();
    public static Scanner sc = new Scanner(System.in);
    public static boolean runApp = true;
    
    //**************************************************************************
    // Function : main()
    // Purpose  : Print to console the main menu commands
    //**************************************************************************
    public static void main(String args[]) 
    {
        // prompt commands until user quits the application
        while(runApp)
        {
            app.printCurrents();
            System.out.print("1.) Add new user\n" +
                             "2.) Login\n" +
                             "3.) View settings\n" +
                             "4.) Quit\n" +
                             "Enter command: ");
            int cmd = sc.nextInt();
            sc.nextLine();
            
            switch(cmd)
            {
                case 1:
                    addUser();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    viewSettings();
                    break;
                case 4:
                    runApp = false;
                    break;
                default:
                    System.out.println("\nCommand not found. Please try again.\n");
            }
        }
    }
    
    //**************************************************************************
    // Function : addUser()
    // Purpose  : Prompts user to create a new account
    //**************************************************************************
    public static void addUser()
    {
        System.out.print("\nEnter username: ");
        String username = sc.nextLine();
        // check if username alredy exists
        if(app.userExists(username))
        {
            System.out.println(username + " already exists. Please enter another username.\n");
        }
        else
        {
            app.addUser(new User(username));
            System.out.println(username + " has been added.\n");
        }
    }
    
    //**************************************************************************
    // Function : login()
    // Purpose  : Prompts user to login to an existing account
    //**************************************************************************
    public static void login()
    {
        // if no users have been added, do not login
        if(app.noUsers())
        {
            System.out.println("\nNo users have been added yet.\n");
            return;
        }
        
        // continue prompting to login until a valid username is entered
        boolean loopUserSelection = true;
        while(loopUserSelection)
        {
            System.out.println("\nSelect a user from the list below");
            app.showUsers();
            System.out.print("Enter username: ");
            String nextUser = sc.nextLine();
            if(app.userExists(nextUser))
            {
                app.setCurrentUser(nextUser);
                System.out.println("Now logged in as " + nextUser + ".\n");
                loopUserSelection = false;
            }
            else
            {
                System.out.println("\n"+ nextUser + " does not exist.");
            }
        }
        
        // once logged in, prompt commands until current user logs out
        boolean loopUserCmd = true;
        while(loopUserCmd)
        {
            app.printCurrents();
            System.out.print("1.) View all calendars\n" + 
                             "2.) Add calendar\n" +
                             "3.) Remove calendar\n" +
                             "4.) Update calendar\n" +
                             "5.) Log out\n" +
                             "Enter command: ");
            int cmd = sc.nextInt();
            sc.nextLine();
            switch(cmd)
            {
                case 1:
                    viewCalendars();
                    break;
                case 2:
                    addCalendar();
                    break;
                case 3:
                    removeCalendar();
                    break;
                case 4:
                    updateCalendar();
                    break;
                case 5:
                    loopUserCmd = false;
                    app.setCurrentUser("");
                    System.out.println();
                    break;
                default:
                    System.out.println("\nCommand not found. Please try again.\n");
            }
        }
    }
    
    //**************************************************************************
    // Function : viewCalendars()
    // Purpose  : Display all available calendars the current user has
    //**************************************************************************
    public static void viewCalendars()
    {
        // display no calendars message if use has no calendars
        if(app.currUserHasNoCalendars())
        {
            System.out.println("\n" + app.getCurrentUser() + " has no calendars.\n");
        }
        else // display all calendars
        {
            System.out.println("\n" + app.getCurrentUser() + "'s calendars:");
            app.showCalendars();
            System.out.println();
        }
    }
    
    //**************************************************************************
    // Function : addCalendar()
    // Purpose  : Prompts current user to create a new calendar
    //**************************************************************************
    public static void addCalendar()
    {
        System.out.print("\nEnter calendar title: ");
        String calendarTitle = sc.nextLine();
        // check if calendar already exists
        if(app.calendarExists(calendarTitle))
        {
            System.out.println(app.getCurrentUser() + " already has a calendar titled " + calendarTitle + ".\n");
        }
        else
        {
            app.addCalendar(new Calendar(calendarTitle));
            System.out.println(calendarTitle + " has been added.\n");
        }
    }
    
    //**************************************************************************
    // Function : removeCalendar()
    // Purpose  : Provides list of available calendars and prompts current user 
    //            to remove an available calendar
    //**************************************************************************
    public static void removeCalendar()
    {
        // do not list calendars if current user has no calendars saved
        if(app.currUserHasNoCalendars())
        {
            System.out.println("\n" + app.getCurrentUser() + " has no calendars.\n");
        }
        else // prompt for input until a valid calendar is entered
        {
            boolean loopCalendarSelection = true;
            while(loopCalendarSelection)
            {
                System.out.println("\nSelect a calendar from the list below");
                app.showCalendars();
                System.out.print("Select calendar to remove: ");
                String calendarTitle = sc.nextLine();
                if(app.calendarExists(calendarTitle))
                {
                    app.removeCalendar(calendarTitle);
                    loopCalendarSelection = false;
                    System.out.println(calendarTitle + " has been removed.\n");
                }
                else
                {
                    System.out.println("\n" + calendarTitle + " not found.");
                }
            }
        }
    }
    
    //**************************************************************************
    // Function : updateCalendar()
    // Purpose  : - Provides list of available calendars and prompts current 
    //              user to update an available calendar
    //            - Calendar updates include renaming the calendar and 
    //              adding/removing/updating an event within the selected calendar
    //**************************************************************************
    public static void updateCalendar()
    {
        // do not list calendars if current user has no calendars saved
        if(app.currUserHasNoCalendars())
        {
            System.out.println("\n" + app.getCurrentUser() + " has no calendars.\n");
        }
        else // prompt for input until a valid calendar is entered
        {
            boolean loopCalendarSelection = true;
            String calendarTitle = "";
            while(loopCalendarSelection)
            {
                System.out.println("\nSelect a calendar from the list below");
                app.showCalendars();
                System.out.print("Select calendar to update: ");
                calendarTitle = sc.nextLine();
                if(app.calendarExists(calendarTitle))
                {
                    loopCalendarSelection = false;
                }
                else
                {
                    System.out.println("\n" + calendarTitle + " not found.");
                }
            }
            // once calendar is selected, prompt commands until user exits the selected calendar (Return to previous menu)
            boolean loopCmd = true;
            while(loopCmd)
            {
                if(!app.calendarExists(calendarTitle))
                {
                    System.out.println("\n" + calendarTitle + " not found.");
                }
                else
                {
                    app.setCurrentCalendar(calendarTitle);
                    System.out.println();
                    app.printCurrents();
                    System.out.print("1.) View event\n" +
                                     "2.) Search by event title\n" +
                                     "3.) Rename calendar\n" +
                                     "4.) Add event\n" +
                                     "5.) Remove event\n" +
                                     "6.) Update event\n" +
                                     "7.) Return to previous menu\n" +
                                     "Enter command: ");
                    int cmd = sc.nextInt();
                    sc.nextLine();
                    switch(cmd)
                    {
                        case 1:
                            viewEvent();
                            break;
                        case 2:
                            searchEvents();
                            break;
                        case 3:
                            renameCalendar();
                            calendarTitle = app.getCurrentCalendar();
                            break;
                        case 4:
                            addEvent();
                            break;
                        case 5:
                            removeEvent();
                            break;
                        case 6:
                            updateEvent();
                            break;
                        case 7:
                            loopCmd = false;
                            app.setCurrentCalendar("");
                            System.out.println();
                            break;
                        default:
                            System.out.println("\nCommand not found. Please try again.");
                    }
                }
            }
        }
    }
    
    //**************************************************************************
    // Function : viewEvent()
    // Purpose  : Provides list of available events within a selected calendar 
    //            and prompts current user to select an available event to view 
    //            in detail
    //**************************************************************************
    public static void viewEvent()
    {
        // do not list events if current calendar has no events saved
        if(app.currCalendarHasNoEvents())
        {
            System.out.println("\n" + app.getCurrentUser() + " has no events in " + app.getCurrentCalendar());
        }
        else // prompt for input until a valid event is entered
        {
            boolean loopEventSelection = true;
            while(loopEventSelection)
            {
                System.out.println("\nSelect an event from the list below");
                app.showEvents();
                System.out.print("Select event to view: ");
                String eventTitle = sc.nextLine();
                if(app.eventExists(eventTitle))
                {
                    loopEventSelection = false;
                    System.out.println();
                    app.viewEvent(eventTitle);
                    System.out.println("Attending: " + app.getCurrentUser());
                }
                else
                {
                    System.out.println("\n" + eventTitle + " event not found.");
                }
            }
        }
    }
    
    //**************************************************************************
    // Function : searchEvents()
    // Purpose  : Prompts user to search for events that contain a particular 
    //            string or substring 
    //**************************************************************************
    public static void searchEvents()
    {
        // do not list events if current calendar has no events saved
        if(app.currCalendarHasNoEvents())
        {
            System.out.println("\n" + app.getCurrentUser() + " has no events in " + app.getCurrentCalendar());
        }
        else // prompt to enter string/substring of event
        {
            System.out.print("\nEnter string or substring of event title: ");
            String eventSubstring = sc.nextLine();
            System.out.println("Events in " + app.getCurrentCalendar() + " that contain '" + eventSubstring + "'.");
            app.searchEvents(eventSubstring);
        }
    }
    
    //**************************************************************************
    // Function : renameCalendar()
    // Purpose  : Prompts user to rename the title of the selected calendar
    //**************************************************************************
    public static void renameCalendar()
    {
        // prompt for new calendar title until a valid title is entered
        boolean loopRename = true;
        String newTitle = "";
        while(loopRename)
        {
            System.out.print("\nEnter new calendar title: ");
            newTitle = sc.nextLine();
            // check if new calenar title already exists
            if(app.calendarExists(newTitle))
            {
                System.out.println(newTitle + " already exists.");
            }
            else
            {
                loopRename = false;
                app.renameCalendar(newTitle);
                System.out.println(app.getCurrentCalendar() + " renamed to " + newTitle);
                app.setCurrentCalendar(newTitle);
            }
        }
    }
    
    //**************************************************************************
    // Function : addEvent()
    // Purpose  : Prompts user to enter new event information to add to the 
    //            selected calendar
    //**************************************************************************
    public static void addEvent()
    {
        System.out.print("\nEnter event title: ");
        String eventTitle = sc.nextLine();
        // checks if event title already exists
        if(!app.eventExists(eventTitle))
        {
            System.out.print("Enter event start time (HH:MM): ");
            String startTime = sc.nextLine();
            System.out.print("Enter event end time (HH:MM): ");
            String endTime = sc.nextLine();
            System.out.print("Enter event start date (MM/DD/YYYY): ");
            String startDate = sc.nextLine();
            System.out.print("Enter event end date (MM/DD/YYYY): ");
            String endDate = sc.nextLine();
            app.addEvent(createEventObj(eventTitle, startTime, endTime, startDate, endDate));
            System.out.println(eventTitle + " has been added to " + app.getCurrentCalendar());
        }
        else
        {
            System.out.println("Event title already exists in this calendar.\n");
        }
    }
    
    //**************************************************************************
    // Function : createEventObj()
    // Purpose  : Help function to parse event information to create new event
    //**************************************************************************
    public static Event createEventObj(String eventTitle, String startTime, String endTime, String startDate, String endDate)
    {
        String str[] = startTime.split(":");
        Time st = new Time(Integer.parseInt(str[0]), Integer.parseInt(str[1]), 0);
        
        str = endTime.split(":");
        Time et = new Time(Integer.parseInt(str[0]), Integer.parseInt(str[1]), 0);
        
        str = startDate.split("/");
        Date sd = new Date(Integer.parseInt(str[2]) - 1900, Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]));
        
        str = endDate.split("/");
        Date ed = new Date(Integer.parseInt(str[2]) - 1900, Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]));
        
        return new Event(eventTitle, st, et, sd, ed);
    }
    
    //**************************************************************************
    // Function : removeEvent()
    // Purpose  : Provides list of available events within a selected calendar 
    //            and prompts current user to select an available event to remove
    //**************************************************************************
    public static void removeEvent()
    {
        // do not list events if current calendar has no events saved
        if(app.currCalendarHasNoEvents())
        {
            System.out.println("\n" + app.getCurrentUser() + " has no events in " + app.getCurrentCalendar());
        }
        else // prompt for input until a valid event is entered
        {
            boolean loopEventSelection = true;
            while(loopEventSelection)
            {
                System.out.println("\nSelect an event from the list below");
                app.showEvents();
                System.out.print("Select event to remove: ");
                String eventTitle = sc.nextLine();
                if(app.eventExists(eventTitle))
                {
                    app.removeEvent(eventTitle);
                    loopEventSelection = false;
                    System.out.println(eventTitle + " has been removed from " + app.getCurrentCalendar() + ".\n");
                }
                else
                {
                    System.out.println("\n" + eventTitle + " event not found.");
                }
            }
        }
    }
    
    //**************************************************************************
    // Function : updateEvent()
    // Purpose  : - Provides list of available events and prompts current 
    //              user to update an available event from a selected calendar
    //            - Event updates include editing the start date/time and end 
    //              date/time 
    //**************************************************************************
    public static void updateEvent()
    {
        // do not display events if selected calendar has not events saved
        if(app.currCalendarHasNoEvents())
        {
            System.out.println("\n" + app.getCurrentUser() + " has no events in " + app.getCurrentCalendar());
        }
        else // prompt for input until user enters a valid event
        {
            boolean loopEventSelection = true;
            String eventTitle = "";
            while(loopEventSelection)
            {
                System.out.println("\nSelect an event from the list below");
                app.showEvents();
                System.out.print("Select event to update: ");
                eventTitle = sc.nextLine();
                if(app.eventExists(eventTitle))
                {
                    loopEventSelection = false;
                }
                else
                {
                    System.out.println("\n" + eventTitle + " not found in " + app.getCurrentCalendar());
                }
            }
            // once event is selected, prompt commands until user exits the selected event (Return to previous menu)
            app.setCurrentEvent(eventTitle);
            boolean loopCmd = true;
            while(loopCmd)
            {
                if(!app.eventExists(eventTitle))
                {
                    System.out.println("\n" + eventTitle + " not found.");
                }
                else
                {
                    System.out.println();
                    app.printCurrents();
                    System.out.print("1.) Edit start or end times\n" +
                                     "2.) Edit start or end dates\n" +
                                     "3.) Return to previous menu\n" +
                                     "Enter command: ");
                    int cmd = sc.nextInt();
                    sc.nextLine();
                    switch(cmd)
                    {
                        case 1:
                            editEventTimes();
                            break;
                        case 2:
                            editEventDates();
                            break;
                        case 3:
                            loopCmd = false;
                            app.setCurrentEvent("");
                            System.out.println();
                            break;
                        default:
                            System.out.println("\nCommand not found. Please try again.");
                    }
                }
            }
        }
    }
    
    //**************************************************************************
    // Function : editEventTimes()
    // Purpose  : Prompts user to edit the start time, end time, or both
    //**************************************************************************
    public static void editEventTimes()
    {
        // prompt which times to edit until a valid command is entered
        boolean loopSelection = true;
        while(loopSelection)
        {
            System.out.print("\nSelect start or end time, or both (S/E/B): ");
            String type = sc.nextLine();
            String startTime = "";
            String endTime = "";
            String str[];
            Time st;
            Time et;
            switch(type)
            {
                // edit start time
                case "S":
                    loopSelection = false;
                    System.out.print("Enter new start time (HH:MM): ");
                    startTime = sc.nextLine();
                    str = startTime.split(":");
                    st = new Time(Integer.parseInt(str[0]), Integer.parseInt(str[1]), 0);
                    app.updateEventTime(st, 'S');
                    break;
                // edit end time
                case "E":
                    loopSelection = false;
                    System.out.print("Enter new end time (HH:MM): ");
                    endTime = sc.nextLine();
                    str = endTime.split(":");
                    et = new Time(Integer.parseInt(str[0]), Integer.parseInt(str[1]), 0);
                    app.updateEventTime(et, 'E');
                    break;
                // edit both start and end times
                case "B":
                    loopSelection = false;
                    System.out.print("Enter new start time (HH:MM): ");
                    startTime = sc.nextLine();
                    str = startTime.split(":");
                    st = new Time(Integer.parseInt(str[0]), Integer.parseInt(str[1]), 0);
                    app.updateEventTime(st, 'S');
                    
                    System.out.print("Enter new end time (HH:MM): ");
                    endTime = sc.nextLine();
                    str = endTime.split(":");
                    et = new Time(Integer.parseInt(str[0]), Integer.parseInt(str[1]), 0);
                    app.updateEventTime(et, 'E');
                    break;
                default:
                    System.out.println("\nCommand not found. Please try again.\n");
            }
        }
    }
    
    //**************************************************************************
    // Function : editEventDates()
    // Purpose  : Prompts user to edit the start date, end date, or both
    //**************************************************************************
    public static void editEventDates()
    {
        // prompt which times to edit until a valid command is entered
        boolean loopSelection = true;
        while(loopSelection)
        {
            System.out.print("\nSelect start or end date, or both (S/E/B): ");
            String type = sc.nextLine();
            String startDate = "";
            String endDate = "";
            String str[];
            Date sd;
            Date ed;
            switch(type)
            {
                // edit start date
                case "S":
                    loopSelection = false;
                    System.out.print("Enter new start date (MM/DD/YYYY): ");
                    startDate = sc.nextLine();
                    str = startDate.split("/");
                    sd = new Date(Integer.parseInt(str[2]) - 1900, Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]));
                    app.updateEventDate(sd, 'S');
                    break;
                // edit end date
                case "E":
                    loopSelection = false;
                    System.out.print("Enter new end date (MM/DD/YYYY): ");
                    endDate = sc.nextLine();
                    str = endDate.split("/");
                    ed = new Date(Integer.parseInt(str[2]) - 1900, Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]));
                    app.updateEventDate(ed, 'E');
                    break;
                // edit both start and end dates
                case "B":
                    loopSelection = false;
                    System.out.print("Enter new start date (MM/DD/YYYY): ");
                    startDate = sc.nextLine();
                    str = startDate.split("/");
                    sd = new Date(Integer.parseInt(str[2]) - 1900, Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]));
                    app.updateEventDate(sd, 'S');
                    
                    System.out.print("Enter new end date (MM/DD/YYYY): ");
                    endDate = sc.nextLine();
                    str = endDate.split("/");
                    ed = new Date(Integer.parseInt(str[2]) - 1900, Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]));
                    app.updateEventDate(ed, 'E');
                    break;
                default:
                    System.out.println("\nCommand not found. Please try again.\n");
            }
        }
    }
    
    //**************************************************************************
    // Function : viewSettings()
    // Purpose  : - Prints out the current settings for the app and prompts the 
    //              available commands
    //            - Users can update settings by changing the theme and setting
    //              the timezone
    //**************************************************************************
    public static void viewSettings()
    {
        System.out.println();
        boolean loopSettings = true;
        while(loopSettings)
        {
            app.showSettings();
            System.out.print(" 1.) Change theme\n" +
                             " 2.) Set timezone\n" +
                             " 3.) Return to previous menu\n" +
                             "Enter command: ");
            int cmd = sc.nextInt();
            sc.nextLine();
        
            switch(cmd)
            {
                case 1:
                    app.changeTheme();
                    System.out.println();
                    break;
                case 2:
                    System.out.print("\nEnter timezone: ");
                    String timezone = sc.nextLine();
                    app.setTimezone(timezone);
                    System.out.println();
                    break;
                case 3:
                    loopSettings = false;
                    System.out.println();
                    break;
                default:
                    System.out.println("\nCommand not found. Please try again.\n");
            }
        }
    }
}
