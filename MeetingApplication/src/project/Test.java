package project;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {
    static ArrayList<Meeting> allMeetings = new ArrayList<>();
    static ArrayList<Person> usernames = new ArrayList<>();
    static Person guest = new Person("Null");
    static Person logout = new Person("Null");
    static boolean isLoggedIn = false; // To track if a user is logged in
    static boolean usersCreated = false; // To track if users have been created

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

         // User creation section
         while (true) {
            System.out.println("Enter username (-1 to continue with menu):");
            String username = scn.next();
             if (username.equals("-1")) {
                 if(usernames.isEmpty()){
                     System.out.println("You must create at least one username to continue");
                   }
                 else{
                     usersCreated = true;
                   break;
                 }
               continue;
            }
            Person user = new Person(username);
            usernames.add(user);
        }

       if(!usersCreated){
            System.out.println("Please create a username to continue.");
            return;
       }


        // Main application loop
        while (true) {
            System.out.println("\n0-Login");
            System.out.println("1-Create/Host new Meeting");
            System.out.println("2-Cancel a meeting");
            System.out.println("3-Attend an existing meeting");
            System.out.println("4-Leave a meeting");
            System.out.println("5-Display Meetings which you are currently attending:");
            System.out.println("6-Display Meetings organized by me");
            System.out.println("7-Logout");
            System.out.println("8-Exit");
            
             int menu1 = -1;
           
            try {
                  menu1 = scn.nextInt();
              } catch (InputMismatchException e) {
                   System.out.println("Invalid input, please enter a number.");
                   scn.next();
                    continue;
              }

            if (!isLoggedIn && menu1 != 0 && menu1 !=8) {
                System.out.println("Please login to continue.");
                continue; // If not logged in, continue the loop to show menu again.
            }
            
           switch (menu1) {
                case 0:
                    if(!isLoggedIn){
                        login(scn);
                    }
                     else{
                        System.out.println("Please logout first.");
                    }
                    break;
                case 1:
                     newMeeting(scn);
                    break;
                case 2:
                      cancelMeeting(scn);
                    break;
                case 3:
                      AttendMeeting(scn);
                    break;
                case 4:
                     leaveMeeting(scn);
                    break;
                case 5:
                    guest.displayMyMeetings();
                    break;
                case 6:
                      guest.displayMyOrganizations();
                    break;
                case 7:
                      logout();
                    break;
                case 8:
                    System.out.println("Bye!");
                    return; // Exit the application
                default:
                     System.out.println("Invalid choice, please try again.");
           }
      }
    }

    public static void login(Scanner scn) {
        System.out.println("Enter username to login:");
        String username = scn.next();
        for (Person user : usernames) {
            if (user.getname().equals(username)) {
                guest = user;
                isLoggedIn = true;
                System.out.println("Login successful! Welcome, " + guest.getname());
                return;
            }
        }
        System.out.println("User not found.");
    }

     public static void newMeeting(Scanner scn) {
        if (guest.getname().equals("Null")) {
            System.out.println("You cannot do that unless you login!");
            return;
        }

        System.out.println("Enter the name of the meeting:");
        String meetingName = scn.next();

        System.out.println("Enter meeting date (day month year):");
        String day = scn.next();
        String month = scn.next();
        int year = 0;
        try {
            year = scn.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid year input, please enter an integer.");
            scn.next();
            return;
        }

        System.out.println("Enter meeting time (hour minute):");
        int hour = 0;
        int minute = 0;
        try {
            hour = scn.nextInt();
            minute = scn.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid time input, please enter an integer.");
            scn.next();
            return;
        }

        System.out.println("Enter time zone:");
        String timeZone = scn.next();
        Mdate newdate = new Mdate(day, month, year, timeZone, hour, minute);

         boolean isOnline = false;
         while(true){
            System.out.println("Is the meeting online? (true/false):");
            try{
             isOnline = scn.nextBoolean();
                break;
            }
          catch(InputMismatchException e){
             System.out.println("Invalid input, please enter true or false.");
             scn.next();
                }
          }

        String url = null;
        String location = null;
        if (isOnline) {
            System.out.println("Enter meeting URL:");
            url = scn.next();
        } else {
            System.out.println("Enter meeting location:");
            location = scn.next();
        }
        Meeting newMeeting = new Meeting(newdate, meetingName, guest, isOnline, url, location); // host current user

        guest.organizeMeeting(newMeeting);
        allMeetings.add(newMeeting);
       System.out.println("Meeting created and saved successfully.");
    }

     public static void cancelMeeting(Scanner scn) {
        if (guest.getname().equals("Null")) {
            System.out.println("You cannot do that unless you login!");
            return;
        }
        ArrayList<Meeting> organizedMeetings = guest.getiOrganize();
        if (organizedMeetings.isEmpty()) {
            System.out.println("You have not organized any meetings.");
            return;
        }
        System.out.println("Meetings you have organized: ");
        for (int i = 0; i < organizedMeetings.size(); i++) {
            System.out.println(i + ". " + organizedMeetings.get(i).getMeetingName());
        }
        System.out.println("Please enter the number of the meeting you want to cancel:");

        int index = 0;
        try {
            index = scn.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid number input, please enter an integer.");
            scn.next();
            return;
        }
        if (index < 0 || index >= organizedMeetings.size()) {
            System.out.println("Invalid meeting number.");
            return;
        }
        Meeting canceledMeeting = organizedMeetings.get(index);

        guest.cancelMeeting(canceledMeeting);

        for (Person attendee : canceledMeeting.getattendees()) {
            attendee.removeMeeting(canceledMeeting);
        }
        allMeetings.remove(canceledMeeting);

        System.out.println("Meeting cancelled successfully.");
    }


     public static void AttendMeeting(Scanner scn) {
        if (guest.getname().equals("Null")) {
            System.out.println("You cannot do that unless you login!");
            return;
        }
        if (allMeetings.isEmpty()) {
            System.out.println("There are no meetings available.");
            return;
        }
       System.out.println("Available meetings: ");
       for(Meeting meeting : allMeetings){
           System.out.println(meeting.getMeetingName());
       }
      System.out.println("Please enter the name of the meeting you want to attend:");
       String meetingName = scn.next();

       Meeting selectedMeeting = null;
       for (Meeting meeting : allMeetings){
          if(meeting.getMeetingName().equals(meetingName)){
            selectedMeeting = meeting;
             break;
           }
       }
         if (selectedMeeting == null) {
              System.out.println("Invalid meeting name.");
             return;
          }
        if(selectedMeeting.addAttendee(guest)){
             System.out.println("Successfully joined the meeting");
            guest.addMeeting(selectedMeeting);
          }
        else {
            System.out.println("You are already attending this meeting!");
        }
    }

    public static void leaveMeeting(Scanner scn) {
    if (guest.getname().equals("Null")) {
        System.out.println("You cannot do that unless you login!");
        return;
    }
    if (guest.getmyMeetings().isEmpty()) {
        System.out.println("You are not attending any meetings.");
        return;
    }
      System.out.println("Meetings you are attending: ");
        for(Meeting meeting : guest.getmyMeetings()){
           System.out.println(meeting.getMeetingName());
         }
    System.out.println("Please enter the name of the meeting you want to leave:");
     String meetingName = scn.next();
      Meeting selectedMeeting = null;
       for (Meeting meeting : guest.getmyMeetings()){
          if(meeting.getMeetingName().equals(meetingName)){
            selectedMeeting = meeting;
             break;
           }
       }
        if (selectedMeeting == null) {
              System.out.println("Invalid meeting name.");
             return;
          }

    if (selectedMeeting.removeAttendee(guest)) {
        System.out.println("Successfully left the meeting");
        guest.removeMeeting(selectedMeeting);
    } else {
        System.out.println("You are not attending this meeting!");
    }

}

    public static void logout() {
        guest = logout;
        isLoggedIn = false;
        System.out.println("Logged out. Have a nice day!");
    }
}