import java.util.Date;

enum PriorityType
{
    REGULAR,
    URGENT,
    CRITICAL
}
public class BoardMessage extends Message
{
    private PriorityType priority;
    private Date expirationDate;
    
    public BoardMessage(String sender, String recipient, String content, Date sendDate, PriorityType priority, Date expirationDate) {
        super(sender, recipient, content, sendDate);
        
        setPriority(priority);
        setExpirationDate(expirationDate);
    }
    public PriorityType getPriority() {
        return priority;
    }
    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    @Override
    public String toString() {
        return super.toString() + "\nPriority: " + priority + "\nExpiration Date: " + expirationDate;
    }
}
