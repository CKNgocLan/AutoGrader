public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        PenFactory ballpointPenFactory = new BallpointPenFactory();
        PenFactory fountainPenFactory = new FountainPenFactory();

        Pen pen1 = ballpointPenFactory.createPen(sailor, "TUZU Forge", Color.GREY, 65);
        Pen pen2 = fountainPenFactory.createPen(concopens, "Golden Lotus", Color.RED, 645.27);

        Client Store = new Client();
        Store.AddPen(pen1);
        Store.AddPen(pen2);

        Store.printInventoryReceipt();
    }

}
