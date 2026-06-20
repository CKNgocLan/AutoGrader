

public class Client extends Pen {
    private Pen pen;

    

    public Client(Brand brand, String modle, Color color, double price) {
        super(brand, modle, color, price);
    }



    public Pen getPen() {
        return pen;
    }
    
    
    
}
