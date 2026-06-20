

public class FountainPen extends Pen{

    public FountainPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);
        //TODO Auto-generated constructor stub
    }
    public String getDescripton(){
        return "Fountain Pen: "+getPrice() +"-"+getModel()+"-"+getColor()+"-"+ getPrice();
    }
   
    
}
