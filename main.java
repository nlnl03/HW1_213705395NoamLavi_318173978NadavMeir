
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class main {
public static void main(String[] args) {
       ArrayList<Message> messages = new ArrayList<>();
        messages.add(new EmailMessage("Yossi Kaufman", "Aviad Baron", "Hello Aviad, how are you?", "Just checking up"));
        messages.add(new EmailMessage("Aviad Baron", "Yossi Kaufman", "Hi Yossi, I'm fabulous, thanks!", "Re: Just checking up"));
        messages.add(new BoardMessage("Admin", "All Users", "System maintenance scheduled for tonight.", new Date(System.currentTimeMillis() + 86400000L)));
        messages.add(new BoardMessage(
            "Dana",                               
            "Staff",                                
            "Important update to the access policy",
            new Date(),                             
            PriorityType.CRITICAL,                   
            new Date(System.currentTimeMillis() + 3L * 24L * 60L * 60L * 1000L) 
        ));
        
    }
   
}
