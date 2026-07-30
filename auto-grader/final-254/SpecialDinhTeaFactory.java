public class SpecialDinhTeaFactory implements TeaFactory {
    @Override
    public Tea createTea() {
        return new SpecialDinhTea();
    }
}
