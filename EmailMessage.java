import java.io.File;

public class EmailMessage extends Message {
 private String subject;

 public String getSubject() {
     return subject;
 }
 public void setSubject(String subject) {
     if (subject == null || subject.isEmpty()) {
         throw new IllegalArgumentException("Subject cannot be null or empty");
     }
     this.subject = subject;
 }
 public EmailMessage(String sender, String recipient, String content, Date sendDate, String subject) {
     super(sender, recipient, content, sendDate);
     setSubject(subject);
 }
 private void addAttachment(File attachment) {
     // Implementation for adding an attachment
 }

private void removeAttachment(File attachment) {
     // Implementation for removing an attachment
 }
}