public class Application {
    public static void main(String[] args) {
        PenFactory ballpointPenFactory = new BallpointPenFactory();
        PenFactory fountainPenFactory = new FountainPenFactory();
        Pen ballpointPen = ballpointPenFactory.createPen(new Brand("Sailor"), "TUZU Forge", Color.GREY, 65);
        Pen fountainPen = fountainPenFactory.createPen(new Brand("ConCoPens"), "Golden Lotus", Color.RED, 645.27);
        System.out.println("Ballpoint Pen: "+ballpointPen.getDescription());
        System.out.println("Fountain Pen: "+fountainPen.getDescription());

    }
}
