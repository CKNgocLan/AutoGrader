

public class Application {
    public static void main(String[] args) {
        Brand brand1 = new Brand("sailor");
        Brand brand2 = new Brand("concopens");

        PenFactory ballpointPenFactory = new BallpointPenFactory();
        PenFactory fountainPenFactory = new FountainPenFactory();

        Client client1 = new Client(ballpointPenFactory.createPen(brand1, "Jotter", Color.RED, 10.0)) {
            @Override
            public Pen getPen() {
                return this.pen;
            }
        };

        Client client2 = new Client(fountainPenFactory.createPen(brand2, "Century II", Color.GREY, 50.0)) {
            @Override
            public Pen getPen() {
                return this.pen;
            }
        };

        System.out.println("Client 1 has a " + client1.getPen().getBrand().getName() + " " + client1.getPen().getModel() + " in " + client1.getPen().getColor() + ": $" + client1.getPen().getPrice());
        System.out.println("Client 2 has a " + client2.getPen().getBrand().getName() + " " + client2.getPen().getModel() + " in " + client2.getPen().getColor() + ": $" + client2.getPen().getPrice());
    }
}
