public class Application {
    public static void main(String[] args) throws Exception {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        PenFactory ballpointPenFactory = new BallpointPenFactory();
        PenFactory fountainPenFactory = new FountainPenFactory();

        Pen ballpointPen = ballpointPenFactory.createPen(sailor, "TUZU Forge", Color.GREY, 65);
        Pen fountaiPen = fountainPenFactory.createPen(concopens, "Golden Lotus", Color.RED, 645.27);

        System.out.println(ballpointPen.getDescription());
        System.out.println(fountaiPen.getDescription());
    }
}
