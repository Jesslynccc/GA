public class SUV extends Vehicle {
        private int seatingCapacity;
        private String entertainmentSystem;
        private String terrainMode;

        public SUV() {

        }

        public SUV(String vehicleID, String brand, String model, double dailyRental, String status, String addOns, int seatingCapacity, String entertainmentSystem, String terrainMode) {
            super
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


    }
