public class RepNote {
    public static void main(String[] args) {
        System.out.println("\n========================================");
        System.out.println("  REPNOTE - Interactive Gym Tracker");
        System.out.println("      Loading your data...");
        System.out.println("========================================\n");

        // Load existing workout history from file, or create new lifter
        Lifter myProfile = FileManager.loadWorkoutHistory();

        System.out.println("Welcome, " + myProfile.getName() + "!");
        System.out.println("Body Weight: " + myProfile.getBodyWeight() + " kg");
        System.out.println("Total Sessions: " + myProfile.getHistory().getSessions().size());

        // Start the interactive menu
        InteractiveMenu menu = new InteractiveMenu(myProfile, new FileManager());
        menu.startMenu();
    }
}
