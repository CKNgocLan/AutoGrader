

public class BallpointPen extends Pen {

    public BallpointPen(Brand brand, String model, Color color, double price) {
        super(brand, model, color, price);
        //TODO Auto-generated constructor stub
     
    }
     public String getDescripton(){
        return "Ballpoint Pen: "+getPrice() +"-"+getModel()+"-"+getColor()+"-"+ getPrice();
    }
   
    
}
