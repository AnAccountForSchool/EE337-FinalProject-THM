/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;
import cms.ContactInfo;

/**
 *
 * @author rjbea
 */
// Current Functionality:
//      Send out NewsLetters with auto-filled text by ContactStatus and Position
//      Anywhere <FirstName> and <LastName> exist in body will be replaced;


// Future Functionality:
//      Schedule Out Letters
//      Send Individual Letters
//      Include Images and Files in Letters
//      More personalization based on position and contact status

public class NewsLetter {
    
    protected String ID;
    protected String SubjectLine;
    protected String NewsLetterFile;
    protected String Body;
    protected String IntendedContactStatus;
    protected String IntendedPosition;
    protected cms.ContactInfo Contact;
    protected boolean Valid;
    protected boolean Crafted;
    protected boolean Ready;
    protected cms.SendStatus SendStatus;
    
    /**
     * 
     * @param ID
     * @param SubjectLine
     * @param Body
     * @param RecipientStatus
     * @param RecipientPosition 
     */
    public NewsLetter(String ID, String SubjectLine, String Body, String RecipientStatus, String RecipientPosition){
        
        if((ID!=null)&&(ID.length()>0)){
            this.ID = ID;
        } else{
            System.out.println("Error: Impropper ID");
        }
        
        if((SubjectLine!=null)&&(SubjectLine.length()>0)){
            this.SubjectLine = SubjectLine;
        } else{
            System.out.println("Error: Impropper SubjectLine");
        }
        
        if((Body!=null)&&(Body.length()>0)){
            this.Body = Body;
        } else{
            System.out.println("Error: Impropper Body");
        }
        
        this.IntendedContactStatus = RecipientStatus;
        this.IntendedPosition = RecipientPosition;
        this.Crafted = false;
        this.Valid = false;
        this.Ready = false;
    }

    /**
     * 
     * @param Contacts
     * @return 
     */
    public java.util.ArrayList<cms.NewsLetter> load(java.util.ArrayList<cms.ContactInfo> Contacts){
        java.util.ArrayList<cms.NewsLetter> results = new java.util.ArrayList<>();
        System.out.println(this.Body);
        if(this.IntendedContactStatus.equalsIgnoreCase("Unknown")&& this.IntendedPosition.equalsIgnoreCase("Unknown"))
            for(cms.ContactInfo Current : Contacts){
                this.Contact = Current;
                this.Valid = true;
                this.Craft();
                results.add(this);
            }
        else{
           for(cms.ContactInfo Current : Contacts){
               if(this.IntendedPosition.equalsIgnoreCase(Current.Position.toString())
                       &&(this.IntendedContactStatus.equalsIgnoreCase(Current.ContactStatus.toString()))){
                   this.Contact = Current;
                   this.Valid = true;
                   this.Craft();
                   results.add(this);
               }
           }
        }   
        return results; 
    }
    
    /**
     * 
     * @return 
     */
    public boolean Craft(){
        String ResultBody = this.Body;
        if(this.isValid()== false){
            this.Crafted = false;
            return false;
        } else if (this.Contact == null){
            System.out.println("There is no contact associated");
            return false;
        }else{
            this.Body = this.Body.replace("<FirstName>", this.getContact().FirstName);
            this.Body = this.Body.replace("<LastName>", this.getContact().LastName);
        }
        
        
        return this.isCrafted();
    }

    public int Send(java.util.ArrayList<cms.ContactInfo> Contacts){
        
        cms.NewsLetter Output;
        
        int results = 0;
        
        if( this.IntendedPosition.toString().equalsIgnoreCase("Unknown") == true){
            if( this.IntendedContactStatus.toString().equalsIgnoreCase("Unknown") == true){
                for(cms.ContactInfo current : Contacts){
                    this.Contact = current;
                    results++;
                    Output = this;
                    System.out.println("Sending to " + current.FirstName + current.LastName + '\n');
                }
            }
        }
        
        for(cms.ContactInfo current : Contacts){        
            if( this.IntendedPosition.toString().equalsIgnoreCase(current.Position.toString()) == true){
                if( this.IntendedContactStatus.toString().equalsIgnoreCase(current.ContactStatus.toString()) == true){
                this.Contact = current;
                results++;
                Output = this;
                System.out.println("Sending to " + current.FirstName + current.LastName + '\n');
                }
            }        
        }
        
        return 0; 
    }
    
    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @return the SubjectLine
     */
    public String getSubjectLine() {
        return SubjectLine;
    }

    /**
     * @param SubjectLine the SubjectLine to set
     */
    public void setSubjectLine(String SubjectLine) {
        this.SubjectLine = SubjectLine;
        this.Crafted = false;
        this.Ready = false;
    }

    /**
     * @return the Body
     */
    public String getBody() {
        return Body;
    }

    /**
     * @param Body the Body to set
     */
    public void setBody(String Body) {
        this.Body = Body;
        this.Crafted = false;
        this.Ready = false;        
    }

    /**
     * @return the IntendedContactStatus
     */
    public String getIntendedContactStatus() {
        return IntendedContactStatus;
    }

    /**
     * @param IntendedContactStatus the IntendedContactStatus to set
     */
    public void setIntendedContactStatus(String IntendedContactStatus) {
        this.IntendedContactStatus = IntendedContactStatus;
        this.Crafted = false;
        this.Ready = false;
    }

    /**
     * @return the IntendedPosition
     */
    public String getIntendedPosition() {
        return IntendedPosition;
    }

    /**
     * @param IntendedPosition the IntendedPosition to set
     */
    public void setIntendedPosition(String IntendedPosition) {
        this.IntendedPosition = IntendedPosition;
        this.Crafted = false;
        this.Ready = false;
    }

    /**
     * @return the Contact
     */
    public cms.ContactInfo getContact() {
        return Contact;
    }

    /**
     * @param Contact the Contact to set
     */
    public void setContact(cms.ContactInfo Contact) {
        this.Contact = Contact;
        this.Crafted = false;
        this.Ready = false;
    }

    /**
     * @return the Valid
     */
    public boolean isValid() {
        return Valid;
    }


    /**
     * @return the Crafted
     */
    public boolean isCrafted() {
        return Crafted;
    }


    /**
     * @return the Ready
     */
    public boolean isReady() {
        return Ready;
    }


    /**
     * @return the SendStatus
     */
    public cms.SendStatus getSendStatus() {
        return SendStatus;
    }

    /**
     * @param SendStatus the SendStatus to set
     */
    public void setSendStatus(cms.SendStatus SendStatus) {
        this.SendStatus = SendStatus;
    }
    /**
     * 
     * @return 
     */
    public String toCustom(){
        String results = "";
        results += "ID: " + this.ID + '\n';
        results += ": Subject: " + this.SubjectLine + '\n';
        results += ": IntendedStatus: " + this.IntendedContactStatus.toString() + '\n';
        results += ": IntendedPosition: " + this.IntendedPosition.toString() + '\n';
        results += ": Body: " + this.Body + '\n';
        results += ": EndBody: EndLetter" + '\n';
    
        return results;
    }
    /**
     * 
     * @param Text
     * @return 
     */
    public static NewsLetter fromCustom(String Text){
        String[] chunks = Text.split(": ");
        int i = 0;
        String ID = "";
        String SubjectLine = "";
        String IntendedContactStatus = "";
        String IntendedPosition = "";
        String Body = "";
        int stop = chunks.length -1;
        while( i < stop-1){
            if(chunks[i].contains("ID")){
                ID = chunks[i+1];
            } else if(chunks[i].contains("Subject")){
                SubjectLine = chunks[i+1];
            } else if(chunks[i].contains("IntendedStatus")){
                IntendedContactStatus  = chunks[i+1];
            } else if(chunks[i].contains("IntendedPosition")){
                IntendedPosition = chunks[i+1];
            } else if(chunks[i].contains("Body")){
                Body = chunks[i+1];
            } else if(chunks[i].contains("EndBody")){
            }
            i = i + 2;
            System.out.println(chunks[i+1]);
        }
        NewsLetter result = new cms.NewsLetter(ID, SubjectLine, Body, IntendedContactStatus, IntendedPosition);
        return result;
    }

}

enum SendStatus{
    Pending,
    Sent,
    Unknown;
}
