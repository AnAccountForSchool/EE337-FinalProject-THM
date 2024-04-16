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
            Help();
        }
    }
    
    
    public static void GUIInterface(){
        java.util.ArrayList<cms.ContactInfo> ContactInfos = new java.util.ArrayList<>();
    
    }
    
    
    public static void CommandLineInterface(String[] args){
        System.out.println("Here");
        java.util.ArrayList<cms.ContactInfo> ContactInfos = new java.util.ArrayList<>();
        
        // args information for debugging
        // need to comment out for release
        int index;     
        System.out.println("Count: "+ args.length);
        for(index = 0; index<args.length; index++){
            System.out.println(" args["+index+"] = " + args[index]);
        }
        
        if(args[0].equalsIgnoreCase("/Initialize")==true){
            System.out.println("Initializing");
            betaOutput();
        }
        
        ContactInfos = cms.CMSManager.importFromDatabase(ContactInfos);
        
        if((args.length == 6) && (args[0].equalsIgnoreCase("/add"))) {            
            addToArrayList(ContactInfos, args[1], args[2], args[3], args[4], args[5], "", "", "", "", "", "");
        } else if((args.length == 12) && (args[0].equalsIgnoreCase("/add"))){
            addToArrayList(ContactInfos, args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11]);                
        }else if((args.length == 3) && (args[0].equalsIgnoreCase("/search"))) {            
            java.util.ArrayList<cms.ContactInfo> SearchResults = new java.util.ArrayList<>();
            SearchResults = Search(ContactInfos, args[1], args[2]);
            if(SearchResults.size()>0){
                String FileName = "SearchResults_" + args[1]+"_"+args[2]+".txt";
                Export(SearchResults, FileName , "custom");
            } else{
                System.out.println("No Results Found");
            }
        } else if((args.length == 4) && (args[0].equalsIgnoreCase("/update"))) {
            Update(ContactInfos, args[1], args[2], args[3]);
            
        } else if((args.length == 2) && (args[0].equalsIgnoreCase("/delete"))) {
            Delete(ContactInfos, args[1]);
        } else if((args.length == 2) && (args[0].equalsIgnoreCase("/archive"))) {
            Update(ContactInfos, args[1], "ContactStatus", "Archive");
        } else if((args.length == 1) && (args[0].equalsIgnoreCase("/validate"))) {
            // Logic for validating contact information
        } else {
            // Display help message
            Help();
        }
    
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
            Export(Contacts, "ContactInfo.xml", "XML");            
            return true;
        }
        


        // Save the data and return true

    }
 
    
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

        Export(Contacts, "ContactInfo.xml", "XML");

        
        
        return results;
    }
    
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
        
        Export(Result, "ContactInfo.xml", "XML");
        
        return results;
    }
    
    public static java.util.ArrayList<cms.ContactInfo> Search(java.util.ArrayList<cms.ContactInfo> Contacts,
            String Field, String fieldValue){
        java.util.ArrayList<cms.ContactInfo> Results = new java.util.ArrayList<>();
                
        if(Field.equalsIgnoreCase("ID")==true){
            for(cms.ContactInfo Current : Contacts){
                if(Current.getID().equalsIgnoreCase(fieldValue)){
                    Results.add(Current);
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
    
    public static boolean Export(java.util.ArrayList<cms.ContactInfo> Contacts, String File, String Format){
        System.out.println("Export");
        if(((File.length()>=3) &&(File.contains("."))&&(Format.equalsIgnoreCase("XML")))){
            System.out.println("XML");
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
    
    public static boolean IDCheck(java.util.ArrayList<cms.ContactInfo> Contacts, String ID){
        boolean results = true;
        
        for(cms.ContactInfo Current : Contacts){
            if(Current.getID().equalsIgnoreCase(ID) == true){
                return false;
            }
        }
        return results;
    }
    
    public static java.util.ArrayList<ContactInfo> importFromDatabase(java.util.ArrayList<ContactInfo> Contacts) {
        java.io.File inputFile = new java.io.File("ContactInfo.xml");

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
    
    public static void beta(java.util.ArrayList<cms.ContactInfo> Contacts){
        betaOutput();
    }

    public static void betaPrint(java.util.ArrayList<cms.ContactInfo> Contacts){
        System.out.println("Here");
        for(cms.ContactInfo CurrentCotnact : Contacts){
            System.out.println(CurrentCotnact.toCustom());
        }


    }

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

            java.io.File outputFile = new java.io.File("ContactInfo.xml");

            try{
                java.io.FileWriter outputFileWriter = new java.io.FileWriter(outputFile);
                outputFileWriter.write(Contact1.toXML());
                outputFileWriter.write(Contact2.toXML());
                outputFileWriter.close();

            } catch(IOException ex){
                System.out.println(ex);
            }

        }



}


