/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

import static cms.CMSManager.Delete;
import static cms.CMSManager.EventAdd;
import static cms.CMSManager.Export;
import static cms.CMSManager.Help;
import static cms.CMSManager.Search;
import static cms.CMSManager.Update;
import static cms.CMSManager.addToArrayList;
import static cms.CMSManager.betaOutput;
import static cms.CMSManager.getEvent;

/**
 *
 * @author rjbea
 */
public class CommandLineManager {
    
    /**
     * 
     * @param args 
     */
    public static void CommandLineManagerStart(String[] args){
        System.out.println("Here");
        java.util.ArrayList<cms.ContactInfo> ContactInfos = new java.util.ArrayList<>();
        java.util.ArrayList<cms.Event> Events = new java.util.ArrayList<>();
        java.util.ArrayList<cms.NewsLetter> NewsLetters = new java.util.ArrayList<>();
        
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


            // this might come later


        } else if ((args.length == 13) && (args[0].equalsIgnoreCase("/EventAdd")==true)){

            // 1 = id 2 = name 3 = day 4 = month 5 = year 6 = Hr1 7 = min1 8 = hr 2 9 = min2 10 = Sum 11 = desc 12 = loc
            EventAdd(args[1], args[2], java.lang.Integer.parseInt(args[3]), 
                    java.lang.Integer.parseInt(args[4]), java.lang.Integer.parseInt(args[5]), 
                    java.lang.Integer.parseInt(args[6]), java.lang.Integer.parseInt(args[7]), 
                    java.lang.Integer.parseInt(args[8]), java.lang.Integer.parseInt(args[9]), 
                    args[10], args[11], args[12]);

            
        } else if ((args.length == 13) && (args[0].equalsIgnoreCase("/EventUpdate")==true)){
            
            System.out.println("Functionality Coming Soon");
            
        }else if((args.length == 3)&&(args[0].equalsIgnoreCase("/EventSend"))){
            
            java.util.ArrayList<cms.ContactInfo> ResultContacts = cms.CMSManager.Search(ContactInfos, args[2], args[3]);
            cms.Event Event = getEvent(args[1]);
            if(Event != null){
                cms.CMSManager.EventSend(Event, ContactInfos);
            } else System.out.println("Event not found");
        } else if ((args.length == 7) && (args[0].equalsIgnoreCase("/NewsletterAdd")==true)){
            cms.CMSManager.NewsLetterAdd(args[1], args[2], args[3], args[4], args[5], args[6]);
            
            
        } else if ((args.length == 2) && (args[0].equalsIgnoreCase("/NewsletterSend")==true)){
            
            cms.NewsLetter NewsLetter = cms.CMSManager.NewsLetterSearch(args[1]);
            if(NewsLetter == null){
                System.out.println("NewsLetter Not Found");
            } else{
                cms.CMSManager.NewsLetterSend(NewsLetter);
            }
            
        } else if((args.length == 4) && (args[0].equalsIgnoreCase("/NewsLetterUpdate"))) {

            cms.CMSManager.NewsLetterUpdate(args[1], args[2], args[3]);
            
        }else if(args[0].equalsIgnoreCase("/beta")== true){
            //betaOutput();
            //betaEvent();
            //betaNewsletter();
        }else{
            // Display help message
            Help();
        }
    
    }
    
}
