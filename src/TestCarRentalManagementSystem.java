import java.util.Scanner;

public class TestCarRentalManagementSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] customerID = {"C001", "C002", "C003"};
        String[] customerName = {"Jesslyn", "Erin", "JiaYing"};
        int[] completedRentals = {0, 5, 6} ;

        Sedan vios = new Sedan("S001", "Toyota", "Vios", 120.0, true, "GPS", 2, 18.5);
        Sedan city = new Sedan("S002", "Honda", "City", 150.0, true, "Child Seat", 3, 17.8);
        SUV fortuner = new SUV("U001", "Honda", "CRV", 220.0, true, "Dashcam", 5);
        SUV crv = new SUV("U002", "Honda", "CRV", 220.0, true, "Dashcam", 5);

        vehicles[0] = vios;
        vehicles[1] = city;
        vehicles[2] = fortuner;
        vehicles[3] = crv;


        int choices = 0;

        do {
            System.out.println("\n==== CAR RENTAL SYSTEM ====");
            System.out.println("1. View Available Vehicles");
            System.out.println("2. Booking");
            System.out.println("3. Return, Checkout and Billing");
            System.out.println("4. Customer Loyalty");
            System.out.println("5. Exit");

            try {
                choice + Integer.parseInt(input.nextLine());

                if (choice == 1) {
                    viewVehicles();
                } else if (choice == 2) {
                    booking();
                } else if (choice == 3) {
                    checkoutReturn();
                } else if (choice == 4) {
                    customerLoyalty();
                } else if (choice == 5) {
                    exit();
                } else {
                    System.out.println("Invalid option. Please choose 1 to 5.") ;

                }

            } while (choice != 5);

        }



    }







}
