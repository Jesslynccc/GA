public abstract class  Vehicle {
    private String vehicleID;
    private String brand;
    private String model;
    private double dailyRental;
    private String status;

    public Vehicle(String vehicleID, String brand, String model, double dailyRental, String status) {
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

    public void setBrand(String Brand) {
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "Vehicle ID: " + vehicleID
                + "\nBrand: " + brand
                + "\nModel: " + model
                + "\nDaily Rental: " + dailyRental
                + "\nStatus: " + status;

    }



}