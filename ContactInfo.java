package CMS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author rjbea
 */

/**
 * 
 * TODO
 *      fromXML
 *      
 * 
 * @author rjbea
 */


public class ContactInfo {
    
    private String ID;
    private String CompanyName;
    private String Name;
    // private String PhoneNumber; // format matters!
    private String Email; // format matters!
    private String Position; // right now it is a string... could be enum?
    private String Address;
    private String City;
    private String State;
    private String Zipcode; // format matters!
    private CMS.ContactType ContactType;
    private java.time.LocalDate MeetingDate;
    private CMS.ContactStatus ContactStatus;
    private CMS.SystemStatus SystemStatus;
    
    
    private ContactInfo(String ContactID){
        this.ID = ContactID;
        this.CompanyName ="";
        this.Name ="";
        this.Email ="";
        this.Position ="";
        this.Address ="";
        this.City ="";
        this.State ="";
        this.Zipcode ="";
        this.MeetingDate = java.time.LocalDate.now();
        
        this.ContactType = CMS.ContactType.Unknown;
        this.ContactStatus = CMS.ContactStatus.Unknown;
        this.SystemStatus = CMS.SystemStatus.Unkown;
     
    
        
    }

    
    public ContactInfo(String ContactID, String CompanyName, String Name, String Email){
        this(ContactID);
        
        
        if(CompanyName.length()>0){
            this.CompanyName = CompanyName;
        } else{

        }        
        if((Name.length()>0)){
            this.Name = Name;
        } else{
            System.out.println("Error: invalid name");
        }
        
        if((Email.contains("@")== true) & ( Email.contains(".") == true)){
            this.Email = Email;
        } else{
            System.out.println("Error: invalid email.");
        }        
    }    
    
    
    public ContactInfo(String contactID, String companyName, String name, 
            String email, String contactStatus){

        this(contactID, companyName, name, email);

        
    }    

    public CMS.ContactStatus getContactStatus(){
        
        return this.ContactStatus;
        
    }
    
    public CMS.SystemStatus getSystemStatus(){
        
        return this.SystemStatus;
        
    }
    
    public void setContactStatus(String contactStat){
        if(contactStat.equalsIgnoreCase("Archive")){
            this.ContactStatus = CMS.ContactStatus.Archive;
        } else if(contactStat.equalsIgnoreCase("Current")){
            this.ContactStatus = CMS.ContactStatus.Current;
        } else if(contactStat.equalsIgnoreCase("New")){
            this.ContactStatus = CMS.ContactStatus.New;
        } else{
            this.ContactStatus = CMS.ContactStatus.Unknown;
        }
    }
    
    public void setSystemStatus(String Stat){
        if(Stat.equalsIgnoreCase("Valid")){
            this.SystemStatus = CMS.SystemStatus.Valid;
        } else if(Stat.equalsIgnoreCase("Invalid")){
            this.SystemStatus = CMS.SystemStatus.Invalid;
        }else{
            this.SystemStatus = CMS.SystemStatus.Unkown;
        }
    }
    
    /**
     * @return the ID
     */
    public String getID() {

        return( this.ID );

    }
    
    public boolean validate(){
        boolean validated = true;
                
        if(this.CompanyName.length()<=0){

            validated = false;
            System.out.println("Invalid CompanyName");
            
        }
        if(this.Name.length()<=0){
            validated = false;
            System.out.println("Invalid Name");
        }
        if((this.Email.length()<=5)|(this.Email.contains("@")==false)|
                (this.Email.contains(".")==false)|(this.Email.contains(","))){
          validated = false;
          System.out.println();
        }
        return validated;
    }

    /**
     * @param CompanyName the CompanyName to set
     * @return 
     */
    public boolean setCompanyName(String CompanyName) {
        // validate companyname before blindly setting it...
        boolean results = false;
        
        if(( CompanyName.length()>0)& (CompanyName.contains(",")== false)){
            this.CompanyName = CompanyName;
            results = true;
        } else{
            System.out.println("Error: Invalid Company Name");
        }

        return(results);
        
    }

    /**
     * @return the CompanyName
     */
    public String getCompanyName() {
        return CompanyName;
    }    
    
    /**
     * @return the Name
     */
    public String getName() {

        return this.Name;
    }
    

    /**
     * @param Name the Name to set
     * @return 
     */
    public boolean setName(String Name) {
        boolean results = false;
        if(Name.length() > 0){
            this.Name = Name;
            results=true;
        } else{
            System.out.println("Error: Invalid name");
        }        
        return(results);
    }
    

    
 
    /**
     * @return the Email
     */
    public String getEmail() {
        return this.Email;
    }

    /**
     * @param Email the Email to set
     * @return 
     */
    public boolean setEmail(String Email) {
        boolean results = false;
        if((Email.contains("@") == true)&(Email.contains(".")== true)){
            this.Email = Email;
            results = true;
        } else{
            System.out.println("Error: Invalid Email");
        }        

        return(results);
    }

    /**
     * @return the Position
     */
    public String getPosition() {
            return this.Position;        
    }

    /**
     * @param Position the Position to set
     * @return 
     */
    public boolean setPosition(String Position) {
        boolean results = false;

        if(Position.length() > 0 ){
            this.Position = Position;
            results = true;
        } else{
            System.out.println("Error: Invalid Position");
        }        
        
        return(results);
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return this.Address;
    }

    /**
     * @param Address the Address to set
     * @return 
     */
    public boolean setAddress(String Address) {
        boolean results = false;
        if(Address.length() > 0 ){
            this.Address = Address;
            results = true;
        } else{
            System.out.println("Error: Invalid Address");
        }        
        return(results);
    }

    /**
     * @return the City
     */
    public String getCity() {
        return this.City;
    }

    /**
     * @param City the City to set
     * @return 
     */
    public boolean setCity(String City) {
        boolean results = false;
        if(City.length()>0){
            this.City = City;
            results = true;
        } else{
            System.out.println("Error: Invalid City Name");
        }
        return(results);
    }

    /**
     * @return the State
     */
    public String getState() {
        return State;
    }

    /**
     * @param State the State to set
     * @return 
     */
    public boolean setState(String State) {
        boolean results = false;
        if(State.length()>0){
            this.State = State;
            results = true;
        } else{
            System.out.println("Error: Invalid State");
        }
        return(results);
    }

    /**
     * @return the Zipcode
     */
    public String getZipcode() {
        return Zipcode;
    }

    /**
     * @param Zipcode the Zipcode to set
     * @return 
     */
    public boolean setZipcode(String Zipcode) {
        boolean results = false;
        if(Zipcode.length()>0){
        this.Zipcode = Zipcode;
        results = true;
        } else{
            System.out.println("Error: Invalid Zipcode");
        }
        return(results);
    }

    /**
     * @return the MeetingDate
     */
    public java.time.LocalDate getMeetingDate() {




        return MeetingDate;        
    }

    /**
     * @param MeetingDate the MeetingDate to set
     * @return 
     */
    public boolean setMeetingDate(String date) {
        String chunks[] = date.split("-");
        boolean results = false;
        if(chunks.length==3){
            int year = java.lang.Integer.parseInt(chunks[0]);
            int month = java.lang.Integer.parseInt(chunks[1]);
            int day = java.lang.Integer.parseInt(chunks[2]);
            results = true;
            this.MeetingDate = java.time.LocalDate.of(year, month, day);
        } else{
            
        }
            
        return(true);
    }
    
    
    public static CMS.ContactInfo fromCSV(String line) throws java.lang.Exception{
        String[] chunks;
        CMS.ContactInfo Results;
        String ID;
        String CompanyName;
        String Name;
        String Position;
        String Address;
        String City;
        String State;
        String Zipcode;
        String Email;
        String MeetingDate;
        String ContactStatus;
        String SystemStatus;

        
        if((line == null)|| (line.length()==0)){
            throw new Exception("Error: invalid csv string");
        } else{
            chunks = line.split(",");
            if(chunks.length == 12){
                ID = chunks[0];
                CompanyName = chunks[1];
                Name = chunks[2];
                Email = chunks[3];
                Address = chunks[4];
                City = chunks[5];
                State = chunks[6];
                Zipcode = chunks[7];
                Position = chunks[8];
                MeetingDate = chunks[9];
                ContactStatus = chunks[10];
                SystemStatus = chunks[11];
                
            } else{
                throw new Exception("Error: invalid csv string");
            }
        }
        
        Results = new CMS.ContactInfo(ID, CompanyName, Name, Email, ContactStatus);
        
        
        if((Address.equalsIgnoreCase("")== true)){
        } else{        
            Results.setAddress(Address);
        }
        
        if((City.equalsIgnoreCase(" ")== true)){

        } else{
            Results.setCity(City);
        }
        
        if((State.equalsIgnoreCase(" ")== true)){
        } else{
            Results.setState(State);
        }
        
        if((Zipcode.equalsIgnoreCase(" ")== true)){

        } else{
            Results.setZipcode(Zipcode);
        }
        
        if((Position.equalsIgnoreCase(" ")== true)){

        } else{
            Results.setPosition(Position);
        }
        
        if((MeetingDate == null)){
        } else{
        }
        
        if((ContactStatus == null)){
        } else{
        }
        
        if((SystemStatus == null)){
        } else{
        }
        
        return Results;
    }
    
    
    
    public void setContactStatus(CMS.ContactStatus Status){
        
        this.ContactStatus = Status;
        
    }
    
    
    
    
    public String toCSV(){
        String results ="";
        
        
        
        results += this.ID +",";
        if((this.CompanyName.equalsIgnoreCase("")== true)||(this.CompanyName == null)){
            results += " ,";        
        } else{    
            results += this.CompanyName +",";
        }
        
        if((this.Name.equalsIgnoreCase("")== true)||(this.Name == null)){
            results += " ,";
        } else{
            results += this.Name +",";
        }
        
        if((this.Email.equalsIgnoreCase("")==true)||(this.Email == null)){
            
        } else{
            results += this.Email + ",";
        }
        
        if((this.Address.equalsIgnoreCase("")== true)||(this.Address == null)){
            results += " ,";
        } else{        
            results += this.Address +",";
        }
        
        if((this.City.equalsIgnoreCase("")== true)||(this.City == null)){
            results += " ,";
        } else{
            results += this.City +",";
        }
        
        if((this.State.equalsIgnoreCase("")== true)||(this.State == null)){
            results += " ,";
        } else{
            results += this.State +",";
        }
        
        if((this.Zipcode.equalsIgnoreCase("")== true)||(this.Zipcode == null)){
            results += " ,";
        } else{
            results += this.Zipcode +",";
        }
        
        if((this.Position.equalsIgnoreCase("")== true)||(this.Position == null)){
            results += " ,";
        } else{
            results += this.Position +",";
        }
        
        if((this.MeetingDate == null)){
            results += " ,";
        } else{
            results += this.MeetingDate.toString() +",";
        }
        
        if((this.ContactStatus == null)){
            results += " ,";
        } else{
            results += this.ContactStatus +",";
        }
        
        if((this.SystemStatus == null)){
            results += " ,";
        } else{
            results +=  this.SystemStatus + "\n";
        }
        
        return(results);
    } 
    

    
    public String toCustom(){
        String results ="";
        results += "ID: " + this.ID + '\n';
        results += "CompanyName: " + this.CompanyName + '\n';
        results += "Name: " + this.Name + '\n';
        results += "Email: " + this.Email + '\n';
        results += "Address: " + this.Address + '\n';
        results += "City: " + this.City + '\n';
        results += "State: " + this.State + '\n';
        results += "Zipcode: " + this.Zipcode + '\n';
        results += "Position: " + this.Position + '\n';
        results += "Meeting Date: " + this.MeetingDate.toString() + '\n';
        results += "ContactStatus: " + this.ContactStatus.toString() + '\n';
        results += "SystemStatus: " + this.SystemStatus.toString() + '\n';
        
        return(results);
    }

    public String toXML(){
        String results = "";
        results += "<ContactInfo>" + '\n';
        results += "  <ID>"+this.ID+"</ID>"+'\n';
        results += "  <CompanyName>"+this.CompanyName+"</CompanyName>"+'\n';
        results += "  <Name>"+this.Name+"</Name>"+'\n';
        results += "  <Email>"+this.Email+"</Email>"+'\n';
        results += "  <Address>"+this.Address+"</Address>"+'\n';
        results += "  <City>"+this.City+"</City>"+'\n';
        results += "  <State>"+this.State+"</State>"+'\n';
        results += "  <Zipcode>"+this.Zipcode+"</Zipcode>"+'\n';
        results += "  <Position>"+this.Position+"</Position>"+'\n';
        results += "  <MeetingDate>"+this.MeetingDate+"</MeetingDate>"+'\n';
        results += "  <ContactStatus>"+this.ContactStatus.toString()+"</ContactStatus>"+'\n';
        results += "  <SystemStatus>"+this.SystemStatus.toString()+"</SystemStatus>"+'\n';
        results +="</ContactInfo>" + '\n';
        return (results);
    }
    
    public CMS.ContactInfo fromXML(String Text){
        String ID = "";
        String CompanyName = "";
        String Name = "";
        String Email = "";
        String Address = "";
        String City = "";
        String State = "";
        String Zipcode = "";
        String Position = "";
        String MeetingDate = "";
        String ContactStatus = "";
        String SystemStatus = "";

        Pattern IDPat = Pattern.compile("<ID>([^<]+)</ID>");
        Pattern CNPat = Pattern.compile("<CompanyName>([^<]+)</CompanyName>");
        Pattern NamPat = Pattern.compile("<Name>([^<]+)</Name>");
        Pattern EmPat = Pattern.compile("<Email>([^<]+)</Email>");
        Pattern AddPat = Pattern.compile("<Address>([^<]+)</Address>");
        Pattern CitPat = Pattern.compile("<City>([^<]+)</City>");
        Pattern StatePat = Pattern.compile("<State>([^<]+)</State>");
        Pattern ZipPat = Pattern.compile("<Zipcode>([^<]+)</Zipcode>");
        Pattern PosPat = Pattern.compile("<Position>([^<]+)</Position>");
        Pattern MDPat = Pattern.compile("<MeetingDate>([^<]+)</MeetingDate>");
        Pattern CSPat = Pattern.compile("<ContactStatus>([^<]+)</ContactStatus>");
        Pattern SSPat = Pattern.compile("<SystemStatus>([^<]+)</SystemStatus>");

        java.util.regex.Matcher matcher;

        matcher = IDPat.matcher(Text);
        if (matcher.find()) {
            ID = matcher.group(1);
        }

        matcher = CNPat.matcher(Text);
        if (matcher.find()) {
            CompanyName = matcher.group(1);
        }

        matcher = NamPat.matcher(Text);
        if (matcher.find()) {
            Name = matcher.group(1);
        }

        matcher = EmPat.matcher(Text);
        if (matcher.find()) {
            Email = matcher.group(1);
        }

        matcher = AddPat.matcher(Text);
        if (matcher.find()) {
            Address = matcher.group(1);
        }

        matcher = CitPat.matcher(Text);
        if (matcher.find()) {
            City = matcher.group(1);
        }

        matcher = StatePat.matcher(Text);
        if (matcher.find()) {
            State = matcher.group(1);
        }

        matcher = ZipPat.matcher(Text);
        if (matcher.find()) {
            Zipcode = matcher.group(1);
        }

        matcher = PosPat.matcher(Text);
        if (matcher.find()) {
            Position = matcher.group(1);
        }

        matcher = MDPat.matcher(Text);
        if (matcher.find()) {
            MeetingDate = matcher.group(1);
        }

        matcher = CSPat.matcher(Text);
        if (matcher.find()) {
            ContactStatus = matcher.group(1);
        }

        matcher = SSPat.matcher(Text);
        if (matcher.find()) {
            SystemStatus = matcher.group(1);
        }
        
        this.ID = ID;
        this.Name = Name;
        this.CompanyName = CompanyName;
        this.Email = Email;
        this.Address = Address;
        this.City = City;
        this.State = State;
        this.Zipcode = Zipcode;
        this.Position = Position;
        this.setContactStatus(ContactStatus);
        this.setSystemStatus(SystemStatus);
        
        this.setMeetingDate(MeetingDate);
        
        return null;
    }
    
    public String toString(String Format){
        String results = "";
        
        if((Format==null)||(Format.length() <=0)){
            
        } else if(Format.equalsIgnoreCase("CSV")==true){
            results = this.toCSV();
        } else if(Format.equalsIgnoreCase("XML")==true){
            results = this.toXML();
        } else if(Format.equalsIgnoreCase("CUSTOM")==true){
            results = this.toCustom();
        } else if(Format.equalsIgnoreCase("JSON")==true){
            // results = this.toJSON();
            results = this.toString();
        } else{
            results = this.toString();
        }
        
        return(results);
    }
    
 public static CMS.ContactInfo fromCustom(String text) throws java.lang.Exception{
        CMS.ContactInfo Results;
        String[] Lines;
        String[] Chunks;
        /*
        ID: 00001
        CompanyName: UAB
        Name: Robert Beatty
        Address: 
        City: 
        State: 
        Zipcode: 
        Position: 
        MeetingDate:
        ContactStatus: 
        SystemStatus:                 
        */
        String ID = "";
        String CompanyName = "";
        String Name = "";
        String Email = "";
        String Address = "";
        String City = "";
        String State = "";
        String Zipcode = "";
        String Position = "";
        String date ="";
        String CS = "";
        String SS = "";
        if(text == null){
            System.out.println("Error: Bad Text File");
            System.out.println("CMS.ContactInfo.fromCustom()");
        } else{
            Lines = text.split("\n");
            System.out.println("Printing text");
            System.out.println(text);
            if(Lines.length == 11){
                
                int count = 0;                
                while(count<=11){
                    Chunks = Lines[count].split(": ");
                    System.out.println(Chunks[1]);
                    System.out.println(Chunks[0]);
                    
                    if(Chunks.length>=2){
                        if(Chunks[0].equalsIgnoreCase("ID")==true){
                            ID = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("CompanyName")==true){
                            CompanyName = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("Name")==true){
                            Name = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("Email")==true){
                            Email = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("Address")){
                            Address = Chunks[1];
                        }else if(Chunks[0].equalsIgnoreCase("City") == true){
                            City = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("State")==true){
                            State = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("Zipcode")==true){
                            Zipcode = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("Position")==true){
                            Position = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("MeetingDate")==true){
                            date = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("ContactStatus")==true){
                            CS = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("SystemStatus")==true){
                            SS = Chunks[1];
                        } else{
                            System.out.println("CMS.ContactInfo.fromCustom()");
                            System.out.println("Unhandled MetaData Or Bad Format");
                        }
                    }
                    count++;
                }// end while loop                    
            } else{
                System.out.println("Wrong Line Cout: "+ Lines.length);
                System.out.println("CMS.ContactInfo.fromCustom()");
            } // end second if/else switch if(Lines.length==11)
            
            
        }
        
        if((ID.length()>0)&(Name.length()>0)&(CompanyName.length()>0)&
                (Email.contains("@")==true)&(Email.contains(".")==true)){
            Results = new CMS.ContactInfo(ID,CompanyName,Name,Email);
            Results.setAddress(Address);
            Results.setCity(City);
            Results.setState(State);
            Results.setZipcode(Zipcode);
            Results.setPosition(Position);
            Results.setMeetingDate(date);
            Results.setContactStatus(CS);
            Results.setSystemStatus(SS);
        } else{
            Results = null;
                    
        }
        
        if(Results != null){
            return Results;
        }else{
        
        //System.out.println("Error: Null Resutts in fromCustom");
            return null;
        }
    }
    
}



enum ContactStatus{
    New,
    Current,
    Archive,
    Unknown;
}

enum SystemStatus{
    Valid,
    Invalid,
    Unkown;
}

enum ContactType{
    Sales,
    Management,
    Engineer,
    Support,
    Unknown;
}
