public class Cardio extends Exercise {
    private int durationMinutes;
    private double distance;

    public Cardio(int durationMinutes, double distance, String name, Equipment gear) {
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

    @Override
    public void displayExercise() {
        System.out.println("- " + name + " (" + gear.getEquipmentName() + "): " 
                           + durationMinutes + " mins, " + distance + " km");
    }
}