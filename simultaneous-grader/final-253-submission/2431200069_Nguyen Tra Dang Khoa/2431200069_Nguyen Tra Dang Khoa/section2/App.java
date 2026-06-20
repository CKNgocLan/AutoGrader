public class App { 
    public static void main(String[] args) throws Exception {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");

        PenFactory ballpointpenFactory = new BallpointPenFactory();
        Pen ballpointpenClient = ballpointpenFactory.createPen(sailor, "TUZU Forge" , Color.GREY, 65);
        
        PenFactory fountainpenFactory = new FountainPenFactory();
        Pen fountainpenClient = fountainpenFactory.createPen(concopens, "Golden Lotus", Color.RED, 645.27);

        System.out.println(ballpointpenClient.getDescription());
        System.out.println(fountainpenClient.getDescription());
    }
}
