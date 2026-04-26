public class Demo {
    public static void main(String[] args) {
        Temperature temp = new Temperature(50);
        System.out.println(temp.getFahrenheit());
        System.out.println(temp.getCelsius());
        System.out.println(temp.getKelvin());

        temp.setFahrenheit(122);
        System.out.println(temp.getFahrenheit());
        System.out.println(temp.getCelsius());
        System.out.println(temp.getKelvin());
    }
}
