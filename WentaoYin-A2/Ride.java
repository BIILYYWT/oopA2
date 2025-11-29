import java.io.*;
import java.util.*;

// Implement the RideInterface, including all functions such as queue, history, and IO.
public class Ride implements RideInterface {
    private String rideName;
    private String rideType; 
    private Employee rideOperator; 

    // Waiting Queue (FIFO)
    private Queue<Visitor> waitingLine;

    // Cycling History (LinkedList)
    private LinkedList<Visitor> rideHistory;

    // Attributes related to cycling cycles
    private int maxRider; 
    private int numOfCycles;

    // Constructor: Initialize all sets and properties
    public Ride() {
        this.rideName = "Unknown";
        this.rideType = "Unknown";
        this.rideOperator = null;
        this.waitingLine = new LinkedList<>(); // Queue is implemented using LinkedList
        this.rideHistory = new LinkedList<>();
        this.maxRider = 1; // 
        this.numOfCycles = 0;
    }

    public Ride(String rideName, String rideType, Employee rideOperator, int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.rideOperator = rideOperator;
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.maxRider = maxRider >= 1 ? maxRider : 1;
        this.numOfCycles = 0;
    }

    // Getter and Setter (core attributes)
    public Employee getRideOperator() {
        return rideOperator;
    }

    public void setRideOperator(Employee rideOperator) {
        this.rideOperator = rideOperator;
    }

    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        this.maxRider = maxRider >= 1 ? maxRider : 1;
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingLine.offer(visitor); // The addition method of Queue
            System.out.println("Successfully added the visitor to[" + rideName + "]Queue: " + visitor.getName());
        } else {
            System.out.println("Addition failed: The visitor object is empty");
        }
    }

    @Override
    public void removeVisitorFromQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println("Removal failed: [" + rideName + "]There are no tourists in the queue.");
            return;
        }
        Visitor removed = waitingLine.poll(); // The removal method of Queue (FIFO)
        System.out.println("Successfully removed the visitor from the [" + rideName + "] queue: " + removed.getName());;
    }

    @Override
    public void printQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println("[" + rideName + "]The waiting queue is empty.");
            return;
        }
        System.out.println("[" + rideName + "]waiting queue (totally" + waitingLine.size() + "person) : ");
        int index = 1;
        for (Visitor v : waitingLine) { // Traverse the queue 
            System.out.println(index + ". " + v);
            index++;
        }
    }

    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor);
            System.out.println("Successfully added the visitor to the [" + rideName + "] ride history: " + visitor.getName());
        } else {
            System.out.println("Addition failed: The visitor object is empty");
        }
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null || rideHistory.isEmpty()) {
            return false;
        }
        // Judging by the ticket ID
        for (Visitor v : rideHistory) {
            if (v.getVisitorTicketId().equals(visitor.getVisitorTicketId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size();
    }

    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("[" + rideName + "]Riding history is empty");
            return;
        }
        System.out.println("[" + rideName + "] Cycling history (totally" + rideHistory.size() + "person) : ");
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor v = iterator.next();
            System.out.println(index + ". " + v);
            index++;
        }
    }

    public void sortRideHistory(Comparator<Visitor> visitorComparator) {
        if (rideHistory.isEmpty()) {
            System.out.println("Sorting failed: [" + rideName + "]Riding history is empty");
            return;
        }
        if (visitorComparator == null) {
            System.out.println("Sorting failed: Comparator object is null");
            return;
        }
        Collections.sort(rideHistory, visitorComparator);
        System.out.println("[" + rideName + "]Riding history sorting completed");
    }

    @Override
    public void runOneCycle() {
        if (rideOperator == null) {
            System.out.println("Run failed: [" + rideName + "]Unassigned operator");
            return;
        }
        if (waitingLine.isEmpty()) {
            System.out.println("Run failed: [" + rideName + "]The waiting queue is empty.");
            return;
        }

        int ridersThisCycle = Math.min(maxRider, waitingLine.size());
        System.out.println("\n[" + rideName + "]Start running" + (numOfCycles + 1) + "Cycle, planned passenger capacity" + ridersThisCycle + "person");

        for (int i = 0; i < ridersThisCycle; i++) {
            Visitor rider = waitingLine.poll();
            addVisitorToHistory(rider); 
        }

        numOfCycles++; 
        System.out.println("The [" + rideName + "] ride has completed its " + numOfCycles + "th cycle\n");
    }

    // Export cycling history to CSV
    public void exportRideHistory(String filePath) {
        if (rideHistory.isEmpty()) {
            System.out.println("Export failed:  [" + rideName + "]The riding history is empty.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("name,age,contactNumber,visitorTicketId,isMember");
            writer.newLine();
            for (Visitor v : rideHistory) {
                writer.write(v.toCsvFormat());
                writer.newLine();
            }
            System.out.println("Successfully exported the [" + rideName + "] ride history to: " + filePath);
        } catch (IOException e) {
            System.out.println("Export failed: IO error - " + e.getMessage());
        }
    }

    // Import cycling history from CSV
    public void importRideHistory(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Import failed: The file does not exist - " + filePath);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int importedCount = 0;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                Visitor visitor = Visitor.fromCsvFormat(line);
                if (visitor != null) {
                    rideHistory.add(visitor);
                    importedCount++;
                }
            }

            System.out.println("Successfully imported " + importedCount + " visitors from [" + filePath + "] to the [" + rideName + "] ride history");
        } catch (IOException e) {
            System.out.println("Import failed: The file does not exist - " + e.getMessage());
        }
    }
}