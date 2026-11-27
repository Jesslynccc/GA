import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class TestCarRentalManagementSystem {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Driver driver1 = new Driver("C001", 0);
        Driver driver2 = new Driver("C002", 3);
        Driver driver3 = new Driver("C003", 5);

        Driver[] drivers = {driver1, driver2, driver3};

        String[] bookedCustomerIDs = {"", "", "", ""};

        Sedan vios = new Sedan("S001", "Toyota", "Vios",
                120.0, "Available", 2, 18.5);

        Sedan city = new Sedan("S002", "Honda", "City",
                150.0, "Available", 3, 17.8);

        SUV fortuner = new SUV("U001", "Toyota", "Fortuner",
                250.0, "Available", 7, "Touchscreen", "4WD");

        SUV crv = new SUV("U002", "Honda", "CR-V",
                220.0, "Available", 5, "Apple CarPlay", "Normal");

        Vehicle[] vehicles = {vios, city, fortuner, crv};

        // Load previous system data
        loadSystemData(vehicles, drivers, bookedCustomerIDs);

        int choice = 0;

        while (choice != 5) {

            System.out.println("==== CAR RENTAL SYSTEM ====");
            System.out.println("1. View Available Vehicle");
            System.out.println("2. Booking");
            System.out.println("3. Return, Checkout and Billing");
            System.out.println("4. Loyalty");
            System.out.println("5. Exit");

            try {
                choice = Integer.parseInt(input.next());
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {

                case 1:
                    viewAvailableVehicles(vehicles);
                    break;

                case 2:
                    booking(vehicles, input, drivers, bookedCustomerIDs);
                    break;

                case 3:
                    returnCheckoutBilling(vehicles, input, drivers,
                            bookedCustomerIDs);
                    break;

                case 4:
                    loyalty(drivers);
                    break;

                case 5:
                    saveSystemData(vehicles, drivers, bookedCustomerIDs);
                    exit();
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        input.close();
    }

    // Feature 1: View Available Vehicles
    public static void viewAvailableVehicles(Vehicle[] vehicles) {

        System.out.println("Available Vehicles");

        for (int i = 0; i < vehicles.length; i++) {

            if (vehicles[i].getStatus().equals("Available")) {
                System.out.println(vehicles[i]);
                System.out.println("------------------");
            }
        }
    }

    // Feature 2: Booking
    public static void booking(Vehicle[] vehicles, Scanner input,
                               Driver[] drivers,
                               String[] bookedCustomerIDs) {

        System.out.println("Booking");
        System.out.println("Enter Vehicle ID: ");

        String vehicleID = input.next();
        boolean found = false;
        int vehicleIndex = -1;

        // Check Vehicle ID
        for (int i = 0; i < vehicles.length; i++) {

            if (vehicles[i].getVehicleID().equals(vehicleID)) {
                found = true;
                vehicleIndex = i;
                break;
            }
        }

        if (found == false) {
            System.out.println("Vehicle not found!");
            return;
        }

        // Check Vehicle Availability
        if (!vehicles[vehicleIndex].getStatus().equals("Available")) {
            System.out.println("Vehicle is not available!");
            return;
        }

        // Enter Customer ID
        System.out.println("Enter Customer ID: ");
        String customerID = input.next();

        int customerIndex = -1;

        // Check Customer ID
        for (int j = 0; j < drivers.length; j++) {

            if (drivers[j].getDriverID().equals(customerID)) {
                customerIndex = j;
                break;
            }
        }

        if (customerIndex == -1) {
            System.out.println("Customer not found!");
            return;
        }

        // Complete Booking
        vehicles[vehicleIndex].setStatus("Rented");
        bookedCustomerIDs[vehicleIndex] = customerID;

        System.out.println("Booking Successful!");
    }





    // Feature 3: Return, Checkout and Billing
    public static void returnCheckoutBilling(Vehicle[] vehicles,
                                             Scanner input,
                                             Driver[] drivers,
                                             String[] bookedCustomerIDs) {

        System.out.println("Return Checkout and Billing");
        System.out.println("Enter Vehicle ID: ");

        String vehicleID = input.next();
        boolean found = false;

        for (int i = 0; i < vehicles.length; i++) {

            if (vehicles[i].getVehicleID().equals(vehicleID)) {

                found = true;

                if (vehicles[i].getStatus().equals("Rented")) {

                    int days = 0;
                    boolean valid = false;

                    while (valid == false) {

                        try {
                            System.out.println("Enter Rental Days: ");
                            days = input.nextInt();

                            if (days > 0) {
                                valid = true;
                            }
                            else {
                                System.out.println(
                                        "Invalid days! Please enter again.");
                            }
                        }
                        catch (InputMismatchException e) {
                            System.out.println(
                                    "Invalid input! Please enter a number.");
                            input.next();
                        }
                    }

                    String returnedCustomerID = bookedCustomerIDs[i];
                    int returnedCustomerIndex = -1;

                    for (int j = 0; j < drivers.length; j++) {

                        if (drivers[j].getDriverID().equals(returnedCustomerID)) {
                            returnedCustomerIndex = j;
                            break;
                        }
                    }

                    if (returnedCustomerIndex == -1) {
                        System.out.println("Customer not found!");
                        return;
                    }

                    String membership =
                            drivers[returnedCustomerIndex].getMembership();

                    double discountRate = 0.0;

                    if (membership.equals("Regular")) {
                        discountRate = 0.10;
                    }
                    else if (membership.equals("VIP")) {
                        discountRate = 0.20;
                    }

                    double totalAmount =
                            vehicles[i].getDailyRental() * days
                                    * (1 - discountRate);

                    System.out.println("Final Amount: RM" + totalAmount);

                    // Bonus: Save order details
                    saveOrderToFile(
                            vehicles[i].getVehicleID(),
                            returnedCustomerID,
                            days,
                            vehicles[i].getDailyRental(),
                            membership,
                            discountRate,
                            totalAmount
                    );

                    vehicles[i].setStatus("Available");

                    drivers[returnedCustomerIndex].setCompletedRentals(
                            drivers[returnedCustomerIndex].getCompletedRentals() + 1
                    );

                    bookedCustomerIDs[i] = "";

                    System.out.println("Return Successful!");
                    break;
                }
                else {
                    System.out.println("Vehicle is already available!");
                    break;
                }
            }
        }

        if (found == false) {
            System.out.println("Vehicle not found!");
        }
    }

    // Feature 4: Loyalty
    public static void loyalty(Driver[] drivers) {

        System.out.println("Loyalty");

        for (int i = 0; i < drivers.length; i++) {

            String membership = drivers[i].getMembership();

            System.out.println(drivers[i].getDriverID() + ":" + membership);
        }
    }

    // Feature 5: Exit
    public static void exit() {

        System.out.println("Thank you for using Car Rental System!");
    }

    // Bonus: File I/O - Save Order Details
    public static void saveOrderToFile(String vehicleID,
                                       String customerID,
                                       int days,
                                       double dailyRental,
                                       String membership,
                                       double discountRate,
                                       double totalAmount) {

        File file = new File("RentalOrders.txt");

        String oldOrders = "";

        // Read previous orders first
        if (file.exists()) {

            try (Scanner fileInput = new Scanner(file)) {

                while (fileInput.hasNextLine()) {
                    oldOrders = oldOrders + fileInput.nextLine() + "\n";
                }
            }
            catch (IOException ex) {
                System.out.println(ex.getMessage());
                return;
            }
        }

        // Write previous orders and new order
        try (PrintWriter pw = new PrintWriter(file)) {

            pw.print(oldOrders);

            pw.println("===== RENTAL ORDER =====");
            pw.println("Vehicle ID: " + vehicleID);
            pw.println("Customer ID: " + customerID);
            pw.println("Rental Days: " + days);
            pw.println("Daily Rental: RM" + dailyRental);
            pw.println("Membership: " + membership);
            pw.println("Discount: " + (discountRate * 100) + "%");
            pw.println("Total Amount: RM" + totalAmount);
            pw.println("========================");
            pw.println();

            System.out.println("Order details saved successfully!");

        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Bonus: Save System Data
    public static void saveSystemData(Vehicle[] vehicles,
                                      Driver[] drivers,
                                      String[] bookedCustomerIDs) {

        String fileName =
                "CarRentalData_" + System.currentTimeMillis() + ".txt";

        File file = new File(fileName);

        try (PrintWriter pw = new PrintWriter(file)) {

            for (int i = 0; i < drivers.length; i++) {

                pw.println("DRIVER");
                pw.println(drivers[i].getDriverID());
                pw.println(drivers[i].getCompletedRentals());
            }

            for (int i = 0; i < vehicles.length; i++) {

                pw.println("VEHICLE");
                pw.println(vehicles[i].getVehicleID());
                pw.println(vehicles[i].getStatus());
                pw.println(bookedCustomerIDs[i]);
            }

            System.out.println("System data saved successfully!");

        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Bonus: Load System Data
    public static void loadSystemData(Vehicle[] vehicles,
                                      Driver[] drivers,
                                      String[] bookedCustomerIDs) {

        File folder = new File(".");
        File[] files = folder.listFiles();

        File latestFile = null;

        if (files != null) {

            for (int i = 0; i < files.length; i++) {

                if (files[i].getName().startsWith("CarRentalData_")
                        && files[i].getName().endsWith(".txt")) {

                    if (latestFile == null ||
                            files[i].lastModified() > latestFile.lastModified()) {

                        latestFile = files[i];
                    }
                }
            }
        }

        if (latestFile == null) {
            System.out.println("No saved data found. Starting new system.");
            return;
        }

        try (Scanner fileInput = new Scanner(latestFile)) {

            while (fileInput.hasNextLine()) {

                String recordType = fileInput.nextLine();

                if (recordType.equals("DRIVER")) {

                    if (fileInput.hasNextLine()) {

                        String driverID = fileInput.nextLine();
                        int completedRentals =
                                Integer.parseInt(fileInput.nextLine());

                        for (int i = 0; i < drivers.length; i++) {

                            if (drivers[i].getDriverID().equals(driverID)) {

                                drivers[i].setCompletedRentals(completedRentals);
                            }
                        }
                    }
                }

                else if (recordType.equals("VEHICLE")) {

                    if (fileInput.hasNextLine()) {

                        String vehicleID = fileInput.nextLine();
                        String status = fileInput.nextLine();
                        String customerID = fileInput.nextLine();

                        for (int i = 0; i < vehicles.length; i++) {

                            if (vehicles[i].getVehicleID().equals(vehicleID)) {

                                vehicles[i].setStatus(status);
                                bookedCustomerIDs[i] = customerID;
                            }
                        }
                    }
                }
            }

            System.out.println("System data loaded successfully!");

        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        catch (NumberFormatException ex) {
            System.out.println("Invalid saved data.");
        }
    }
}
