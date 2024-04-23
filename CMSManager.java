/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cms;



import java.io.IOException;

/**
 *
 * @author rjbea
 */
public class CMSManager {        

    
    // The Contatact Info Repository
    protected static String ContactRepository = "ContactInfo.xml";
    protected static String ContRepForm = "XML";
    // The Event Repository
    protected static String EventRepository = "Events.ics";
    protected static String EvenntRepForm = "ICS";
    // Contains a list of the Newsletter MetaData
    // Primarily used for not creating duplicates and storing file location data
    protected static String NewsletterRepository = "Newsletters.txt";
    
    /**
     * @param args the command line arguments
     */    
    public static void main(String[] args) {
        // TODO code application logic here
        // java.util.ArrayList<cms.ContactInfo> ContactInfos = new java.util.ArrayList<>();
        // CMSManager.beta(ContactInfos);
        // CMSManager.importFromDatabase(ContactInfos);
        //betaPrint(ContactInfos);
        //betaPrint(ContactInfos);
        if(args.length > 0){
            CommandLineInterface(args); // this works to create a database if there is none
        } else{
            GUIInterface(args); // currently there is no GUI Interface, comment out when there is
        }
    }
    
    
    /**
     *  All GUI Creation will start here
     */
    public static void GUIInterface(String[] args){

        
        cms.ContactManagerGUI.main(args);
    
    }
    
    /**
     * 
     * @param args 
     */
    public static void CommandLineInterface(String[] args){
        
        cms.CommandLineManager.CommandLineManagerStart(args);
        
    }
    
    /**
     * 
     * @param Contacts
     * @param ID
     * @param CompanyName
     * @param FirstName
     * @param LastName
     * @param Email
     * @param Position
     * @param Address
     * @param City
     * @param State
     * @param Zipcode
     * @param ContactStatus
     * @return 
     */
    public static boolean addToArrayList(java.util.ArrayList<cms.ContactInfo> 
            Contacts, String ID, String CompanyName, String FirstName, 
            String LastName, String Email, String Position, String Address,
            String City, String State, String Zipcode, String ContactStatus){
        // Search for matching ID
        System.out.println("Adding");
        boolean idCheck = IDCheck(Contacts, ID);
        if(idCheck == false){
            System.out.println("Error: Matching ID Found");
            return false;
        }
        
        // Start to construct CotnactInfo
        cms.ContactInfo newContact = new cms.ContactInfo(ID, CompanyName, 
                FirstName, LastName, Email);

        
        newContact.setPosition(Position);
        newContact.setAddress(Address);
        newContact.setCity(City);
        newContact.setState(State);
        newContact.setZipcode(Zipcode);
        newContact.setContactStatus(ContactStatus);

        java.util.ArrayList<Boolean> results = new java.util.ArrayList<>();
        
        
        if(newContact.getSystemStatus().toString().equalsIgnoreCase("Invalid")){
            System.out.println("Error: Invalid Contact, Not Added");
            return false;
        } else{
            Contacts.add(newContact);
            betaPrint(Contacts);
            Export(Contacts, ContactRepository, ContRepForm);            
            return true;
        }
        


        // Save the data and return true

    }

    /**
     * 
     * @param Contacts
     * @param ID
     * @param Field
     * @param fieldValue
     * @return 
     */
    public static boolean Update(java.util.ArrayList<cms.ContactInfo> 
            Contacts, String ID, String Field, String fieldValue){
        boolean results = false;
        java.util.ArrayList<cms.ContactInfo> Results = new java.util.ArrayList<>();
        System.out.println("Update");
        System.out.println("Field : "+Field);
        System.out.println("FieldValue : "+fieldValue);
        
        if(Field.equalsIgnoreCase("CompanyName")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getID().equalsIgnoreCase(ID)){
                    Current.setCompanyName(fieldValue);
                }
                Results.add(Current);
            }
        }else if(Field.equalsIgnoreCase("FirstName")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getID().equalsIgnoreCase(ID)){
                    Current.setFirstName(fieldValue);
                }
                Results.add(Current);
            }
        }else if(Field.equalsIgnoreCase("LastName")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getID().equalsIgnoreCase(ID)){
                    Current.setLastName(fieldValue);
                }
                Results.add(Current);
            }
            
        }else if(Field.equalsIgnoreCase("email")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getID().equalsIgnoreCase(ID)){
                    Current.setEmail(fieldValue);
                }
                Results.add(Current);
            }    
        }else if(Field.equalsIgnoreCase("Position")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getID().equalsIgnoreCase(ID)){
                    Current.setPosition(fieldValue);
                }
                Results.add(Current);
            }
        }else if(Field.equalsIgnoreCase("Adress")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getID().equalsIgnoreCase(ID)){
                    Current.setAddress(fieldValue);
                }
                Results.add(Current);
            }
            
        }else if(Field.equalsIgnoreCase("City")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getID().equalsIgnoreCase(ID)){
                    Current.setCity(fieldValue);
                }
                Results.add(Current);
            }
        }else if(Field.equalsIgnoreCase("Zipcode")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getID().equalsIgnoreCase(ID)){
                    Current.setZipcode(fieldValue);
                }
                Results.add(Current);
            }                        
        }else if(Field.equalsIgnoreCase("ContactStatus")==true){
             for(cms.ContactInfo Current : Contacts){
                if(Current.getID().equalsIgnoreCase(ID)){
                    Current.setContactStatus(fieldValue);
                }
            }  
        } else{
            System.out.println("Error: unsuported search field");
        }

        Export(Contacts, ContactRepository, ContRepForm);

        
        
        return results;
    }
    
    /**
     * 
     * @param Contacts
     * @param ID
     * @return 
     */
    public static boolean Delete(java.util.ArrayList<cms.ContactInfo> Contacts,
            String ID){
        boolean results = false;
        
        java.util.ArrayList<cms.ContactInfo> Result = new java.util.ArrayList<>();
        
        for(cms.ContactInfo Current:Contacts){
            if( Current.getID().equalsIgnoreCase(ID) == true){
                // we found a match and removed it
                results = true;
            } else{
                Result.add(Current);
            }
        }
        
        Export(Result, ContactRepository, ContRepForm);
        
        return results;
    }
    
    /**
     * 
     * @param Field
     * @param fieldValue
     * @return 
     */
    public static java.util.ArrayList<cms.ContactInfo> Search(String Field, String fieldValue){
        java.util.ArrayList<cms.ContactInfo> Contacts = cms.CMSManager.importFromDatabase();
        java.util.ArrayList<cms.ContactInfo> Results = new java.util.ArrayList<>();
                
        if(Field.equalsIgnoreCase("ID")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getID().equalsIgnoreCase(fieldValue)==true){
                    Results.add(Current);
                    return Results;
                }
            }
        }else if(Field.equalsIgnoreCase("CompanyName")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getCompanyName().equalsIgnoreCase(fieldValue)){
                    Results.add(Current);
                }
            }
        }else if(Field.equalsIgnoreCase("FirstName")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getFirstName().equalsIgnoreCase(fieldValue)){
                    Results.add(Current);
                }
            }
        }else if(Field.equalsIgnoreCase("LastName")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getLastName().equalsIgnoreCase(fieldValue)){
                    Results.add(Current);
                }
            }
        }else if(Field.equalsIgnoreCase("Position")== true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getPosition().toString().equalsIgnoreCase(fieldValue)){
                    Results.add(Current);
                }
            }
        }else if(Field.equalsIgnoreCase("ContactStatus")==true){
             for(cms.ContactInfo Current : Contacts){
                if(Current.getContactStatus().toString().equalsIgnoreCase(fieldValue)){
                    Results.add(Current);
                }
            }  
        } else{
            System.out.println("Error: unsuported search field");
        }
                
        return Results;
    }
    
    /**
     * 
     * @param Contacts
     * @param File
     * @param Format
     * @return 
     */
    public static boolean Export(java.util.ArrayList<cms.ContactInfo> Contacts, String File, String Format){
        System.out.println("Export");
        if(((File.length()>=3) &&(File.contains("."))&&(Format.equalsIgnoreCase(ContRepForm)))){
            System.out.println(ContRepForm);
            java.io.File outputFile = new java.io.File(File);

            try{
                java.io.FileWriter outputFileWriter = new java.io.FileWriter(outputFile);
                for(cms.ContactInfo Current : Contacts){
                    outputFileWriter.write(Current.toXML());
                }
                outputFileWriter.close();
                return true;

            } catch(IOException ex){
                System.out.println(ex);
                return false;
            }        
        } else if(((File.length()>=3) &&(File.contains("."))&&(Format.equalsIgnoreCase("Custom")))){
            System.out.println("Custom");
            java.io.File outputFile = new java.io.File(File);

            try{
                java.io.FileWriter outputFileWriter = new java.io.FileWriter(outputFile);
                for(cms.ContactInfo Current : Contacts){
                    outputFileWriter.write(Current.toCustom());
                }
                outputFileWriter.close();
                return true;

            } catch(IOException ex){
                System.out.println(ex);
                return false;
            }        
        }else{
            System.out.println("Error: Impropper Filename");
            return false;
        }
      
        
    }
    
    /**
     * 
     * @param Contacts
     * @param ID
     * @return 
     */
    public static boolean IDCheck(java.util.ArrayList<cms.ContactInfo> Contacts, String ID){
        boolean results = true;
        
        for(cms.ContactInfo Current : Contacts){
            if(Current.getID().equalsIgnoreCase(ID) == true){
                return false;
            }
        }
        return results;
    }
    
    /**
     * 
     * @param Contacts
     * @return 
     */
    public static java.util.ArrayList<ContactInfo> importFromDatabase() {
        java.util.ArrayList<ContactInfo> Contacts = new java.util.ArrayList<>();
        java.io.File inputFile = new java.io.File(ContactRepository);

        try {
            java.io.FileReader inputFileReader = new java.io.FileReader(inputFile);
            java.io.BufferedReader inputBuffReader = new java.io.BufferedReader(inputFileReader);
            String TextBlock = "";
            String line = "";

            while ((line = inputBuffReader.readLine()) != null) {
                if (line.contains("<ContactInfo>")) {
                    // If the line is the start of a new contact info, reset text 
                    TextBlock = "";                                       
                } else if (line.contains("</ContactInfo")) {
                    // If the line marks the end of a contact info block, create a ContactInfo object
                    ContactInfo AddedContact = ContactInfo.fromXML(TextBlock);
                    //System.out.println(AddedContact.toCustom());
                    Contacts.add(AddedContact);
                } else {
                    // Continue reading and add to text block
                    TextBlock += line;
                }
            }

            // Close readers
            inputBuffReader.close();
            inputFileReader.close();

            // Print contacts (for debugging)
           // betaPrint(Contacts);

            return Contacts;

        } catch (IOException ex) {
            // Handle IO exceptions
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 
     * @param file
     * @return 
     */
    public static java.util.ArrayList<cms.Event> EventImportICS(String file){
        java.util.ArrayList<cms.Event> Events = new java.util.ArrayList<>();
        java.io.File inputFile = new java.io.File(file);
        cms.Event currentEvent = null;
        String line;
        String text="";
        try{
            java.io.FileReader inputFileReader = new java.io.FileReader(inputFile);
            java.io.BufferedReader buffReader = new java.io.BufferedReader(inputFileReader);
            
            while((line = buffReader.readLine())!=null){
                if (line.contains("BEGIN:VCALENDAR")){
                    text = "";
                } else if (line.contains("END:VCALENDAR")){
                    currentEvent = cms.Event.fromICS(text);
                    //System.out.println(currentEvent.toICS());
                    Events.add(currentEvent);
                } else{
                    text += line + '\n';
                }
            }
        
        } catch(IOException ex){
            System.out.println(ex.toString());
        }
        
        return Events;
    }
    
    /**
     * 
     * @param ID
     * @return 
     */
    public static cms.Event getEvent(String ID){
        java.util.AbstractList<cms.Event> Events = EventImportICS(EventRepository);
        
        for(cms.Event current : Events){
            if (current.getID().equalsIgnoreCase(ID) ==true) return current;
        }
        
        
        return null;
    }
    
    /**
     * 
     * @param ID
     * @param Title
     * @param Subject
     * @param Body
     * @param Status
     * @param Position
     * @return 
     */
    public static boolean NewsLetterAdd(String ID, String Title, String Subject,
            String Body, String Status, String Position){
        
        java.util.ArrayList<cms.NewsLetter> NewsLetters = NewsLetterImport(NewsletterRepository);
        
        
        
     
        cms.NewsLetter newsLetter = new cms.NewsLetter(ID, Subject, Body, Status, Position);
        
        if(newsLetter == null){
            return false;
        } else{
            NewsLetters.add(newsLetter);
            return true;
        }
        

    }
    
    /**
     * 
     * @param Field
     * @param fieldValue
     * @return 
     */
    public static java.util.ArrayList<cms.NewsLetter> NewsLetterSearch(String Field, String fieldValue){
        java.util.ArrayList<cms.NewsLetter> NewsLetters = NewsLetterImport(NewsletterRepository);
        java.util.ArrayList<cms.NewsLetter> Results = new java.util.ArrayList<cms.NewsLetter>();
        

        if(Field.equals("ID")){
            for(cms.NewsLetter current : NewsLetters){
                if(current.ID.equalsIgnoreCase(fieldValue)==true){
                    Results.add(current);
                }
            }
        }
        
        if(Field.equals("ContactStatus")){
            for(cms.NewsLetter current : NewsLetters){
                if(current.IntendedContactStatus.toString().equalsIgnoreCase(fieldValue)==true){
                    Results.add(current);
                }
            }
        }
        
        if(Field.equals("Position")){
            for(cms.NewsLetter current : NewsLetters){
                if(current.getIntendedPosition().toString().equalsIgnoreCase(fieldValue)==true){
                    Results.add(current);
                }
            }
        }
            
        
        return Results;
    }
    
    /**
     * 
     * @param ID
     * @return 
     */
    public static String NewsLetterReview(String ID){
        java.util.ArrayList<cms.NewsLetter> Newsletters = NewsLetterSearch("ID", ID);

        System.out.println(Newsletters.get(0).toString());
        
        return Newsletters.get(0).toString();
    }
    
    /**
     * 
     * @param ID
     * @param Field
     * @param fieldValue
     * @return 
     */
    public static java.util.ArrayList<cms.NewsLetter> NewsLetterUpdate(String ID, String Field, String fieldValue){
        
        return null;
    }
    
    /**
     * 
     * @param ID
     * @return 
     */
    public static boolean NewsLetterDelete(String ID){
        
        System.out.println("Not Yet Implemented");
        
        
        return false;
    }
    
    /**
     * 
     * @param NewsLetter
     * 
     * @return 
     */
    public static boolean NewsLetterSend(cms.NewsLetter NewsLetter){
        
        java.util.ArrayList<cms.ContactInfo> Contacts = new java.util.ArrayList<>();
        Contacts = importFromDatabase();
        System.out.println(Contacts.get(0).toXML());
        java.util.ArrayList<cms.NewsLetter> NewsLetters = NewsLetter.load(Contacts);
        
        for(cms.NewsLetter Current : NewsLetters){
            System.out.println(Current.getBody());
        }
        
        
        
        
        return false;
    }
    
    public static cms.NewsLetter NewsLetterSearch(String ID){
        java.util.ArrayList<cms.NewsLetter> NewsLetters = new java.util.ArrayList<>();
        cms.NewsLetter results;
        for(cms.NewsLetter current: NewsLetters){
            if(current.ID.equalsIgnoreCase(ContRepForm)==true ){
                return current;
            }
        }
        return null;
    }
    
    /**
     * 
     * @param File
     * @return 
     */
    public static java.util.ArrayList<cms.NewsLetter> NewsLetterImport(String File){
        System.out.println("Import");
        File = NewsletterRepository; // Remove in final product
        java.util.ArrayList<cms.NewsLetter> newsLetters = new java.util.ArrayList<>();
        
        java.io.File inputFile = new java.io.File(File);
        try{
            java.io.FileReader inputFileReader = new java.io.FileReader(inputFile);
            java.io.BufferedReader buffReader = new java.io.BufferedReader(inputFileReader);
            String line;
            String text="";
            while((line = buffReader.readLine()) != null){
                
                if(line.contains("EndBody: EndLetter")== false){
                    text += line;
                    System.out.println(line);
                } else{
                    
                    cms.NewsLetter newsLetter = cms.NewsLetter.fromCustom(text);
                    newsLetters.add(newsLetter);
                    text = "";
                }
            }
            
            buffReader.close();
            inputFileReader.close();
        }catch(IOException ex){
            System.out.println(ex.toString());
        }
        return newsLetters;
    }
    
    /**
     * 
     * @param NewsLetters
     * @return 
     */
    public static boolean NewsLetterExport(java.util.ArrayList<cms.NewsLetter> NewsLetters){
        
        java.io.File outputFile = new java.io.File(NewsletterRepository);
        
        String text;
        try{
            java.io.FileWriter outputFileWriter = new java.io.FileWriter(outputFile);
            for(cms.NewsLetter current : NewsLetters){
                outputFileWriter.write(current.toCustom());
                
            }
            outputFileWriter.close();
        } catch(IOException ex){
            System.out.println("Not Exported");
            return false;
        }
        
        return true;
    }
    
    /**
     * 
     * @param ID
     * @param Name
     * @param Year
     * @param Month
     * @param Day
     * @param startHour
     * @param startMin
     * @param endHour
     * @param endMin
     * @param Summary
     * @param Description
     * @param Location
     * @return 
     */
    public static boolean EventAdd(String ID, String Name, int Year, int Month, 
            int Day, int startHour, int startMin, int endHour, int endMin, String Summary, 
            String Description, String Location){
        
        System.out.println("EventAdd");
        java.util.ArrayList<cms.Event> Events = new java.util.ArrayList<>();
        
        Events = EventImportICS(EventRepository);
        for(cms.Event current:Events){
            if (current.getID().equalsIgnoreCase(ID) == true){
                System.out.println("ID ALREADY EXISTS");
                return false;
            }
        }

        cms.Event newEvent = new cms.Event(ID, Name, Day, Month, Year, startHour, startMin, endHour, endMin, Summary, Description, Location);
        System.out.println(newEvent.toICS());
        Events.add(newEvent);
        
        EventExport(Events, EventRepository, EvenntRepForm);
        
        return true;
    }
    /**
     * 
     * @param Events
     * @param file
     * @param Format
     * @return 
     */
    public static boolean EventExport(java.util.ArrayList<cms.Event> Events, String file, String Format){
        boolean results = false;
        
        // Format comming soon
        
        String Text = "";
        for(cms.Event current : Events){
            Text += current.toICS();
        }
        
        java.io.File outputFile = new java.io.File(file);
        java.io.FileWriter outFileWriter;
        try{
            outFileWriter = new java.io.FileWriter(outputFile);
            outFileWriter.write(Text);
            outFileWriter.close();
            results = true;
        } catch(Exception ex){
            
        }
        return results;
    }

    public static boolean EventUpdate(String ID, String Field, String FieldValue){
        java.util.ArrayList<cms.Event> Events = EventImportICS(EventRepository);
        
        for(cms.Event current : Events){
            if(current.getID().equalsIgnoreCase(ID)){
            
                switch(Field.toLowerCase()){
                    case "name":
                        current.EventName = FieldValue;
                        break;
                    case "file":
                        current.EventFile = FieldValue;
                        break;
                    case "date":
                        System.out.println("Coming Soon");
                        break;
                    case "starttime":
                        System.out.println("Coming Soon");
                        break;
                    case "endtime":
                        System.out.println("Coming Soon");
                    case "summary":
                        current.Summary = FieldValue;
                        break;
                    case "description":
                        current.Description = FieldValue;
                        break;
                    case "location":
                        current.Description = FieldValue;
                }
            }
        }
        
        EventExport(Events, EventRepository, EvenntRepForm);
        
        return false;
    }

    /**
     * 
     * @param Event
     * @param Contacts
     * @return 
     */
    public static boolean EventSend(cms.Event Event, java.util.ArrayList<cms.ContactInfo> Contacts){
        java.util.ArrayList<cms.Event> thisEvent = new java.util.ArrayList<>();
        thisEvent.add(Event);
        String EventName = Event.getEventName().replace(" ", "_");
        EventExport(thisEvent, EventName + ".ics", EvenntRepForm);
        
        
        
        for(cms.ContactInfo Current : Contacts){
            System.out.println("Event has been sent to " + Current.FirstName + Current.LastName);
        }
        
        return true;
    }
    /**
     * 
     */
    public static void Help(){
        String Text = "";
        Text += "=========================================================" + '\n';
        Text += "args[0] args[1]     etc...                   :Use" + '\n';
        Text += "Initialize :Creates a database when one does not exist"  + '\n';
        Text += "/add ID CompanyName FirstName LastName Email" + '\n';
        Text += "   :Add A Contact with minimum requiered information" + '\n';
        Text += "/search fieldType fieldValue" + '\n';
        Text += "   :Search For a contact. Does Not Support Address Information" + '\n';
        Text += "/update ID fieldType fieldValue" + '\n'; 
        Text += "   :Search for a contact and update a value" + '\n';
        Text += "/delete ID" + '\n';
        Text += "/export fileName fileFormat" + '\n';
        Text += "   :Export your contacts with a specific file and format" + '\n';
        
        System.out.println(Text);
                
    }
    /**
    * 
    * @param Contacts 
    */ 
    public static void beta(java.util.ArrayList<cms.ContactInfo> Contacts){
        //betaOutput();
        betaNewsletter();
    }
    /**
 * 
 * @param Contacts 
 */
    public static void betaPrint(java.util.ArrayList<cms.ContactInfo> Contacts){
        System.out.println("Here");
        for(cms.ContactInfo CurrentCotnact : Contacts){
            System.out.println(CurrentCotnact.toCustom());
        }


    }
    /**
     * 
     */
    public static void betaNewsletter(){
        String ID = "001";
        String Title = "A NewsLetter";
        String Subject = "A News Letter For You";
        String Body = "Hello <FirstName> <LastName>," +'\n';
        Body += "   This is a news letter for you Mr <LastName>.";
        java.util.ArrayList<NewsLetter> NewsLetters = new java.util.ArrayList<>();
        
        cms.NewsLetter beta = new cms.NewsLetter(ID, Subject, Body, "Unknown", "Unknown");
        
        NewsLetters.add(beta);
        
        NewsLetterExport(NewsLetters);
        
        java.util.ArrayList<cms.NewsLetter> BetaList = new java.util.ArrayList<>();
        
        BetaList = NewsLetterImport(NewsletterRepository);
        
        System.out.println(beta.toCustom());
        
        java.util.ArrayList<cms.ContactInfo> Contacts = new java.util.ArrayList<>();
        

        NewsLetterSend(beta);
        
    }
    /**
     * 
     */
    public static void betaEvent(){
        String ID = "E001";
        String Name = "Test Event";
        int year = 2025;
        int month = 11;
        int day = 30;
        int StartHr = 11;
        int StartMin = 30;
        int EndHr = 14;
        int EndMin = 00;
        String Summary = "A Test EVENT";
        String Description ="The First Test Event. Testing Status";
        String Location = "My imagination";
        
        
        
        cms.Event TestEvent01 = new cms.Event(ID, Name, day, month, year, StartHr, StartMin, EndHr, EndMin, Summary, Description, Location);
        java.util.ArrayList<cms.Event> Events = new java.util.ArrayList<>();
        
        System.out.println(TestEvent01.toString());
        
        Events.add(TestEvent01);
        
        cms.CMSManager.EventExport(Events, EventRepository, EvenntRepForm);
        Events.clear();
        System.out.println(Events.size());
        
        Events = cms.CMSManager.EventImportICS(EventRepository);
        
        if(Events.size() > 0){
            System.out.println(Events.get(0).toICS());
        } else{
            System.out.println("Error");
        }
        
    }
    /**
     * 
     */
    public static void betaOutput(){
            // Test 1, Missing Data, not including ID, Name info, or Email

            java.util.ArrayList<cms.ContactInfo> ContactInfos = new java.util.ArrayList<>();
            String ID = "001";
            String CompanyName = "UAB";
            String FirstName = "Robert";
            String LastName = "Beatty";
            String Email = "RJB89@uab.edu";
            String Position = "Student";
            String Address = "1331 34th St S";
            String City = "Birmingham";
            String State = "AL";
            String Zipcode = "35205";
            String ContactStatus = "New";

            cms.ContactInfo Contact1 = new cms.ContactInfo(ID, CompanyName, FirstName, LastName, Email);

            cms.ContactInfo Contact2 = new cms.ContactInfo("002", CompanyName, "Gregory", "Myers", "GMYERS@uab.edu");

            Contact1.setPosition(Position);
            Contact1.setAddress(Address);
            Contact1.setCity(City);
            Contact1.setState(State);
            Contact1.setZipcode(Zipcode);
            Contact1.setContactStatus(ContactStatus);

            Contact2.ValidateAll();

            java.io.File outputFile = new java.io.File(ContactRepository);

            try{
                java.io.FileWriter outputFileWriter = new java.io.FileWriter(outputFile);
                outputFileWriter.write(Contact1.toXML());

                outputFileWriter.close();

            } catch(IOException ex){
                System.out.println(ex);
            }

        }

}


