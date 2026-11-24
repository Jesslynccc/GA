public class SUV extends Vehicle {
        private int seatingCapacity;
        private String entertainmentSystem;
        private String terrainMode;


        public SUV(String vehicleID, String brand, String model, double dailyRental, String status, String addOns, int seatingCapacity, String entertainmentSystem, String terrainMode) {
            super(vehicleID, brand, model, dailyRental, status, addOns);
            this.seatingCapacity = seatingCapacity;
            this.entertainmentSystem = entertainmentSystem;
            this.terrainMode = terrainMode;
        }

        public void setSeatingCapacity(int seatingCapacity) {
            this.seatingCapacity = seatingCapacity;
        }

        public int getSeatingCapacity() {
            return seatingCapacity;
        }

        public void setEntertainmentSystem(String entertainmentSystem) {
            this.entertainmentSystem = entertainmentSystem;
        }

        public String entertainmentSystem() {
            return entertainmentSystem;
        }

        public void setTerraiMode(String terraiMode) {
            this.terrainMode = terrainMode;
        }

        public String getTerrainMode() {
            return terrainMode;
        }

        public void displayDetails() {
            System.out.println("Vehicle Type: SUV");
            System.out.println("Vehicle ID " + getVehicleID());
            System.out.println("Brand: " + getBrand());
            System.out.println("Model : " + getModel());

        }


    }
