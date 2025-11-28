// Inherits from Person, tracks amusement ride operators
public class Employee extends Person {
    private String employeeId;
    private String assignedRideName;

    // Default constructor
    public Employee() {
        super(); // Invoke the parent class's default constructor
        this.employeeId = "Unknown";
        this.assignedRideName = "Unknown";
    }

    // Constructor with parameters, initialising parent class + child class properties
    public Employee(String name, int age, String contactNumber, String employeeId, String assignedRideName) {
        super(name, age, contactNumber); // Calling the parent class's parameterised constructor
        this.employeeId = employeeId;
        this.assignedRideName = assignedRideName;
    }

    // Getter and Setter
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getAssignedRideName() {
        return assignedRideName;
    }

    public void setAssignedRideName(String assignedRideName) {
        this.assignedRideName = assignedRideName;
    }

    @Override
    public String toString() {
        return "Employee{" + super.toString() + ", employeeId='" + employeeId + "', assignedRideName='" + assignedRideName + "'}";
    }
}