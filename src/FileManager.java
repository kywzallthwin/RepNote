import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private static final String FILE_NAME = "workout_data.txt";

    /**
     * Save the lifter's workout history to a file
     */
    public static void saveWorkoutHistory(Lifter lifter) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            writer.println(lifter.getName());
            writer.println(lifter.getBodyWeight());
            writer.println(lifter.getHistory().getSessions().size());

            for (WorkoutTracker session : lifter.getHistory().getSessions()) {
                writer.println(session.getDayType());
                writer.println(session.getDate());
                writer.println(session.getExercises().size());

                for (Exercise ex : session.getExercises()) {
                    if (ex instanceof Weightlifting) {
                        Weightlifting w = (Weightlifting) ex;
                        writer.println("WEIGHTLIFTING");
                        writer.println(w.getName());
                        writer.println(w.getEquipment().getEquipmentName());
                        writer.println(w.getWeight());
                        writer.println(w.getSets());
                        writer.println(w.getReps());
                    } else if (ex instanceof Cardio) {
                        Cardio c = (Cardio) ex;
                        writer.println("CARDIO");
                        writer.println(c.getName());
                        writer.println(c.getEquipment().getEquipmentName());
                        writer.println(c.getDurationMinutes());
                        writer.println(c.getDistance());
                    }
                }
            }
            System.out.println("\nWorkout data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving workout data: " + e.getMessage());
        }
    }

    /**
     * Load the lifter's workout history from file
     */
    public static Lifter loadWorkoutHistory() {
        File file = new File(FILE_NAME);

        // If file doesn't exist, return a new default lifter
        if (!file.exists()) {
            System.out.println("No previous workout data found. Starting fresh!\n");
            return new Lifter("New Lifter", 75.0);
        }

        try (Scanner scanner = new Scanner(new FileReader(FILE_NAME))) {
            String name = scanner.nextLine();
            double bodyWeight = Double.parseDouble(scanner.nextLine());
            Lifter lifter = new Lifter(name, bodyWeight);

            int sessionCount = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < sessionCount; i++) {
                String dayType = scanner.nextLine();
                String date = scanner.nextLine();
                int exerciseCount = Integer.parseInt(scanner.nextLine());

                WorkoutTracker session = new WorkoutTracker(dayType, date);

                for (int j = 0; j < exerciseCount; j++) {
                    String exerciseType = scanner.nextLine();

                    if ("WEIGHTLIFTING".equals(exerciseType)) {
                        String exName = scanner.nextLine();
                        String equipmentName = scanner.nextLine();
                        double weight = Double.parseDouble(scanner.nextLine());
                        int sets = Integer.parseInt(scanner.nextLine());
                        int reps = Integer.parseInt(scanner.nextLine());

                        Equipment equipment = new Equipment(equipmentName, "");
                        Weightlifting exercise = new Weightlifting(exName, equipment, weight, sets, reps);
                        session.addExercise(exercise);

                    } else if ("CARDIO".equals(exerciseType)) {
                        String exName = scanner.nextLine();
                        String equipmentName = scanner.nextLine();
                        int duration = Integer.parseInt(scanner.nextLine());
                        double distance = Double.parseDouble(scanner.nextLine());

                        Equipment equipment = new Equipment(equipmentName, "");
                        Cardio exercise = new Cardio(exName, equipment, duration, distance);
                        session.addExercise(exercise);
                    }
                }

                lifter.getHistory().addSession(session);
            }

            System.out.println("--Workout data loaded successfully!--\n");
            return lifter;

        } catch (IOException e) {
            System.out.println("Error loading workout data: " + e.getMessage());
            return new Lifter("New Lifter", 75.0);
        }
    }
}



