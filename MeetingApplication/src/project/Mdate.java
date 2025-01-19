package project;

public class Mdate {
    private String day , month , timeZone;
    private int year  , hour , minute;
        Mdate(String day , String month , int year , String timeZone ,int hour ,int minute){
             this.month = month;
             this.day = day;
             this.year = year;
             this.timeZone= timeZone;
             this.hour = hour;
             this.minute = minute;
    }
    @Override
    public boolean equals(Object obj){
      if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mdate date = (Mdate) obj;
        return day.equals(date.day) && month.equals(date.month) && year == date.year && timeZone.equals(date.timeZone) && hour == date.hour && minute == date.minute;
    }
    
    public String getDay() {
        return day;
    }
    public String getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    public String gettimeZone() {
        return timeZone;
    }
    public int gethour() {
        return hour;
    }
    public int getminute() {
        return minute;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public void setmonth(String month) {
        this.month = month;
    }
    public void setyear(int year) {
        this.year = year;
    }
    public void settimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
    public void sethour(int hour) {
        this.hour = hour;
    }
    public void setminute(int minute) {
        this.minute = minute;
    }
     @Override
    public String toString() {
        return "Timezone: " + this.timeZone + ", Minute: " + this.minute + ", Hour: " + this.hour +  ", Day: " + this.day + ", Month: " + this.month + ", Year: " + this.year;
    }
}