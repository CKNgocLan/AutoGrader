public class PremiumLotusTeaFactory implements TeaFactory {
    @Override
    public Tea createTea() {
        return new PremiumLotusTea();
    }
}