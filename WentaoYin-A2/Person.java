// Abstract class: cannot be instantiated, serves as the parent class for Employee and Visitor.
public abstract class Person {
    private String name;
    private int age;
    private String contactNumber;

    // Default constructor
    public Person() {
        this.name = "Unknown";
        this.age = 0;
        this.contactNumber = "Unknown";
    }

    // Constructor with parameters, initialises all parent class properties
    public Person(String name, int age, String contactNumber) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters, adhering to the principle of encapsulation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 100) {
            this.age = age;
        } else {
            System.out.println("The age input is invalid; it must be between 0 and 100.");
        }
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", contactNumber='" + contactNumber + "'}";
    }
}