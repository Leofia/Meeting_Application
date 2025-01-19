package project;

import java.util.ArrayList;
import java.util.UUID;

public class Person {
    private String name;
   private final String id;
    private ArrayList<Meeting> myMeetings;
    private ArrayList<Meeting> iOrganize;

      Person(String name){
        this.name = name;
        this.id = UUID.randomUUID().toString();
        myMeetings = new ArrayList<>();
        iOrganize =  new ArrayList<>();
      }
    public String getname() {
        return name;
    }
    public String getid() {
        return id;
    }
    public ArrayList<Meeting> getmyMeetings() {
        return myMeetings;
    }
    public ArrayList<Meeting> getiOrganize() {
        return iOrganize;
    }
    public void setname(String name) {
        this.name = name;
    }
    public ArrayList<Meeting> setmyMeetings() {
        return myMeetings;
    }
    public ArrayList<Meeting> setiOrganize() {
        return iOrganize;
    }

     @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return name.equals(person.name);
    }

     public boolean addMeeting(Meeting meeting){
        for (Meeting existingMeeting : myMeetings) {
          if(isOverlap(meeting,existingMeeting)){
            System.out.println("Error, this meeting overlaps with another meeting you are attending.");
            return false;
          }
        }
       myMeetings.add(meeting);
        return true;
     }
      private boolean isOverlap(Meeting newMeeting, Meeting existingMeeting) {
    // Assuming you have a way to get start and end times from meeting
    // Example check (This is where you would do actual logic):
    
    Mdate newDate = newMeeting.getDate();
    Mdate existingDate = existingMeeting.getDate();
    
    if (!newDate.getDay().equals(existingDate.getDay())
            || !newDate.getMonth().equals(existingDate.getMonth())
            || newDate.getYear() != existingDate.getYear()){
      return false;
    }
    if(newDate.gethour() != existingDate.gethour()){
      return false;
    }
     if(newDate.getminute() != existingDate.getminute()){
      return false;
    }
   
  return true; // Eğer aynı gün ve saatte ise çakışıyor
}
     public void removeMeeting(Meeting meeting){
         myMeetings.remove(meeting);
     }
     public void organizeMeeting(Meeting meeting){
         iOrganize.add(meeting);
     }
     public void cancelMeeting(Meeting meeting){
         iOrganize.remove(meeting);
     }
   
      public void displayMyMeetings() {
       if (myMeetings.isEmpty()){
           System.out.println("You are not attending any meetings.");
           return;
        }
       System.out.println("Meetings you are attending:");
       for (Meeting meeting : myMeetings) {
          System.out.println("Date:" + meeting.getDate().getDay() + "/" +  meeting.getDate().getMonth() + "/" + meeting.getDate().getYear() + ", Time:" + meeting.getDate().gethour() + ":" + meeting.getDate().getminute() + " , Host: " + meeting.gethost().getname());
        }
     }
       public void displayMyOrganizations() {
        if (iOrganize.isEmpty()) {
           System.out.println("You have not organized any meetings.");
           return;
        }
        System.out.println("Meetings you have organized:");
        for (Meeting meeting : iOrganize) {
           System.out.println("Date:" + meeting.getDate().getDay() + "/" + meeting.getDate().getMonth() + "/" + meeting.getDate().getYear() + ", Time:" + meeting.getDate().gethour() + ":" + meeting.getDate().getminute() + " , Host: " + meeting.gethost().getname());
        }
    }

    @Override
    public String toString() {
        return "User: " + this.name + ", ID: " + this.id + ", Number of meetings attending: " + this.myMeetings.size() + ", Number of meetings organized: " + this.iOrganize.size();
    }
}