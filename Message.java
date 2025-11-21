
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public abstract class Message {
    protected String sender;
    protected String content;
    protected Date sendDate;
    protected String recipient;

    //get set
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
        if (sendDate == null) {
            throw new IllegalArgumentException("Send date cannot be null");
        }
        Date now = new Date();
        if (sendDate.after(now)) {
            throw new IllegalArgumentException("Send date cannot be in the future");
        }
        this.sendDate = new Date(sendDate.getTime());
    }

    public void setRecipient(String recipient) {
     if (recipient == null || recipient.isEmpty()) {
            throw new IllegalArgumentException("Recipient cannot be null or empty");
     }  
        this.recipient = recipient;
    }
    //
    public Message(String sender, String recipient, String content, Date sendDate) {
       setContent(content);
       setSender(sender);
       setRecipient(recipient);
       setSendDate(sendDate); 
    }

    public Message(String sender, String recipient, String content){
        this(sender, recipient, content, new Date());
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = (sendDate == null)
            ? "null"
            : sendDate.toInstant().atZone(java.time.ZoneId.systemDefault()).format(fmt);
        return "Message{sender='" + sender + "', recipient='" + recipient + "', sendDate=" + formattedDate + ", content='" + content + "'}";
    }

    public boolean find(ArrayList<String> keywords) {
        for (String keyword : keywords) {
            if (content.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
    public int getWordCount() {
        if (this.content == null || this.content.isEmpty()) {
            return 0;
        }
        String[] words = this.content.trim().split("\s+");
        return words.length;
    }

    public abstract String generatePreview();
        

}