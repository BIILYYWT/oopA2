// Core methods that must be implemented when defining the Ride class
public interface RideInterface {
    // Queue operations
    void addVisitorToQueue(Visitor visitor);
    void removeVisitorFromQueue();
    void printQueue();

    // Riding history operation
    void addVisitorToHistory(Visitor visitor);
    boolean checkVisitorFromHistory(Visitor visitor);
    int numberOfVisitors();
    void printRideHistory();

    // Riding cycle operation
    void runOneCycle();
}