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

    public double getBodyWeight() {
        return bodyWeight;
    }

    public WorkoutHistory getHistory() {
        return this.history;
    }
    
}
