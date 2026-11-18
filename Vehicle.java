public class Vehicle {
    public String vehicleID;
    public String brand;
    public String model;
    public double dailyRental;
    public boolean status;

    public Vehicle(String vehicleID, String brand, String model, double dailyRental, boolean status) {
        this.vehicleID = vehicleID;
        this.brand = brand;
        this.model = model;
        this.dailyRental = dailyRental;
        this.status = status;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setModel(String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }

    public void setDailyRental(double dailyRental) {
        this.dailyRental = dailyRental;
    }

    public double getDailyRental() {
        return dailyRental;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }



}
