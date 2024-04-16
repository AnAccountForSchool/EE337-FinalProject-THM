/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

import java.time.Month;

/**
 *
 * @author rjbea
 */
public class Event {
    private String EventName;
    private java.time.LocalDate Day;
    private java.time.LocalTime StartTime;
    private java.time.Duration Duration;
    private java.time.LocalTime EndTime;
    private String Summary;
    private String Description;
    private String Location;
    private String ID;
    private cms.EventStatus EventStatus;
    
    public Event(String Name, int Day, int Month, int Year, int StartHour, 
            int StartMinute, long DurationHour){
        
        // TODO: Validate
        java.time.LocalDate today = java.time.LocalDate.now();
        
        this.EventName = Name;
        this.Day = java.time.LocalDate.of(Year, Month, Month);
        this.StartTime = java.time.LocalTime.of(StartHour, StartMinute);
        this.Duration = java.time.Duration.ofHours(DurationHour);
        this.EndTime = this.StartTime.plusHours(DurationHour);
        
        
        if(this.Day.isAfter(today) == true){
            this.setEventStatus("Upcoming");
        } else {
            this.setEventStatus("Past");
        }
        
    }
    
    public boolean setEventStatus(String status){
        boolean results = false;
        java.time.LocalDate today = java.time.LocalDate.now();
        if(status.equalsIgnoreCase("Upcoming")){
            if(this.Day.isAfter(today) ==false){
                System.out.println("Error: Cannot make events before current date 'Upcoming'");
            }else{
                this.EventStatus = EventStatus.Upcoming;
                results = true;
            }
        }else if(status.equalsIgnoreCase("Past")){
            this.EventStatus = EventStatus.Past;
            results = true;
        } else if(status.equalsIgnoreCase("Canceled")){
            this.EventStatus = EventStatus.Cancled;
            results = true;
        }
        return results;
    }
    
    public boolean setName(String name){
        boolean results = false;
        if((name == null)|| (name.length() <= 0)){
            // Do nothing. Return False
        } else{
            this.EventName = name;
            results = true;
        }
        return results;
    }
    
    public boolean Cancel(){
        this.EventStatus = EventStatus.Cancled;
        return true;
    }
    
    public boolean setDate(int Day, int Month, int Year){
        boolean results = false;
        java.time.LocalDate today = java.time.LocalDate.now();
        
        this.Day = java.time.LocalDate.of(Year, Month, Month);
        
        if(this.Day.isAfter(today) == true){
            this.setEventStatus("Upcoming");
        } else {
            this.setEventStatus("Past");
        }
        
        return results;
    }

    public String toString(){
        String results = "";
        results += "BEGIN:"+this.EventName+'\n';
        results += "DTSTART:"+this.Day+"t"+this.StartTime+'\n';
        results += "DTEND:"+this.Day+"t"+this.EndTime+'\n';
        results += "SUMMARY:"+this.Summary+'\n';
        results += "DESCRIPTION:"+this.Description+'\n';
        results += "LOCATION:"+this.Location+'\n';
        results += "UID:"+this.ID+'\n';
        results += "END:"+this.EventName+'\n';
        
        return results;
    }
    
    public String toICS(){
        String results = "BEGIN:VCALENDAR" +'\n';
        results+= "VERSION:2.0" +'\n';
        results+= "CALSCALE:UNKNOWN" +'\n';
        results+= this.toString();
        results+= "END:VCALENDAR"+'\n';
        return results;
        
    }
    

}

    enum EventStatus{
    
    Upcoming,
    Past,
    Cancled;
    
}
