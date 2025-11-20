import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class EmailMessage extends Message implements IDigital
{

    private String subject;
    private ArrayList<File> attachments;

    //get set
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        if (subject == null || subject.isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be null or empty");
        }
        this.subject = subject;
    }
    //
    public EmailMessage(String sender, String recipient, String content, Date sendDate, String subject, ArrayList<File> attachments) {
        super(sender, recipient, content, sendDate);
        setSubject(subject);
        this.attachments = attachments;
    }
    public EmailMessage(String sender, String recipient, String content, String subject){
        this(sender, recipient, content, new Date(), subject, new ArrayList<File>());
    }

    @Override
    public String toString() {
        return super.toString() + "\nSubject: " + subject + "\nAttachments: " + attachments.size();
    }
    
    public String printCommunicationMethod() {
        return "Sent via Email Server.";
    }

    
    public String generatePreview() {
        String subj = (this.subject == null) ? "" : this.subject.trim();
        String senderName = (this.sender == null) ? "" : this.sender;
        return "[Email] Subject: " + subj + " | From: " + senderName;
    }    
    
    private void addAttachment(File attachment) {
        if (attachment == null) {
            throw new IllegalArgumentException("Attachment cannot be null");
        }
        attachments.add(attachment);
    }

    public boolean removeAttachment(File attachment) throws AttachmentException {
        if (attachment == null) {
            throw new IllegalArgumentException("Attachment cannot be null");
        }
        boolean removedAny = false;
        for (int i = attachments.size() - 1; i >= 0; i--) {
            File f = attachments.get(i);
            if (f.equals(attachment)) {
                attachments.remove(i);
                removedAny = true;
            }
        }
        if (!removedAny) {
            throw new AttachmentException("Attachment not found: " + attachment);
        }
        return true;
    }
}