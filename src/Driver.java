public class Driver {
    private String driverID;
    private int completedRentals;

    public Driver(String driverID, int completedRentals) {
        this.driverID = driverID;
        this.completedRentals = completedRentals;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public int getCompletedRentals() {
        return completedRentals;
    }

    public void setCompletedRentals(int completedRentals) {
        this.completedRentals = completedRentals;
    }

    public String getMembership() {
        if (completedRentals == 0) {
            return "New";
        }
        else if (completedRentals <= 4) {
            return "Regular";
        }
        else {
            return "VIP";
        }
    }

    @Override
    public String toString() {
        return "Driver ID: " + driverID
                + "\nCompleted Rentals: " + completedRentals
                + "\nMembership: " + getMembership();
    }
}