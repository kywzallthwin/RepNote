public abstract class  Exercise {
    protected String name;
    protected Equipment gear;

    public Exercise(String name, Equipment gear) {
        this.name = name;
        this.gear = gear;
    }

    public abstract void displayExercise();

    public String getName() {
        return name;
    }

    public Equipment getEquipment() {
        return gear;
    }
}