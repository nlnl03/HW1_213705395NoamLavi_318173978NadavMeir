import java.util.Date;



public class BoardMessage extends Message
{
    
    enum PriorityType
    {
    REGULAR,
    URGENT,
    CRITICAL
    }
    
    private PriorityType priority;
    private Date expirationDate;
    
    //get set
    public PriorityType getPriority() {
        return priority;
    }
    public void setPriority(PriorityType priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }
        this.priority = priority;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        if (expirationDate == null) {
            throw new IllegalArgumentException("Expiration date cannot be null");
        }
        Date now = new Date();
        if (expirationDate.before(now)) {
            throw new IllegalArgumentException("Expiration date cannot be in the past");
        }
        this.expirationDate = new Date(expirationDate.getTime());
    }
    //

    public BoardMessage(String sender, String recipient, String content, Date sendDate, PriorityType priority, Date expirationDate) {
        super(sender, recipient, content, sendDate); 
        setPriority(priority);
        setExpirationDate(expirationDate);
    }
    public BoardMessage(String sender, String recipient, String content, Date expirationDate){
        this(sender, recipient, content, new Date(), PriorityType.REGULAR, expirationDate);
    }

    @Override
    public String toString() {
        return super.toString() + "\nPriority: " + priority + "\nExpiration Date: " + expirationDate;
    }

    public long daysUntilExpiration() {
        if (this.expirationDate == null) {
            return -1;
        }
        long millisPerDay = 24L * 60L * 60L * 1000L;
        long diff = this.expirationDate.getTime() - new Date().getTime();
        return diff / millisPerDay;
    }
    
    @Override
    public String generatePreview() {
        String senderName = (this.sender == null) ? "" : this.sender;
        String contentStr = (this.content == null) ? "" : this.content.trim();
        int maxLen = 15;
        String shortened = contentStr.length() > maxLen ? contentStr.substring(0, maxLen) + "..." : contentStr;
        String tag = (this instanceof BoardMessage) ? "Board" : "Message";
        return "[" + tag + "] " + senderName + ": " + shortened;
    }

}
