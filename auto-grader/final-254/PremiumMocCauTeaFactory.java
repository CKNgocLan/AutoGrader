public class PremiumMocCauTeaFactory implements TeaFactory {
    @Override
    public Tea createTea() {
        return new PremiumMocCauTea();
    }
}