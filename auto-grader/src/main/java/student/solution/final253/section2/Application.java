package student.solution.final253.section2;

public class Application {
    public static void main(String[] args) throws Exception {
        System.out.println(
            new Pen.PenBuilder()
                .setBrand("Platinum")
                .setModel("BNB-5000")
                .setPrice(57.93)
                .build());
        System.out.println(
            new Pen.PenBuilder()
                .setBrand("Montblanc")
                .setModel("LeGrand")
                .setPrice(1145)
                .build());
    }
}
