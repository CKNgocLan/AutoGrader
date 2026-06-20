public class Application {
    public static void main(String[] args) {
        Brand sailor = new Brand("Sailor");
        Brand concopens = new Brand("ConCoPens");
        PenFactory ballpointPenFactory = new PenFactory(BallpointPenFactory);
        PenFactory foutaiPenFactory = new PenFactory(FountainPenFactory)
        Cilent ballpointPenCilent = new Client(ballpointPenFactory, sailor, "TUZU Forge" , "GREY", 65);
        Cilent fountainPenCilent = new Client(foutaiPenFactory, concopens, "Golden Lotus" , "RED", 645.27);
        pen.showAll


    }

}
