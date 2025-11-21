import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;


public class main {
    private static ArrayList<Message> messages = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedData();

        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addMessageInteractive();
                    break;
                case "2":
                    deleteMessageInteractive();
                    break;
                case "3":
                    printAllMessages();
                    break;
                case "4":
                    countMessagesContainingWords();
                    break;
                case "5":
                    printDigitalMessagesOnly();
                    break;
                case "6":
                    printAllPreviews();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
        System.out.println("Goodbye!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n=== Messaging System Menu ===");
        System.out.println("1 - Add message");
        System.out.println("2 - Delete message (by index)");
        System.out.println("3 - Print all messages");
        System.out.println("4 - Count messages containing words");
        System.out.println("5 - Print digital messages only");
        System.out.println("6 - Print message previews");
        System.out.println("0 - Exit");
        System.out.print("Choose an option: ");
    }

    private static void seedData() {
        // 2 BoardMessages
        messages.add(new BoardMessage("Dana", "Important update about campus schedule", new Date().minusDays(2), "All", BoardMessage.PriorityType.REGULAR, "Main Hall"));
        messages.add(new BoardMessage("Ori", "Emergency: building closed", new Date().minusHours(5), "All", BoardMessage.PriorityType.URGENT, "North Block"));

        // 2 EmailMessages
        ArrayList<File> att1 = new ArrayList<>();
        att1.add(new File("report.pdf", "pdf"));
        EmailMessage e1 = new EmailMessage("Rona", "Please find attached the project report.", new Date().minusDays(1), "professor@uni.edu", "Project Submission", att1);
        EmailMessage e2 = new EmailMessage("Avi", "Meeting minutes attached.", "team@company.com", "Meeting Minutes");
        e2.addAttachment(new File("minutes.docx", "docx"));
        messages.add(e1);
        messages.add(e2);

        // 2 SMSMessages
        try {
            messages.add(new SMSMessage("Service", "Your verification code is 123456", new Date().minusMinutes(30), "+972501234567", "+972501234567"));
            messages.add(new SMSMessage("Admin", "System will be down at midnight", "AllUsers", "0509998888"));
        } catch (Exception ex) {
            System.out.println("Error seeding SMS messages: " + ex.getMessage());
        }
    }

    private static void addMessageInteractive() {
        System.out.println("Choose type to add: 1-Board, 2-Email, 3-SMS");
        String t = scanner.nextLine().trim();
        try {
            switch (t) {
                case "1":
                    System.out.print("Sender: ");
                    String bs = scanner.nextLine();
                    System.out.print("Recipient: ");
                    String br = scanner.nextLine();
                    System.out.print("Content: ");
                    String bc = scanner.nextLine();
                    System.out.print("Location: ");
                    String loc = scanner.nextLine();
                    System.out.print("PriorityType (REGULAR/URGENT/CRITICAL) or press Enter: ");
                    String p = scanner.nextLine().trim();
                    BoardMessage.PriorityType pr = BoardMessage.PriorityType.REGULAR;
                    if (!p.isEmpty()) {
                        pr = BoardMessage.PriorityType.valueOf(p.toUpperCase());
                    }
                    BoardMessage bm = new BoardMessage(bs, bc, new Date(), br, pr, loc);
                    messages.add(bm);
                    System.out.println("Board message added.");
                    break;
                case "2":
                    System.out.print("Sender: ");
                    String es = scanner.nextLine();
                    System.out.print("Recipient (email): ");
                    String er = scanner.nextLine();
                    System.out.print("Subject: ");
                    String subj = scanner.nextLine();
                    System.out.print("Content: ");
                    String ec = scanner.nextLine();
                    EmailMessage em = new EmailMessage(es, ec, new Date(), er, subj, new ArrayList<>());
                    // optionally add attachments
                    System.out.print("Add attachment? (y/n): ");
                    String aa = scanner.nextLine().trim();
                    while (aa.equalsIgnoreCase("y")) {
                        System.out.print("Attachment name: ");
                        String an = scanner.nextLine();
                        System.out.print("Attachment type: ");
                        String at = scanner.nextLine();
                        em.addAttachment(new File(an, at));
                        System.out.print("Add another? (y/n): ");
                        aa = scanner.nextLine().trim();
                    }
                    messages.add(em);
                    System.out.println("Email message added.");
                    break;
                case "3":
                    System.out.print("Sender: ");
                    String ss = scanner.nextLine();
                    System.out.print("Recipient: ");
                    String sr = scanner.nextLine();
                    System.out.print("Phone number for SMS: ");
                    String pn = scanner.nextLine();
                    System.out.print("Content: ");
                    String sc = scanner.nextLine();
                    SMSMessage sm = new SMSMessage(ss, sc, new Date(), sr, pn);
                    messages.add(sm);
                    System.out.println("SMS message added.");
                    break;
                default:
                    System.out.println("Invalid type.");
            }
        } catch (IllegalArgumentException ia) {
            System.out.println("Invalid input: " + ia.getMessage());
        } catch (AttachmentException ae) {
            System.out.println("Attachment error: " + ae.getMessage());
        } catch (SMSFormatException se) {
            System.out.println("SMS format error: " + se.getMessage());
        } catch (Exception ex) {
            System.out.println("Error adding message: " + ex.getMessage());
        }
    }

    private static void deleteMessageInteractive() {
        printAllMessages();
        System.out.print("Enter index to delete (starting at 0): ");
        String s = scanner.nextLine().trim();
        try {
            int idx = Integer.parseInt(s);
            if (idx < 0 || idx >= messages.size()) {
                System.out.println("Index out of range.");
            } else {
                Message removed = messages.remove(idx);
                System.out.println("Removed: " + removed.generatePreview());
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a valid integer.");
        }
    }

    private static void printAllMessages() {
        System.out.println("\n--- All messages (" + messages.size() + ") ---");
        for (int i = 0; i < messages.size(); i++) {
            System.out.println("[" + i + "] " + messages.get(i).toString());
        }
    }

    private static void countMessagesContainingWords() {
        System.out.print("Enter comma-separated words to search for: ");
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        ArrayList<String> words = new ArrayList<>();
        for (String p : parts) {
            if (!p.trim().isEmpty()) words.add(p.trim());
        }
        int count = 0;
        for (Message m : messages) {
            if (m.find(words)) count++;
        }
        System.out.println("Messages containing at least one of the words: " + count);
    }

    private static void printDigitalMessagesOnly() {
        System.out.println("\n--- Digital messages only ---");
        for (Message m : messages) {
            if (m instanceof IDigital) {
                IDigital d = (IDigital) m;
                System.out.println(m.generatePreview() + " || Method: " + d.printCommunicationMethod());
            }
        }
    }

    private static void printAllPreviews() {
        System.out.println("\n--- Previews ---");
        for (Message m : messages) {
            System.out.println(m.generatePreview());
        }
    }
}
