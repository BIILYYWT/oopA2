public class AssignmentTwo {
    public static void main(String[] args) {
        AssignmentTwo demo = new AssignmentTwo();
        
        // 依次运行所有模块演示
        System.out.println("Waiting Queue");
        demo.partThree();
        
        System.out.println("\nHistory of cycling");
        demo.partFourA();
        
        System.out.println("\nHistorical sorting");
        demo.partFourB();
        
        System.out.println("\nCycling cycle");
        demo.partFive();
        
        System.out.println("\nExport CSV");
        demo.partSix();
        
        System.out.println("\nImport CSV");
        demo.partSeven();
    }

    // Waiting Queue
    public void partThree() {
        // Create an operator (used to initialize Ride)
        Employee operator = new Employee("Zhang San", 30, "13800138000", "EMP001", "Roller Coaster");
        // Create a Ride object
        Ride rollerCoaster = new Ride("Roller Coaster", "Thrilling", operator, 2);
        
        // Add tourists to the queue
        rollerCoaster.addVisitorToQueue(new Visitor("Li Si", 25, "13900139000", "TICK001", false));
        rollerCoaster.addVisitorToQueue(new Visitor("Wang Wu", 35, "13700137000", "TICK002", true));
        rollerCoaster.addVisitorToQueue(new Visitor("Zhao Liu", 22, "13600136000", "TICK003", false));
        rollerCoaster.addVisitorToQueue(new Visitor("Chen Qi", 40, "13500135000", "TICK004", true));
        rollerCoaster.addVisitorToQueue(new Visitor("Han Ba", 18, "13400134000", "TICK005", false));
        
        // Print queue
        System.out.println("\nQueue after adding tourists: ");
        rollerCoaster.printQueue();
        
        // Remove a tourist
        System.out.println("\nRemove a tourist: ");
        rollerCoaster.removeVisitorFromQueue();
        
        System.out.println("\nQueue after removal: ");
        rollerCoaster.printQueue();
    }

    // History of cycling
    public void partFourA() {
        Ride thunderstorm = new Ride("Thunderstorm", "Water Ride", null, 4);
        
        // Add tourists to the history
        Visitor v1 = new Visitor("Tom", 28, "123456", "TICK006", false);
        Visitor v2 = new Visitor("Sherly", 24, "654321", "TICK007", true);
        Visitor v3 = new Visitor("Ben", 32, "112233", "TICK008", false);
        Visitor v4 = new Visitor("David", 26, "332211", "TICK009", true);
        Visitor v5 = new Visitor("Alice", 29, "445566", "TICK010", false);
        
        thunderstorm.addVisitorToHistory(v1);
        thunderstorm.addVisitorToHistory(v2);
        thunderstorm.addVisitorToHistory(v3);
        thunderstorm.addVisitorToHistory(v4);
        thunderstorm.addVisitorToHistory(v5);
        
        //  Check if the visitor is in history）
        Visitor checkV = new Visitor("Sherly", 24, "654321", "TICK007", true);
        System.out.println("\nCheck if Sherly is in history: " + (thunderstorm.checkVisitorFromHistory(checkV) ? "Y" : "N"));
        
        // Print the number of historical tourists）
        System.out.println("Total number of historical cycling tourists: " + thunderstorm.numberOfVisitors());
        
        // Print riding history
        System.out.println("\nDetails of riding history: ");
        thunderstorm.printRideHistory();
    }

    // Historical sorting
    public void partFourB() {
        Ride ferrisWheel = new Ride("Ferris Wheel", "Family", null, 6);
        VisitorComparator comparator = new VisitorComparator(); // Custom Sorter
        
        ferrisWheel.addVisitorToHistory(new Visitor("Mike", 35, "778899", "TICK011", false));
        ferrisWheel.addVisitorToHistory(new Visitor("Lily", 22, "998877", "TICK012", true));
        ferrisWheel.addVisitorToHistory(new Visitor("John", 40, "556677", "TICK013", false));
        ferrisWheel.addVisitorToHistory(new Visitor("Emma", 31, "776655", "TICK014", true));
        ferrisWheel.addVisitorToHistory(new Visitor("Bob", 27, "223344", "TICK015", false));

        System.out.println("\nRiding history before sorting: ");
        ferrisWheel.printRideHistory();
        
        // Sorting
        ferrisWheel.sortRideHistory(comparator);
        
        System.out.println("\nSorted riding history (members first + age in descending order):");
        ferrisWheel.printRideHistory();
    }

    // Cycling cycle
    public void partFive() {
        // Create an operator and a Ride (maxRider=2)
        Employee operator = new Employee("Li Ming", 28, "13100131000", "EMP002", "Pirate Ship");
        Ride pirateShip = new Ride("Pirate Ship", "Thrilling", operator, 2);
        
        //  Add 10 tourists to the queue
        for (int i = 1; i <= 10; i++) {
            pirateShip.addVisitorToQueue(new Visitor("Visitor" + i, 20 + i, "1300013000" + i, "TICK0" + (15 + i), i % 3 == 0));
        }
        
        //  Print the queue before running
        System.out.println("Queue waiting before running: ");
        pirateShip.printQueue();
        
        // Run one cycle
        pirateShip.runOneCycle();
        
        // Print the queue and history after running
        System.out.println("Waiting queue after running:");
        pirateShip.printQueue();
        
        System.out.println("Riding history after operation:");
        pirateShip.printRideHistory();
    }

    // Export CSV
    public void partSix() {
        Ride bumperCars = new Ride("Bumper Cars", "Family", null, 4);
        
        bumperCars.addVisitorToHistory(new Visitor("Charlie", 19, "14100141000", "TICK026", false));
        bumperCars.addVisitorToHistory(new Visitor("Diana", 23, "14200142000", "TICK027", true));
        bumperCars.addVisitorToHistory(new Visitor("Ethan", 25, "14300143000", "TICK028", false));
        bumperCars.addVisitorToHistory(new Visitor("Fiona", 28, "14400144000", "TICK029", true));
        bumperCars.addVisitorToHistory(new Visitor("George", 33, "14500145000", "TICK030", false));
        
        // Export to CSV file
        String exportPath = "ride_history.csv";
        bumperCars.exportRideHistory(exportPath);
    }

    // Import CSV
    public void partSeven() {
        Ride merryGoRound = new Ride("Merry-Go-Round", "Kids", null, 8);
        
        // Import from the CSV file
        String importPath = "ride_history.csv";
        merryGoRound.importRideHistory(importPath);
        
        // Verify the import result
        System.out.println("\nTotal number of tourists after import: " + merryGoRound.numberOfVisitors());
        System.out.println("Details of tourists after import: ");
        merryGoRound.printRideHistory();
    }
}
