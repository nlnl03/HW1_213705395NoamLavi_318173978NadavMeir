import java.time.LocalDateTime;
import java.util.Date;
import javax.xml.crypto.Data;

public class SMSMessage extends Message implements IDigital {
    private String phoneNumber; // optional extra for SMS

    public SMSMessage(String sender, String content, Date sendDate, String recipient, String phoneNumber) throws SMSFormatException {
        super(sender, recipient, content, sendDate);
        setPhoneNumber(phoneNumber);
    }

    public SMSMessage(String sender, String content, String recipient, String phoneNumber) throws SMSFormatException {
        this(sender, content, new Date(), recipient, phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public final void setPhoneNumber(String phoneNumber) throws SMSFormatException {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new SMSFormatException("Phone number cannot be empty for SMS.");
        }
        String digits = phoneNumber.trim().replaceAll("[^0-9]", "");
        if (digits.length() < 7) {
            throw new SMSFormatException("Phone number seems invalid: " + phoneNumber);
        }
        this.phoneNumber = digits;
    }

    @Override
    public String printCommunicationMethod() {
        return "Sent via SMS Gateway";
    }

    @Override
    public String generatePreview() {
        String snippet = content.length() <= 20 ? content : content.substring(0, 20) + "...";
        return "[SMS] To: " + recipient + " | From: " + sender + " | " + snippet;
    }

    @Override
    public String toString() {
        return "SMSMessage{" +
                "sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sendDate=" + sendDate +
                ", content='" + content + '\'' +
                '}';
    }
}