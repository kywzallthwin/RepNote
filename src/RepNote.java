public class RepNote {
    public static void main(String[] args) {
        Equipment smithMachine = new Equipment("Smith Machine", "Machine");
        Equipment rope = new Equipment("Rope Attachment", "Cable");

        Weightlifting chestPress = new Weightlifting(16.0, 2, 12, "Chest Press Flat", smithMachine);
        Weightlifting lateralraise = new Weightlifting(6.5, 1, 8, "Lateral Raise", rope);

        System.out.println("\n--- Updating Chset Press ---");
        chestPress.updateWeight(18.0);
        chestPress.updateSets(3);
        chestPress.displayExercise();
    }
}
