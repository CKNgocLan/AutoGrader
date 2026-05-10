import java.time.LocalDate;

public class Application {
    public static void main(String[] args) throws Exception {
        Brand sailor = new Brand("Sailor", Country.JAPAN);
        Brand pilot = new Brand("Pilot", Country.JAPAN);
        Brand concopens = new Brand("ConCoPens", Country.VIETNAM);

        Pen ballpointPen = new BallpointPen("Shikiori", sailor, 51.61);
        Pen fountainPen = new FountainPen("Golden Lotus", concopens, 645.27);
        
        Discount discount = new Discount(0.19, LocalDate.now().plusMonths(1));
        Pen discountedPen = new BallpointPen("FriXion", pilot, 13.82, discount);

        System.out.println(ballpointPen);
        System.out.println("*".repeat(15));
        System.out.println(fountainPen);
        System.out.println("*".repeat(15));
        System.out.println(discountedPen);
    }
}
