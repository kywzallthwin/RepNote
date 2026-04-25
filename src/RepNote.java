public class RepNote {
    public static void main(String[] args) {
        // Equipment smithMachine = new Equipment("Smith Machine", "Machine");
        // Equipment rope = new Equipment("Rope Attachment", "Cable");

        // Weightlifting chestPress = new Weightlifting(16.0, 2, 12, "Chest Press Flat", smithMachine);
        // Weightlifting lateralraise = new Weightlifting(6.5, 1, 8, "Lateral Raise", rope);

        // System.out.println("\n--- Updating Chset Press ---");
        // chestPress.updateWeight(18.0);
        // chestPress.updateSets(3);
        // chestPress.displayExercise();

        System.out.println("\nInitializing RepSync Test Environment...\n");

        Equipment smithMachine = new Equipment("Smith Machine", "Machine");
        Equipment rope = new Equipment("Rope Attachment", "Cable");
        Equipment treadmill = new Equipment("Treadmill", "Cardio Machine");

        Weightlifting chestPress = new Weightlifting(16.0, 2, 12, "Chest Press Flat", smithMachine);
        Weightlifting lateralraise = new Weightlifting(6.5, 1, 8, "Lateral Raise", rope);
        Cardio postWorkoutRun = new Cardio(15, 2.5, "Cool-down jog", treadmill);

        System.out.println("\n--- Updating Chset Press ---");
        chestPress.updateWeight(18.0);
        chestPress.updateSets(3);
        chestPress.displayExercise();

        WorkoutTracker pushDay = new WorkoutTracker("Push", "April 25, 2026");

        pushDay.addExercise(chestPress);
        pushDay.addExercise(lateralraise);
        pushDay.addExercise(postWorkoutRun);

        pushDay.displayRoutine();
    }
}
