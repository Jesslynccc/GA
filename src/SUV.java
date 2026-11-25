public class SUV extends Vehicle {
        private int seatingCapacity;
        private String entertainmentSystem;
        private String terrainMode;


        public SUV(String vehicleID, String brand, String model, double dailyRental, String status, int seatingCapacity, String entertainmentSystem, String terrainMode) {
            super(vehicleID, brand, model, dailyRental, status);
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

        public String getEntertainmentSystem() {
            return entertainmentSystem;
        }

        public void setTerrainMode(String terrainMode) {
            this.terrainMode = terrainMode;
        }

        public String getTerrainMode() {
            return terrainMode;
        }

        @Override
        public String toString() {
            return super.toString()
                    + "\nSeating Capacity: " + seatingCapacity
                    + "\nEntertainment System: " + entertainmentSystem
                    + "\nTerrain Mode: " + terrainMode;
        }
}
