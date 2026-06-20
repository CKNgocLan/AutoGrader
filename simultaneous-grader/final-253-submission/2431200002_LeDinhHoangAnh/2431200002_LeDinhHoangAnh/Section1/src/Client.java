public class Client {
    Pen pen;

    public Client(Pen pen) {
        PenFactory penfac= null;
       if(pen.getBrand().getName().equalsIgnoreCase(("ballpointPenFactory"))){
        penfac = new BallpointPenFactory();
       }
       else if (pen.getBrand().getName().equalsIgnoreCase("fountainPenFactory")) {
        penfac = new FountainPenFactory();
       }
    }
    public Pen getPen(){
        return pen;
    }
}
