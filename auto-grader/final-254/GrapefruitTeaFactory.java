public class GrapefruitTeaFactory implements TeaFactory {
    @Override
    public Tea createTea() {
        return new GrapefruitTea();
    }
}