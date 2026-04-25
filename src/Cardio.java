public class Cardio extends Exercise {
    private int durationMinutes;
    private double distance;

    public Cardio(String name, Equipment gear, int durationMinutes, double distance) {
        super(name, gear);
        this.durationMinutes = durationMinutes;
        this.distance = distance;
    }

    // Encapsulation
    public void updateDuration(int mins) {
        if (mins > 0) {
            this.durationMinutes = mins;
        }
    }

    public void updateDistance(double dist) {
        if (dist > 0) {
            this.distance = dist;
        }
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public void displayExercise() {
        System.out.println("- " + name + " (" + gear.getEquipmentName() + "): " 
                           + durationMinutes + " mins, " + distance + " km");
    }
}