/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

import java.util.regex.Pattern;



/**
 *
 * @author rjbea
 */
public class ContactInfo {
    private final String ID;
    protected String CompanyName;
    protected String FirstName;
    protected String LastName;
    protected String Email; // format matters!
    public cms.Position Position;
    protected String Address;
    protected String City;
    protected String State;
    protected String Zipcode; // format matters!
    public cms.ContactStatus ContactStatus;
    protected cms.SystemStatus SystemStatus;

    
    public ContactInfo(String ID, String CompanyName, String FirstName, String LastName, String Email){
            
            //System.out.println("Constructor");
        
            this.ID = ID;
            this.CompanyName = CompanyName;
            this.FirstName = FirstName;
            this.LastName = LastName;
            this.Email = Email;
            this.Position = cms.Position.Unknown;
            this.ContactStatus = cms.ContactStatus.Unknown;
            this.SystemStatus = cms.SystemStatus.Unknown;
            this.Validate();
        
        
        
    }
    
    public boolean Validate(){
        java.util.ArrayList<Boolean> Results = new java.util.ArrayList<Boolean>();
        
        if((this.CompanyName==null)||(this.CompanyName.length()<=0)){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        // Results[1] = FirstName
        if((this.FirstName==null)||(this.FirstName.length()<=0)){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        // Results[2] = LastName
        if((this.LastName == null)||(this.LastName.length()<=0)){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        // Results[3] = Email
        if((this.Email == null)||((this.Email.length()<5)||
                (this.Email.contains("@")==false)||(this.Email.contains(".")==false))){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        if(Results.contains(false)==true){
            this.SystemStatus = cms.SystemStatus.Invalid;
        } else{
            this.SystemStatus = cms.SystemStatus.Valid;
        }
        
        return false;
    }
    
    public java.util.ArrayList<Boolean> ValidateAll(){
        java.util.ArrayList<Boolean> Results = new java.util.ArrayList<Boolean>();
        
        // Results[0] = CompanyName
        if((this.CompanyName==null)||(this.CompanyName.length()<=0)){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        // Results[1] = FirstName
        if((this.FirstName==null)||(this.FirstName.length()<=0)){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        // Results[2] = LastName
        if((this.LastName == null)||(this.LastName.length()<=0)){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        // Results[3] = Email
        if((this.Email == null)||((this.Email.length()<5)||
                (this.Email.contains("@")==false)||(this.Email.contains(".")==false))){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        // Results[4] = Position
        if(this.Position == cms.Position.Unknown){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        // Results[5] = Address
        if((this.Address == null)||(this.Address.equals("")==true)||
                (this.Address.equals(" ")==true)){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        // Results[6] = City
        if((this.City == null)||(this.City.equals("")==true)||
                (this.City.equals(" ")==true)){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        // Results[7] = State
        if((this.State == null)||(this.State.equals("")==true)||
                (this.State.equals(" ")==true)){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        // Results[8] = Zipcode
        if((this.Zipcode == null)||(this.Zipcode.equals("")==true)||
                (this.Zipcode.equals(" ")==true)){
            Results.add(false);
        } else{
            Results.add(true);
        }
        
        // Results[9] = ContactStatus
        if(this.ContactStatus == ContactStatus.Unknown){
            Results.add(false);
        } else{ 
            Results.add(true);
        }
        
        if((Results.get(0)== true)&&(Results.get(1)==true)&&
                (Results.get(2)==true)&& (Results.get(3)==true)&&(Results.get(4)==true)){
            this.SystemStatus = cms.SystemStatus.Valid;
        } else{
            this.SystemStatus = cms.SystemStatus.Invalid;
        }
        
        return Results;
    } 
    
    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @return the CompanyName
     */
    public String getCompanyName() {
        return CompanyName;
    }

    /**
     * @param CompanyName the CompanyName to set
     * @return 
     */
    public boolean setCompanyName(String CompanyName) {
        if((CompanyName == null)){
            System.out.println("CompanyName must not be null");
            return false;
        } else{
            this.CompanyName = CompanyName;
            return true;
        }
    }

    /**
     * @return the FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * @param FirstName the FirstName to set
     * @return 
     */
    public boolean setFirstName(String FirstName) {
        if((FirstName == null)){
            System.out.println("FirstName must not be null");
            return false;
        } else{
            this.FirstName = FirstName;
            return true;
        }
    }

    /**
     * @return the LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * @param LastName the LastName to set
     * @return 
     */
    public boolean setLastName(String LastName) {
        if((LastName == null)){
            System.out.println("LastName must not be null");
            return false;
        } else{
            this.LastName = LastName;
            return true;
        }
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     * @return 
     */
    public boolean setEmail(String Email) {
        if((Email.contains("@")==false)||(Email.contains(".")==false)||(Email.length()<4)){
            System.out.println("Error: Inavalid Email");
            return false;
        }else{
            this.Email = Email;
            return true;
        }
    }

    /**
     * @return the Position
     */
    public cms.Position getPosition() {
        return Position;
    }

    /**
     * @param position
     * @return tion to set
     */
    public boolean setPosition(String position) {
        //System.out.println("setPosition");
        //System.out.println(position);
        
        if(position.equalsIgnoreCase("Customer")==true){
            this.Position = cms.Position.Customer;
            return true;
        } else if(position.equalsIgnoreCase("Engineer")==true){
            this.Position = cms.Position.Engineer;
            return true;
        } else if(position.equalsIgnoreCase("Manager")==true){
            this.Position = cms.Position.Manager;
            return true;
        } else if(position.equalsIgnoreCase("Student")==true){
            this.Position = cms.Position.Student;
            return true;
        } else if(position.equalsIgnoreCase("Unknown")==true){
            this.Position = cms.Position.Unknown;
            return true;
        }else {
            System.out.println("Error: Unhandled Position");
            return false;
        }
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     * @return 
     */
    public boolean setAddress(String Address) {
        if((Address == null)){
            System.out.println("Adress must not be null");
            return false;
        } else{
            this.Address = Address;
            return true;
        }
    }

    /**
     * @return the City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param City the City to set
     * @return 
     */
    public boolean setCity(String City) {
        if((City == null)){
            System.out.println("City must not be null");
            return false;
        } else{
            this.City = City;
            return true;
        }
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
        if((State == null)){
            System.out.println("State must not be null");
            return false;
        } else{
            this.State = State;
            return true;
        }
    }

    /**
     * @return the Zipcode
     */
    public String getZipcode() {
        return Zipcode;
    }

    /**
     * @param Zipcode the Zipcode to set
     */
    public void setZipcode(String Zipcode) {
        this.Zipcode = Zipcode;
    }

    /**
     * @return the ContactStatus
     */

    
    public cms.ContactStatus getContactStatus(){
        return this.ContactStatus;
    }

    /**
     * @param ContactStatus the ContactStatus to set
     * @return 
     */
    public boolean setContactStatus(String ContactStatus) {
        
        if(ContactStatus.equalsIgnoreCase("New")==true){
            this.ContactStatus = cms.ContactStatus.New;
            return true;
        } else if(ContactStatus.equalsIgnoreCase("Current")==true){
            this.ContactStatus = cms.ContactStatus.Current;
            return true;
        } else if(ContactStatus.equalsIgnoreCase("Archive")==true){
            this.ContactStatus = cms.ContactStatus.Archive;
            return true;
        } else if(ContactStatus.equalsIgnoreCase("Unknown")==true){
            this.ContactStatus = cms.ContactStatus.Unknown;
            return true;
        }else {
            System.out.println("Error: Unhandled Contact Status");
            return false;
        }
    }

    /**
     * @return the SystemStatus
     */
    public cms.SystemStatus getSystemStatus() {
        return SystemStatus;
    }
    
    // Importable
    
    /**
     * 
     * 
     * @param Text
     * @return 
     */
    public static cms.ContactInfo fromXML(String Text){
        
        //System.out.println("fromXML");
        
        String ID = "";
        String CompanyName = "";
        String FirstName = "";
        String LastName = "";
        String Email = "";
        String Position = "";
        String Address = "";
        String City = "";
        String State = "";
        String Zipcode = "";
        String ContactStatus = "";

        Pattern IDPat = Pattern.compile("<ID>([^<]+)</ID>");
        Pattern CNPat = Pattern.compile("<CompanyName>([^<]+)</CompanyName>");
        Pattern FirstNamPat = Pattern.compile("<FirstName>([^<]+)</FirstName>");
        Pattern LastNamPat = Pattern.compile("<LastName>([^<]+)</LastName>");
        Pattern EmPat = Pattern.compile("<Email>([^<]+)</Email>");
        Pattern PosPat = Pattern.compile("<Position>([^<]+)</Position>");
        Pattern AddPat = Pattern.compile("<Address>([^<]+)</Address>");
        Pattern CitPat = Pattern.compile("<City>([^<]+)</City>");
        Pattern StatePat = Pattern.compile("<State>([^<]+)</State>");
        Pattern ZipPat = Pattern.compile("<Zipcode>([^<]+)</Zipcode>");
        Pattern CSPat = Pattern.compile("<ContactStatus>([^<]+)</ContactStatus>");

        java.util.regex.Matcher matcher;

        matcher = IDPat.matcher(Text);
        if (matcher.find()) {
            ID = matcher.group(1);
        }

        matcher = CNPat.matcher(Text);
        if (matcher.find()) {
            CompanyName = matcher.group(1);
        }

        matcher = FirstNamPat.matcher(Text);
        if (matcher.find()) {
            FirstName = matcher.group(1);
        }
        
        matcher = LastNamPat.matcher(Text);
        if (matcher.find()) {
            LastName = matcher.group(1);
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


        matcher = CSPat.matcher(Text);
        if (matcher.find()) {
            ContactStatus = matcher.group(1);
        }
        try{
            cms.ContactInfo Results = new cms.ContactInfo(ID, CompanyName, FirstName, LastName, Email);
            
            Results.setPosition(Position);
            Results.setAddress(Address);
            Results.setCity(City);
            Results.setState(State);
            Results.setZipcode(Zipcode);
            Results.setContactStatus(ContactStatus);
            Results.Validate();
            //System.out.println(Results.toCustom());
            return Results;
        } catch(Exception ex){
            System.out.println(ex);
            cms.ContactInfo Results = null;
            return Results;
        }
        
    }
    
    /**
     * 
     * @param text
     * @return
     * @throws java.lang.Exception 
     */
    public static cms.ContactInfo fromCustom(String text) throws java.lang.Exception{
        String[] Lines;
        String[] Chunks;
        String ID = "";
        String CompanyName = "";
        String FirstName = "";
        String LastName = "";
        String Email = "";
        String Position = "";
        String Address = "";
        String City = "";
        String State = "";
        String Zipcode = "";
        String CS = "";
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
                        } else if(Chunks[0].equalsIgnoreCase("FirstName")==true){
                            FirstName = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("LastName")==true){
                            LastName = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("Email")==true){
                            Email = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("Position")==true){
                            Position = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("Address")){
                            Address = Chunks[1];
                        }else if(Chunks[0].equalsIgnoreCase("City") == true){
                            City = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("State")==true){
                            State = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("Zipcode")==true){
                            Zipcode = Chunks[1];
                        } else if(Chunks[0].equalsIgnoreCase("ContactStatus")==true){
                            CS = Chunks[1];
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
        
        if((ID.length()>0)&(FirstName.length()>0)&(LastName.length()>0)
                &(CompanyName.length()>0)&(Email.contains("@")==true)
                &(Email.contains(".")==true)){
           
            cms.ContactInfo Results = new cms.ContactInfo(ID, CompanyName, FirstName, LastName, Email);
            
            System.out.println(Results.toCustom());
            
            Results.setPosition(Position);
            Results.setAddress(Address);
            Results.setCity(City);
            Results.setState(State);
            Results.setZipcode(Zipcode);
            Results.setContactStatus(CS);
            Results.Validate();
            return Results; 
        } else{
            return null;
                    
        }
        

    }


    // Exportables
    
    /**
     * 
     * @return 
     */
    public String toXML(){
        String results = "";        
        results += "<ContactInfo>" + '\n';
        results += "<ID>"+this.ID+"</ID>" + '\n';
        if(this.CompanyName == null){
            results += "<CompanyName>"+""+"</CompanyName>" + '\n';
        }else{
            results += "<CompanyName>"+this.CompanyName+"</CompanyName>" + '\n';
        }
        
        if(this.FirstName == null){
            results += "<FirstName>"+""+"</FirstName>" + '\n';
        }else{
            results += "<FirstName>"+this.FirstName+"</FirstName>" + '\n';
        }
        
        if(this.LastName == null){
            results += "<LastName>"+""+"</LastName>" + '\n';
        }else{
            results += "<LastName>"+this.LastName+"</LastName>" + '\n';
        }
        
        if(this.Email == null){
        results += "<Email>"+""+"</Email>" + '\n';
        }else{
            results += "<Email>"+this.Email+"</Email>" + '\n';
        }
        
        results += "<Position>"+this.Position.toString()+"</Position>" + '\n';
        
        if(this.Address == null){
        results += "<Address>"+""+"</Address>" + '\n';
        } else{
            results += "<Address>"+this.Email+"</Address>" + '\n';
        }
        if(this.City == null){
            results += "<City>"+""+"</City>" + '\n';
        } else{
            results += "<City>"+this.City+"</City>" + '\n';
        }
        
        if(this.State == null){
        results += "<State>"+""+"</State>" + '\n';
        } else{
            results += "<State>"+this.State+"</State>" + '\n';
        }
        
        if(this.Zipcode == null){
        results += "<Zipcode>"+""+"</Zipcode>" + '\n';
        }else{
            results += "<Zipcode>"+this.Zipcode+"</Zipcode>" + '\n';
        }
            
        results += "<ContactStatus>"+this.ContactStatus.toString()+"</ContactStatus>" + '\n';
        results += "</ContactInfo>" + '\n';        
        return results;
    }
    
    public String toCSV(){
        String results = "";
        results += this.ID + ",";
        results += this.FirstName + ",";
        results += this.LastName + ",";
        results += this.ContactStatus  + ",";
        return results;
    }
    
    /**
     * 
     * @return 
     */
    public String toCustom(){
        String results = "";
        results += "ID: " + this.ID + '\n';
        results += "CompanyName: " + this.CompanyName + '\n';
        results += "FirstName: " + this.FirstName + '\n';
        results += "LastName: " + this.LastName + '\n';
        results += "Email: " + this.Email + '\n';
        results += "Position: " + this.Position.toString() + '\n';
        results += "Address: " + this.Address + '\n';
        results += "City: " + this.City + '\n';
        results += "State: " + this.State + '\n';
        results += "Zipcode: " + this.Zipcode + '\n';
        results += "ContactStatus: " + this.ContactStatus.toString() + '\n';        
        return results;
    }
    
}


enum Position{
    Student,
    Manager,
    Engineer,
    Customer,
    Unknown;
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
    Unknown;
    
}
