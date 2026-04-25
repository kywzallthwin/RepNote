public class Equipment {
    private String equipmentName;
    private String category;

    public Equipment(String equipmentName, String category) {
        this.equipmentName = equipmentName;
        this.category = category;
    }

    String getEquipmentName() {
        return this.equipmentName;
    }
}
