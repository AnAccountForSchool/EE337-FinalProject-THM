/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

import java.util.ArrayList;
import java.time.Month;
import jdk.jfr.Description;

/**
 *
 * @author rjbea
 */
public class Event {
    protected String EventName;
    protected java.time.LocalDate Day;
    protected java.time.LocalTime StartTime;
    protected java.time.Duration Duration;
    protected java.time.LocalTime EndTime;
    protected String Summary;
    protected String Description;
    protected String Location;
    private String ID;
    protected cms.EventStatus EventStatus;
    
    
    /**
     * 
     * @param ID
     * @param Name
     * @param Day
     * @param Month
     * @param Year
     * @param StartHour
     * @param StartMinute
     * @param EndHour
     * @param EndMinute
     * @param Summary
     * @param Description
     * @param Location 
     */
    public Event(String ID, String Name, int Day, int Month, int Year, int StartHour, 
            int StartMinute, int EndHour, int EndMinute, String Summary, String Description, String Location){
        
        // TODO: Validate
        java.time.LocalDate today = java.time.LocalDate.now();
        
        this.ID = ID;
        this.EventName = Name;
        this.Day = java.time.LocalDate.of(Year, Month, Day);
        this.StartTime = java.time.LocalTime.of(StartHour, StartMinute);
        this.EndTime = java.time.LocalTime.of(EndHour, EndMinute);
        this.Description = Description;
        this.Location = Location;
        this.Summary = Summary;
        
        if(this.Day.isAfter(today) == true){
            this.setEventStatus("Upcoming");
        } else {
            this.setEventStatus("Past");
        }
        
    }
    
    /**
     * 
     * @param ID
     * @param Name
     * @param Day
     * @param Month
     * @param Year
     * @param StartHour
     * @param StartMinute
     * @param EndHour
     * @param EndMinute
     * @param Summary
     * @param Description
     * @param Location
     * @param EventStatus 
     */
    public Event(String ID, String Name, int Day, int Month, int Year, 
            int StartHour, int StartMinute, int EndHour, int EndMinute, 
            String Summary, String Description, String Location, String EventStatus){
        
        this.ID = ID;
        this.EventName = Name;
        this.Day = java.time.LocalDate.of(Year, Month, Day);
        this.StartTime = java.time.LocalTime.of(StartHour, StartMinute);
        this.EndTime = java.time.LocalTime.of(EndHour, EndMinute);
        this.Summary = Summary;
        this.Description = Description;
        this.Location = Location;
        this.setEventStatus(EventStatus);
        
    }

    /**
     * 
     * @param status
     * @return 
     */    
    public boolean setEventStatus(String status){
        boolean results = false;
        java.time.LocalDate today = java.time.LocalDate.now();
        if(status.equalsIgnoreCase("Upcoming")){
            if(this.getDay().isAfter(today) ==false){
                System.out.println("Error: Cannot make events before current date 'Upcoming'");
            }else{
                this.setEventStatus(getEventStatus().Upcoming);
                results = true;
            }
        }else if(status.equalsIgnoreCase("Past")){
            this.setEventStatus(getEventStatus().Past);
            results = true;
        } else if(status.equalsIgnoreCase("Canceled")){
            this.setEventStatus(getEventStatus().Canceled);
            results = true;
        }
        return results;
    }
    
    /**
 * 
 * @param name
 * @return 
 */    
    public boolean setName(String name){
        boolean results = false;
        if((name == null)|| (name.length() <= 0)){
            // Do nothing. Return False
        } else{
            this.setEventName(name);
            results = true;
        }
        return results;
    }

    /**
 * 
 * @return 
 */    
    public boolean Cancel(){
        this.setEventStatus(getEventStatus().Canceled);
        return true;
    }

    /**
 * 
 * @param Day
 * @param Month
 * @param Year
 * @return 
 */    
    public boolean setDate(int Day, int Month, int Year){
        boolean results = false;
        java.time.LocalDate today = java.time.LocalDate.now();
        
        this.setDay(java.time.LocalDate.of(Year, Month, Day));
        results = true;
        if(this.getDay().isAfter(today) == true){
            this.setEventStatus("Upcoming");
        } else {
            this.setEventStatus("Past");
        }
        
        return results;
    }

    /**
 * 
 * @return 
 */    
    public String getID(){
        return this.ID;
    }

    /**
     * 
     * @return 
     */
    public String toString(){
        String results = "";
        results += "BEGIN:"+this.getEventName()+'\n';
        results += "DTSTART:"+this.getDay()+"t"+this.getStartTime()+'\n';
        results += "DTEND:"+this.getDay()+"t"+this.getEndTime()+'\n';
        results += "SUMMARY:"+this.getSummary()+'\n';
        results += "DESCRIPTION:"+this.getDescription()+'\n';
        results += "LOCATION:"+this.getLocation()+'\n';
        results += "UID:"+this.ID+'\n';
        results += "END:"+this.getEventName()+'\n';
        
        return results;
    }
    
    /**
     * 
     * @return 
     */
    public String toICS(){
        String results = "BEGIN:VCALENDAR" +'\n';
        results+= "VERSION:2.0" +'\n';
        results+= "CALSCALE:UNKNOWN" +'\n';
        results+= this.toString();
        results+= "END:VCALENDAR"+'\n';
        return results;
        
    }
    
    /**
     * 
     * @param event
     * @return 
     */
    public static Event fromICS(String event) {
        String eventName = extractValue(event, "BEGIN:");
        String startDate = extractValue(event, "DTSTART:");
        String endDate = extractValue(event, "DTEND:");
        String summary = extractValue(event, "SUMMARY:");
        String description = extractValue(event, "DESCRIPTION:");
        String location = extractValue(event, "LOCATION:");
        String id = extractValue(event, "UID:");

        String[] start = startDate.split("t");
        String[] sDate = extractDate(start[0]);
        
        String[] sTime = extractTime(start[1]);
        String[] end = endDate.split("t");
        String[] eTime = extractTime(end[1]);
        cms.Event current;
        current = new cms.Event(id, eventName, java.lang.Integer.parseInt(sDate[2]), 
                java.lang.Integer.parseInt(sDate[1]) , java.lang.Integer.parseInt(sDate[0]), 
                java.lang.Integer.parseInt(sTime[0]), java.lang.Integer.parseInt(sTime[1]), 
                java.lang.Integer.parseInt(eTime[0]), java.lang.Integer.parseInt(eTime[1]), 
                summary, description, location);
        
        
        return current;
    }
    
    /**
     * 
     * @param localString
     * @return 
     */
    private static String[] extractDate(String localString){
        return localString.split("-");
    }
    
    /**
     * 
     * @param localString
     * @return 
     */
    private static String[] extractTime(String localString){
        return localString.split(":");
    }

    /**
     * 
     * @param event
     * @param propertyName
     * @return 
     */
    private static String extractValue(String event, String propertyName) {
        String[] lines = event.split("\\r?\\n");
        for (String line : lines) {
            if (line.startsWith(propertyName)) {
                return line.substring(propertyName.length());
            }
        }
        return null;
    }

    /**
     * @return the EventName
     */
    public String getEventName() {
        return EventName;
    }

    /**
     * @param EventName the EventName to set
     */
    public void setEventName(String EventName) {
        this.EventName = EventName;
    }

    /**
     * @return the Day
     */
    public java.time.LocalDate getDay() {
        return Day;
    }

    /**
     * @param Day the Day to set
     */
    public void setDay(java.time.LocalDate Day) {
        this.Day = Day;
    }

    /**
     * @return the StartTime
     */
    public java.time.LocalTime getStartTime() {
        return StartTime;
    }

    /**
     * @param StartTime the StartTime to set
     */
    public void setStartTime(java.time.LocalTime StartTime) {
        this.StartTime = StartTime;
    }

    /**
     * @return the Duration
     */
    public java.time.Duration getDuration() {
        return Duration;
    }

    /**
     * @param Duration the Duration to set
     */
    public void setDuration(java.time.Duration Duration) {
        this.Duration = Duration;
    }

    /**
     * @return the EndTime
     */
    public java.time.LocalTime getEndTime() {
        return EndTime;
    }

    /**
     * @param EndTime the EndTime to set
     */
    public void setEndTime(java.time.LocalTime EndTime) {
        this.EndTime = EndTime;
    }

    /**
     * @return the Summary
     */
    public String getSummary() {
        return Summary;
    }

    /**
     * @param Summary the Summary to set
     */
    public void setSummary(String Summary) {
        this.Summary = Summary;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the Location
     */
    public String getLocation() {
        return Location;
    }

    /**
     * @param Location the Location to set
     */
    public void setLocation(String Location) {
        this.Location = Location;
    }

    /**
     * @return the EventStatus
     */
    public cms.EventStatus getEventStatus() {
        return EventStatus;
    }

    /**
     * @param EventStatus the EventStatus to set
     */
    public void setEventStatus(cms.EventStatus EventStatus) {
        this.EventStatus = EventStatus;
    }

}

enum EventStatus{
    
    Upcoming,
    Past,
    Canceled;
    
}
