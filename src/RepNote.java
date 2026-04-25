public class RepNote {
    public static void main(String[] args) {
        System.out.println("=== Booting RepNote Engine (3-Day PPL)===\n");

        //create user profile
        Lifter myProfile = new Lifter("Kyaw Zall Thwin", 54.0);
        System.out.println("User Profile Loaded: " + myProfile.getName() + "\n");

        //create equipment
        Equipment smithMachine = new Equipment("Smith Machine", "Machine");
        Equipment barbell = new Equipment("Barbell", "Free Weight");
        Equipment cableRope = new Equipment("Rope Attachment", "Cable");
        Equipment legPressMachine = new Equipment("Leg Press Machine", "Machine");
        Equipment cableVBar = new Equipment("V-Bar Attachment", "Cable");
        Equipment treadmill = new Equipment("Treadmill", "Cardio Machine");
        Equipment tBar = new Equipment("Bar Machine", "Machine");

        // Day 1; Push Day (Chset, Shoulders, Triceps)
        WorkoutTracker pushDay = new WorkoutTracker("Push", "April 20, 2026");

        Weightlifting inclinePress = new Weightlifting("Incline Chest Press", smithMachine, 15.0, 3, 12);
        Weightlifting flatPress = new Weightlifting("Flat Chest Press", smithMachine, 16.0, 3, 12);
        Weightlifting tricepPushdown = new Weightlifting("Tricep Pushdown", cableRope, 23.0, 3, 12);

        pushDay.addExercise(inclinePress);
        pushDay.addExercise(flatPress);
        pushDay.addExercise(tricepPushdown);

        // Day 2: Pull day (Back, Biceps)
        WorkoutTracker pullDay = new WorkoutTracker("Pull", "April 20, 2026");

        Weightlifting latPulldown = new Weightlifting("Lat Pulldown", cableVBar, 32.0, 3, 12);
        Weightlifting tBarRow = new Weightlifting("T-Bar Row", tBar, 35.0, 3, 12);
        Weightlifting facePull = new Weightlifting("Face Pull", cableRope, 23.5, 3, 12);
    
        pullDay.addExercise(latPulldown);
        pullDay.addExercise(tBarRow);
        pullDay.addExercise(facePull);

        /// Day 3: Leg day
        WorkoutTracker legDay = new WorkoutTracker("Leg", "April 20, 2026");

        Weightlifting legPress = new Weightlifting("Seated Leg Press", legPressMachine, 66.0, 3, 12);
        Weightlifting rdls = new Weightlifting("RDLs", barbell, 17.0, 3, 12);
        Cardio upHillWalk = new Cardio("Up-Hill Walk", treadmill, 10, 1.0);
    
        legDay.addExercise(legPress);
        legDay.addExercise(rdls);
        legDay.addExercise(upHillWalk);

        // Save
        myProfile.getHistory().addSession(pushDay);
        myProfile.getHistory().addSession(pullDay);
        myProfile.getHistory().addSession(legDay);

        myProfile.getHistory().viewAllSessions();;
    }
}
