public class Weightlifting extends Exercise {
    // Encapsulation
    private double weight;
    private int sets;
    private int reps;

    public Weightlifting( String name, Equipment gear, double weight, int sets, int reps) {
        super(name, gear);
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
    }

    // Encapsulation: Safe update methods
    public void updateWeight(double newWeight) {
        if (newWeight >= 0) {
            this.weight = newWeight;
        } else {
            System.out.println("Error: Weight cannot be negative.");
        }
    }

    public void updateSets(int newSets) {
        if (newSets > 0) {
            this.sets = newSets;
        } 
    }

    public void updateReps(int newReps) {
        if (newReps > 0) {
            this.reps = newReps;
        }
    }

    @Override
    public void displayExercise() {
        System.out.println("- " + name + " (" + gear.getEquipmentName() + "): " 
                           + weight + "kg x " + reps + " reps x " + sets + " sets");
    }
}