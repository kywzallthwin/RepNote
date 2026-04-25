
import java.util.ArrayList;

public class WorkoutTracker {
    private String dayType;
    private String date;
    private ArrayList<Exercise> exerciseList;

    public WorkoutTracker(String dayType, String date) {
        this.dayType = dayType;
        this.date = date;
        this.exerciseList = new ArrayList<>();
    }

    public void addExercise(Exercise ex) {
        this.exerciseList.add(ex);
    }

    public void displayRoutine() {
        System.out.println("\n=== RepNote Tracker: " + dayType + " Day (" + date + ") ===");

        if (exerciseList.isEmpty()) {
            System.out.println("No exercises logged yet.");
        } else {
            for (Exercise ex : exerciseList) {
                ex.displayExercise(); 
            }
        }
        System.out.println("==============================================");
    }
}
