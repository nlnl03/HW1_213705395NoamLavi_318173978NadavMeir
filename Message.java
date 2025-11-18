
import java.util.Date;

public abstract class Message {
    protected String sender;
    protected String content;
    protected Date sendDate;
    protected String recipient;

    public String getSender() {
        return sender;
    }
    public String getContent() {
        return content;
    }
    public Date getSendDate() {
        return sendDate;
    }
    public String getRecipient() {
        return recipient;
    } 

    public void setSender(String sender) {
     if (sender == null || sender.isEmpty()) {
            throw new IllegalArgumentException("Sender cannot be null or empty");
    }
        this.sender = sender;
    }
    public void setContent(String content) {
     if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
     }
        this.content = content;
    }
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public void setRecipient(String recipient) {
     if (recipient == null || recipient.isEmpty()) {
            throw new IllegalArgumentException("Recipient cannot be null or empty");
     }  
        this.recipient = recipient;
    }

    @Override
    public String toString() {
        return "From: " + sender + "\nTo: " + recipient + "\nDate: " + sendDate + "\nContent: " + content;
    }

    public Message(String sender, String recipient, String content, Date sendDate) {
       setContent(content);
       setSender(sender);
       setRecipient(recipient);
       setSendDate(sendDate); 
    }
 }