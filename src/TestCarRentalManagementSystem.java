import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

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
                    returnCheckoutBilling(vehicles, input, drivers, bookedCustomerIDs);
                    break;

                case 4:
                    loyalty(drivers);
                    break;

                case 5:
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

        System.out.println("Enter Customer ID: ");
        String customerID = input.next();

        int customerIndex = -1;

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

        for (int i = 0; i < vehicles.length; i++) {

            if (vehicles[i].getVehicleID().equals(vehicleID)) {

                found = true;

                if (vehicles[i].getStatus().equals("Available")) {

                    vehicles[i].setStatus("Rented");
                    bookedCustomerIDs[i] = customerID;

                    System.out.println("Booking Successful!");
                    break;
                }
                else {
                    System.out.println("Vehicle is not available!");
                    break;
                }
            }
        }

        if (found == false) {
            System.out.println("Vehicle not found!");
        }
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
                            vehicles[i].getDailyRental() * days * (1 - discountRate);

                    System.out.println("Final Amount: RM" + totalAmount);

                    // Bonus: Save order details to a text file
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

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("RentalOrders.txt", true))) {

            writer.write("===== RENTAL ORDER =====");
            writer.newLine();

            writer.write("Vehicle ID: " + vehicleID);
            writer.newLine();

            writer.write("Customer ID: " + customerID);
            writer.newLine();

            writer.write("Rental Days: " + days);
            writer.newLine();

            writer.write("Daily Rental: RM" + dailyRental);
            writer.newLine();

            writer.write("Membership: " + membership);
            writer.newLine();

            writer.write("Discount: " + (discountRate * 100) + "%");
            writer.newLine();

            writer.write("Total Amount: RM" + totalAmount);
            writer.newLine();

            writer.write("========================");
            writer.newLine();
            writer.newLine();

            System.out.println("Order details saved successfully!");

        }
        catch (IOException e) {
            System.out.println("Error writing order details to file.");
        }
    }
}
