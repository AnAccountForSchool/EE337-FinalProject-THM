/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cms;

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
    protected String Body;
    protected cms.ContactStatus IntendedContactStatus;
    protected cms.Position IntendedPosition;
    protected cms.ContactInfo Contact;
    protected boolean Valid;
    protected boolean Crafted;
    protected boolean Ready;
    protected cms.SendStatus SendStatus;
    
    
    public NewsLetter(String ID, String SubjectLine, String Body, cms.ContactStatus RecipientStatus, cms.Position RecipientPosition){
        
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
    
    public java.util.ArrayList<cms.NewsLetter> load(java.util.ArrayList<cms.ContactInfo> Contacts){
        java.util.ArrayList<cms.NewsLetter> results = new java.util.ArrayList<>();
        
        if(this.getIntendedContactStatus().equals(cms.ContactStatus.Unknown)&&this.getIntendedPosition().equals(cms.Position.Unknown))
            for(cms.ContactInfo Current : Contacts){
                this.Valid = true;
                results.add(this);
            }
        else{
           for(cms.ContactInfo Current : Contacts){
               if(Current.Position.equals(this.getIntendedPosition())&&(Current.ContactStatus.equals(this.getIntendedContactStatus()))){
                   this.Valid = true;
                   results.add(this);
               }
           }
        }   
        return results; 
    }
    
    public boolean Craft(){
        String ResultBody = "";
        if(this.isValid()== false){
            this.Crafted = false;
            return false;
        } else{
            String[] chunks = this.getBody().split("<FirstName>");
            int i = 0;
            int end = chunks.length-1;
            while(i < end){
                ResultBody = chunks[i] + getContact().getFirstName();
                i++;
            }
            ResultBody = chunks[end];
            this.setBody(ResultBody);
            chunks = this.getBody().split("<LastName>");
            i = 0;
            end = chunks.length-1;
            while(i < end){
                ResultBody = chunks[i] + getContact().getFirstName();
                i++;
            }
            ResultBody = chunks[end];
            this.setBody(ResultBody);
            
            this.Crafted = true;
        }
        return this.isCrafted();
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
    public cms.ContactStatus getIntendedContactStatus() {
        return IntendedContactStatus;
    }

    /**
     * @param IntendedContactStatus the IntendedContactStatus to set
     */
    public void setIntendedContactStatus(cms.ContactStatus IntendedContactStatus) {
        this.IntendedContactStatus = IntendedContactStatus;
        this.Crafted = false;
        this.Ready = false;
    }

    /**
     * @return the IntendedPosition
     */
    public cms.Position getIntendedPosition() {
        return IntendedPosition;
    }

    /**
     * @param IntendedPosition the IntendedPosition to set
     */
    public void setIntendedPosition(cms.Position IntendedPosition) {
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
    
    public String toCustom(){
        String results = "";
        results += "ID" + this.ID + '\n';
        results += "Subject: " + this.SubjectLine + '\n';
        results += "IntendedStatus: " + this.IntendedContactStatus.toString() + '\n';
        results += "IntendedPosition: " + this.IntendedPosition.toString() + '\n';
        results += "Body: " + this.Body + '\n';
    
        return results;
    }

}

enum SendStatus{
    Pending,
    Sent,
    Unknown;
}
