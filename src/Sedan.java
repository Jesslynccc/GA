public class Sedan extends Vehicle {
    private int luggageCapacity;
    private double fuelEfficiency;

    public Sedan(String vehicleID, String brand, String model, double dailyRental, String status, int luggageCapacity, double fuelEfficiency){
        super(vehicleID, brand, model, dailyRental, status);
        this.luggageCapacity = luggageCapacity;
        this.fuelEfficiency = fuelEfficiency;
    }

    public int getLuggageCapacity() {
        return luggageCapacity;
    }

    public void setLuggageCapacity(int luggageCapacity) {
        this.luggageCapacity = luggageCapacity;
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public void setFuelEfficiency(double fuelEfficiency) {
        this.fuelEfficiency = fuelEfficiency;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nLuggage Capacity: " + luggageCapacity
                + "\nFuel Efficiency: " + fuelEfficiency;
    }
}