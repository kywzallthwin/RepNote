public class Lifter {
    private String name;
    private double bodyWeight;    
    private WorkoutHistory history;

    public Lifter(String name, double bodyWeight) {
        this.name = name;
        this.bodyWeight = bodyWeight;
        this.history = new WorkoutHistory();
    }

    public String getName() {
        return name;
    }

    public boolean updateName(String newName) {
        if (newName == null) {
            return false;
        }

        String cleanedName = newName.trim();
        if (cleanedName.isEmpty()) {
            return false;
        }

        this.name = cleanedName;
        return true;
    }

    public double getBodyWeight() {
        return bodyWeight;
    }

    public WorkoutHistory getHistory() {
        return this.history;
    }
    
}
