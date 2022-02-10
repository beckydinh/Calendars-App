## INF 122 Assignment 1-2

- Console based application using Java 8/1.8.0_162
- The list of commands are displayed on a menu before the user is prompted for input
- A display indicating the current user logged in, the current calendar selected, and the current event is printed out before the command menus to help keep track of what is being viewed
- A user must be added and logged into before any other actions can be performed
- Calendars can be added after after a user logs in. Viewing, removing, or updating a calendar can only be done if there is at least one calendar saved to the user account
- Calendars can be renamed after by updating the selected calendar
- Events can be added after a calendar is added. Viewing, removing, or updating an event can only be done if there is at least one event saved to the selected calendar
- Event times and dates can be editted by updating the event
- Application is designed to catch logical errors
    - Ex. trying to view all calendars before creating 
    - Ex. entering an event title that does not exist
- Application is not to catch all invalid inputs 
    - Ex. inputting a String instead of an int

## 1. The *Calendars* App must support the Gregorian calendar
- By default, the application utilizes the Gregorian calendar
- This can be tested after logging in, creating a calendar, and updating that calendar to add events, and adding an event, then viewing the details of an event

## 2. Each calendar in the *Calendars* app can have different sets of events that a user can set hours and minutes for, including starting and ending times.
- The test for the previous requirement shows that users can set hours, minutes as well as start and end date when adding an event
- Different sets of events can be tested by adding more events and then viewing the details of an event. Viewing the details of an event displays a list of all events saved to the selected calendar

## 4. A user can add, remove, and update events.
- The previous tests showed that users can add events
- To test user's ability to remove an event, select the remove event command when prompted
- To test user's ability to update an event, select the update event command when prompted
 
## 5. The Calendars app supports multiple users each of which may have multiple calendars.
- The previous tests show that users can add a calendar to their account
- To test for multiple calendars, add more calendars, then select the command to view all calendars to display a list of all the calendars saved to the current user

## 6. Users can add, remove, and update individual calendars.
- The previous tests show that individual calendars can be added and updated
    - Updating a calendar includes renaming the calendar and adding/removing/updating events
- To test the user's ability to remove a calendar, select the remove calendar command when prompted

## 8. The Calendars app provides users with a choice of a dark theme or light theme.
- This can be tested by selecting the view settings command on the main menu
- The current theme and timezone settings is displayed above the command menu

## 9. The Calendars app has a screen that allows a user to configure the app for a particular time zone or change the app's theme (either dark or light).
- Configuration of timezone and theme can be adjusted by selecting the view settings command then following the prompt

## 15. A user should be able to search for events that contain a particular string or substring. For example, if a user searches for "birth", events entitled "Rebirth meeting" and "Birthday party" should be returned to the user.
- This can be tested after logging in, creating a calendar, and updating that calendar to add events, and adding an event
- Once at least one event has been added, select the search by event title command and follow the prompt

## 16. Selecting a particular event should show details of an event, including the time it starts and ends, the title of the event, and any users the event is shared with.
- This can be tested after logging in, creating a calendar, and updating that calendar to add events, and adding an event, then viewing the details of an event
