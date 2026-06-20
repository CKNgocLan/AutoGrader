public class Application {
    public static void main(String[] args) throws Exception {
        PenFactory bpf = new BallpointPenFactory();
        PenFactory fpf = new FountainPenFactory();


        Client luc = new Client(bpf);
        Pen lucPen = luc.getPen();

        System.out.println(lucPen);

        Client duy = new Client(fpf);
        Pen duyPen = duy.getPen();

        System.out.print(duyPen);

    }
}
