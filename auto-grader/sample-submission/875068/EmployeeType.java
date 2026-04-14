
public enum EmployeeType {
    FULL_TIME("FULL_TIME"),
    PART_TIME("PART_TIME");

    private final String value;// khai bao cac value tren thanh static -> allow full control

    EmployeeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}