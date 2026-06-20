

public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
    Brand concopens = new Brand("ConCoPens");

    PenFactory ballpointPenFactory = new BallPointPenFactory();
    PenFactory fountainPenFactory = new FountainPenFactory();

    Pen ballpointPenClient = ballpointPenFactory.createPen(sailor, "TUZU forge", Color.GREY , 65);
    Pen fountainPenClient = fountainPenFactory.createPen(concopens, "Golden Lotus", Color.RED, 645.27);

    System.out.println(ballpointPenClient.getDescription());
    }
    

    
}
