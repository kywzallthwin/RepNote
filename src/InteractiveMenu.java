import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InteractiveMenu {
    private Scanner scanner;
    private Lifter lifter;
    private FileManager fileManager;

    public InteractiveMenu(Lifter lifter, FileManager fileManager) {
        this.lifter = lifter;
        this.fileManager = fileManager;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Start the main interactive menu loop
     */
    public void startMenu() {
        boolean running = true;

        while (running) {
            displayMainMenu();
            System.out.print("\nEnter your choice (1-4): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        viewHistoryOption();
                        break;
                    case 2:
                        editSessionOption();
                        break;
                    case 3:
                        createNewSessionOption();
                        break;
                    case 4:
                        System.out.println("\n✓ Saving and exiting RepNote... Goodbye!");
                        FileManager.saveWorkoutHistory(lifter);
                        running = false;
                        break;
                    default:
                        System.out.println("\n✗ Invalid choice. Please enter 1-4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n✗ Invalid input. Please enter a number.");
            }
        }

        scanner.close();
    }

    /**
     * Display the main menu
     */
    private void displayMainMenu() {
        System.out.println("\n========================================");
        System.out.println("    REPNOTE - Workout Tracker Menu");
        System.out.println("========================================");
        System.out.println("User: " + lifter.getName());
        System.out.println("========================================");
        System.out.println("1. View Workout History");
        System.out.println("2. Edit Existing Workout Session");
        System.out.println("3. Create New Workout Session");
        System.out.println("4. Exit & Save");
        System.out.println("========================================");
    }

    /**
     * Option 1: View all workout history
     */
    private void viewHistoryOption() {
        ArrayList<WorkoutTracker> sessions = lifter.getHistory().getSessions();

        if (sessions.isEmpty()) {
            System.out.println("\n✗ No workout sessions recorded yet.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("   WORKOUT HISTORY - ALL SESSIONS");
        System.out.println("========================================\n");

        for (int i = 0; i < sessions.size(); i++) {
            WorkoutTracker session = sessions.get(i);
            System.out.println((i + 1) + ". " + session.getDayType() + " Day - " + session.getDate() 
                             + " (" + session.getExercises().size() + " exercises)");
        }

        System.out.println("\nPress Enter to return to main menu...");
        scanner.nextLine();
    }

    /**
     * Option 2: Edit an existing workout session
     */
    private void editSessionOption() {
        ArrayList<WorkoutTracker> sessions = lifter.getHistory().getSessions();

        if (sessions.isEmpty()) {
            System.out.println("\n✗ No workout sessions to edit.");
            return;
        }

        System.out.println("\n========================================");
        System.out.println("   SELECT SESSION TO EDIT");
        System.out.println("========================================\n");

        for (int i = 0; i < sessions.size(); i++) {
            WorkoutTracker session = sessions.get(i);
            System.out.println((i + 1) + ". " + session.getDayType() + " Day - " + session.getDate());
        }

        System.out.print("\nSelect session number (or 0 to cancel): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                return;
            }

            if (choice > 0 && choice <= sessions.size()) {
                editSessionMenu(sessions.get(choice - 1));
            } else {
                System.out.println("\n✗ Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n✗ Invalid input.");
        }
    }

    /**
     * Edit a specific workout session (add/remove/edit exercises)
     */
    private void editSessionMenu(WorkoutTracker session) {
        boolean editing = true;

        while (editing) {
            ArrayList<Exercise> exercises = session.getExercises();

            System.out.println("\n========================================");
            System.out.println("EDITING: " + session.getDayType() + " Day");
            System.out.println("========================================\n");

            if (exercises.isEmpty()) {
                System.out.println("No exercises in this session.\n");
            } else {
                System.out.println("Exercises in this session:");
                for (int i = 0; i < exercises.size(); i++) {
                    Exercise ex = exercises.get(i);
                    System.out.print((i + 1) + ". ");
                    ex.displayExercise();
                }
                System.out.println();
            }

            System.out.println("Options:");
            System.out.println("1. Edit an exercise");
            System.out.println("2. Add new exercise");
            System.out.println("3. Remove an exercise");
            System.out.println("0. Done editing\n");

            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0) {
                    editing = false;
                } else if (choice == 1) {
                    editExerciseSelection(session);
                } else if (choice == 2) {
                    addExerciseToSession(session);
                } else if (choice == 3) {
                    removeExerciseFromSession(session);
                } else {
                    System.out.println("\n✗ Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n✗ Invalid input.");
            }
        }

        FileManager.saveWorkoutHistory(lifter);
    }

    /**
     * Select which exercise to edit
     */
    private void editExerciseSelection(WorkoutTracker session) {
        ArrayList<Exercise> exercises = session.getExercises();

        if (exercises.isEmpty()) {
            System.out.println("\n✗ No exercises to edit.");
            return;
        }

        System.out.println("\n--- SELECT EXERCISE TO EDIT ---\n");

        for (int i = 0; i < exercises.size(); i++) {
            System.out.print((i + 1) + ". ");
            exercises.get(i).displayExercise();
        }

        System.out.print("\nSelect exercise number (or 0 to cancel): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                return;
            }

            if (choice > 0 && choice <= exercises.size()) {
                editExerciseMenu(exercises.get(choice - 1));
            } else {
                System.out.println("\n✗ Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n✗ Invalid input.");
        }
    }

    /**
     * Edit the properties of a specific exercise
     */
    private void editExerciseMenu(Exercise exercise) {
        if (exercise instanceof Weightlifting) {
            editWeightliftingExercise((Weightlifting) exercise);
        } else if (exercise instanceof Cardio) {
            editCardioExercise((Cardio) exercise);
        }
    }

    /**
     * Edit weightlifting exercise (weight, sets, reps)
     */
    private void editWeightliftingExercise(Weightlifting exercise) {
        boolean editing = true;

        while (editing) {
            System.out.println("\n--- EDITING WEIGHTLIFTING EXERCISE ---");
            System.out.print("\nCurrent: ");
            exercise.displayExercise();

            System.out.println("\nOptions:");
            System.out.println("1. Edit weight (kg) - Current: " + exercise.getWeight());
            System.out.println("2. Edit sets - Current: " + exercise.getSets());
            System.out.println("3. Edit reps - Current: " + exercise.getReps());
            System.out.println("0. Done editing\n");

            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0) {
                    editing = false;
                } else if (choice == 1) {
                    System.out.print("Enter new weight (kg): ");
                    double newWeight = Double.parseDouble(scanner.nextLine());
                    exercise.updateWeight(newWeight);
                    System.out.println("✓ Weight updated to " + newWeight + " kg");
                } else if (choice == 2) {
                    System.out.print("Enter new sets: ");
                    int newSets = Integer.parseInt(scanner.nextLine());
                    exercise.updateSets(newSets);
                    System.out.println("✓ Sets updated to " + newSets);
                } else if (choice == 3) {
                    System.out.print("Enter new reps: ");
                    int newReps = Integer.parseInt(scanner.nextLine());
                    exercise.updateReps(newReps);
                    System.out.println("✓ Reps updated to " + newReps);
                } else {
                    System.out.println("✗ Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Edit cardio exercise (duration, distance)
     */
    private void editCardioExercise(Cardio exercise) {
        boolean editing = true;

        while (editing) {
            System.out.println("\n--- EDITING CARDIO EXERCISE ---");
            System.out.print("\nCurrent: ");
            exercise.displayExercise();

            System.out.println("\nOptions:");
            System.out.println("1. Edit duration (minutes) - Current: " + exercise.getDurationMinutes());
            System.out.println("2. Edit distance (km) - Current: " + exercise.getDistance());
            System.out.println("0. Done editing\n");

            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0) {
                    editing = false;
                } else if (choice == 1) {
                    System.out.print("Enter new duration (minutes): ");
                    int newDuration = Integer.parseInt(scanner.nextLine());
                    exercise.updateDuration(newDuration);
                    System.out.println("✓ Duration updated to " + newDuration + " minutes");
                } else if (choice == 2) {
                    System.out.print("Enter new distance (km): ");
                    double newDistance = Double.parseDouble(scanner.nextLine());
                    exercise.updateDistance(newDistance);
                    System.out.println("✓ Distance updated to " + newDistance + " km");
                } else {
                    System.out.println("✗ Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input. Please enter a valid number.");
            }
        }
    }

    /**
     * Add a new exercise to the session
     */
    private void addExerciseToSession(WorkoutTracker session) {
        System.out.println("\n--- ADD NEW EXERCISE ---");
        System.out.println("1. Weightlifting");
        System.out.println("2. Cardio\n");

        System.out.print("Select exercise type (1 or 2): ");

        try {
            int type = Integer.parseInt(scanner.nextLine());

            if (type == 1) {
                addWeightliftingExercise(session);
            } else if (type == 2) {
                addCardioExercise(session);
            } else {
                System.out.println("\n✗ Invalid choice.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n✗ Invalid input.");
        }
    }

    /**
     * Add a weightlifting exercise
     */
    private void addWeightliftingExercise(WorkoutTracker session) {
        System.out.print("\nExercise name: ");
        String name = scanner.nextLine();

        System.out.print("Equipment name: ");
        String equipmentName = scanner.nextLine();

        System.out.print("Weight (kg): ");
        double weight = Double.parseDouble(scanner.nextLine());

        System.out.print("Sets: ");
        int sets = Integer.parseInt(scanner.nextLine());

        System.out.print("Reps: ");
        int reps = Integer.parseInt(scanner.nextLine());

        Equipment equipment = new Equipment(equipmentName, "");
        Weightlifting exercise = new Weightlifting(name, equipment, weight, sets, reps);
        session.addExercise(exercise);

        System.out.println("\n✓ Exercise added successfully!");
    }

    /**
     * Add a cardio exercise
     */
    private void addCardioExercise(WorkoutTracker session) {
        System.out.print("\nExercise name: ");
        String name = scanner.nextLine();

        System.out.print("Equipment name: ");
        String equipmentName = scanner.nextLine();

        System.out.print("Duration (minutes): ");
        int duration = Integer.parseInt(scanner.nextLine());

        System.out.print("Distance (km): ");
        double distance = Double.parseDouble(scanner.nextLine());

        Equipment equipment = new Equipment(equipmentName, "");
        Cardio exercise = new Cardio(name, equipment, duration, distance);
        session.addExercise(exercise);

        System.out.println("\n✓ Exercise added successfully!");
    }

    /**
     * Remove an exercise from the session
     */
    private void removeExerciseFromSession(WorkoutTracker session) {
        ArrayList<Exercise> exercises = session.getExercises();

        if (exercises.isEmpty()) {
            System.out.println("\n✗ No exercises to remove.");
            return;
        }

        System.out.println("\n--- SELECT EXERCISE TO REMOVE ---\n");

        for (int i = 0; i < exercises.size(); i++) {
            System.out.print((i + 1) + ". ");
            exercises.get(i).displayExercise();
        }

        System.out.print("\nSelect exercise number to remove (or 0 to cancel): ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                return;
            }

            if (choice > 0 && choice <= exercises.size()) {
                Exercise removed = exercises.remove(choice - 1);
                System.out.println("\n✓ Exercise removed: " + removed.getName());
            } else {
                System.out.println("\n✗ Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("\n✗ Invalid input.");
        }
    }

    /**
     * Option 3: Create a new workout session
     */
    private void createNewSessionOption() {
        System.out.println("\n--- CREATE NEW WORKOUT SESSION ---\n");

        System.out.print("Enter day type (e.g., Push, Pull, Leg): ");
        String dayType = scanner.nextLine();

        System.out.print("Enter date (e.g., April 25, 2026): ");
        String date = scanner.nextLine();

        WorkoutTracker newSession = new WorkoutTracker(dayType, date);

        boolean addingExercises = true;

        while (addingExercises) {
            System.out.println("\nCurrent exercises in session: " + newSession.getExercises().size());
            System.out.println("1. Add Weightlifting exercise");
            System.out.println("2. Add Cardio exercise");
            System.out.println("0. Done adding exercises\n");

            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice == 0) {
                    addingExercises = false;
                } else if (choice == 1) {
                    addWeightliftingExercise(newSession);
                } else if (choice == 2) {
                    addCardioExercise(newSession);
                } else {
                    System.out.println("\n✗ Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n✗ Invalid input.");
            }
        }

        if (newSession.getExercises().size() > 0) {
            lifter.getHistory().addSession(newSession);
            System.out.println("\n✓ New workout session created with " + newSession.getExercises().size() + " exercises!");
            FileManager.saveWorkoutHistory(lifter);
        } else {
            System.out.println("\n✗ Session not created (no exercises added).");
        }
    }
}
