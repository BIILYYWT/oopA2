// Inherits from Person, tracks theme park visitors
public class Visitor extends Person {
    private String visitorTicketId;
    private boolean isMember; // Whether a member?

    // Default constructor
    public Visitor() {
        super();
        this.visitorTicketId = "Unknown";
        this.isMember = false;
    }

    // Constructor with parameters
    public Visitor(String name, int age, String contactNumber, String visitorTicketId, boolean isMember) {
        super(name, age, contactNumber);
        this.visitorTicketId = visitorTicketId;
        this.isMember = isMember;
    }

    // Getter and Setter
    public String getVisitorTicketId() {
        return visitorTicketId;
    }

    public void setVisitorTicketId(String visitorTicketId) {
        this.visitorTicketId = visitorTicketId;
    }

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    @Override
    public String toString() {
        return "Visitor{" + super.toString() + ", visitorTicketId='" + visitorTicketId + "', isMember=" + isMember + "}";
    }

    // Formatting method for CSV file writing
    public String toCsvFormat() {
        // Format: Name, Age, Contact Information, Ticket ID, Membership Status (1=Yes, 0=No)
        return getName() + "," + getAge() + "," + getContactNumber() + "," + getVisitorTicketId() + "," + (isMember ? 1 : 0);
    }

    // Parse Visitor objects from CSV strings
    public static Visitor fromCsvFormat(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 5) {
            System.out.println("CSV format error, skipping this record:" + csvLine);
            return null;
        }
        try {
            String name = parts[0];
            int age = Integer.parseInt(parts[1]);
            String contact = parts[2];
            String ticketId = parts[3];
            boolean isMember = Integer.parseInt(parts[4]) == 1;
            return new Visitor(name, age, contact, ticketId, isMember);
        } catch (NumberFormatException e) {
            System.out.println("CSV data type error, skipping this record:" + csvLine);
            return null;
        }
    }
}