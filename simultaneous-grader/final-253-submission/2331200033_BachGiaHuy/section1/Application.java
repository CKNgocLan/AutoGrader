public class Application {
    public static void main(String[] args) {
        Brand sailor=new Brand("Sailor");
        Brand concopens=new Brand("ConCoPens");
        PenFactory ballpointPenFactory=new BallpointPenFactory();
        PenFactory fountainPenFactory=new FountainPenFactory();

        Pen ballpointPenClient=ballpointPenFactory.createPen(sailor,"TUZU Forge" , Color.GREY, 75);
        Pen fountainPenClient=fountainPenFactory.createPen(concopens, "Golden Lotus", Color.RED, 645.27);
    }
}
