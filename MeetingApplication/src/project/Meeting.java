package project;

import java.util.ArrayList;

public class Meeting {
    private  Mdate date;
    private ArrayList<Person> attendees;
    private final Person host;
    private boolean isOnline;
    private String url;
    private String location;
    private String meetingName;

      Meeting( Mdate date ,String meetingName , Person host , boolean isOnline, String url, String location){
        this.date = date;
        this.host = host;
        this.isOnline = isOnline;
        this.url = url;
        this.location = location;
       this.meetingName = meetingName;
        attendees = new ArrayList<>();
    }

   public Mdate getDate() {
        return date;
    }
    public ArrayList<Person> getattendees() {
        return attendees;
    }
    public Person gethost() {
        return host;
    }
    public boolean isOnline() {
        return isOnline;
    }
    public String geturl() {
        return url;
    }
    public String getlocation() {
        return location;
    }
     public String getMeetingName() {
        return meetingName;
    }
    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }
   public void setDate(Mdate date) {
        this.date = date;
    }
    public void setattendees(ArrayList<Person> attendees ) {
        this.attendees = attendees;
    }
    public void setisOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }
    public void seturl(String url) {
        this.url = url;
    }
    public void setlocation(String location) {
        this.location = location;
    }

    @Override
   public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Meeting meeting = (Meeting) obj;
        return  date.equals(meeting.date) && host.equals(meeting.host) &&
                ((url != null ? url.equals(meeting.url) : meeting.url == null) &&
                (location != null ? location.equals(meeting.location) : meeting.location == null))  && attendees.equals(meeting.attendees);
    }

    public boolean addAttendee(Person person){
      if(attendees.contains(person)){
          return false;
        }
      attendees.add(person);
      return true;
    }
    public boolean removeAttendee(Person person){
        if (attendees.remove(person)){
          return true;
        }
      return false;
    }

     public void removeAllAttendee() {
        attendees.clear();
     }

    @Override
  public String toString() {
        return "Host: " + this.host.getname() + ", Date: " + this.date.toString() +
                ", Online: " + (this.isOnline ? "Yes" : "No") + ", Location: " + (this.isOnline ? this.url : this.location) +
                ", Attendees: " + attendees;
    }
}