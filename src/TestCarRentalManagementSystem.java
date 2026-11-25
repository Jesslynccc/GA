import java.util.Scanner;

public class TestCarRentalManagementSystem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] customerID = {"C001", "C002", "C003"};
        int[] completedRentals = {0, 5, 6} ;

        String[] bookedCustomerID =;

        Sedan vios = new Sedan("S001", "Toyota", "Vios", 120.0, true, "GPS", 2, 18.5);
        Sedan city = new Sedan("S002", "Honda", "City", 150.0, true, "Child Seat", 3, 17.8);
        SUV fortuner = new SUV("U001", "Honda", "CRV", 220.0, true, Dashcam, 5, );
        SUV crv = new SUV();

        Vehicle[] vehicles = {vios, city, fortuner, crv};




    }

    static Vehicle[] vehicles = new Vehicle[4];
    static String[] customersID = new String[10];
    static String[] customersNames = new String[10];
    static int[] completedRentals = new int[10];
    static int customeraCount = 0;







}
