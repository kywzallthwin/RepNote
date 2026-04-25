import java.util.ArrayList;

public class WorkoutHistory {
    private ArrayList<WorkoutTracker> allSessions;

    public WorkoutHistory() {
        this.allSessions = new ArrayList<>();
    }

    public void addSession(WorkoutTracker session) {
        this.allSessions.add(session);
    }

    public ArrayList<WorkoutTracker> getSessions() {
        return allSessions;
    }

    public void viewAllSessions() {
        System.out.println("\n===== MASTER WORKOUT HISTORY =====");
        if (allSessions.isEmpty()) {
            System.out.println("No sessions recorded yet.");
        } else {
            // Loop through every saved day and tell it to print itself
            for (WorkoutTracker session : allSessions) {
                session.displayRoutine(); 
            }
        }
        System.out.println("==================================");
    }
}
