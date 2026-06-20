public class BallpointPen extends Pen{
    public Pen pen;
    
    public Pen getPen() {
        return pen;
    }


    public void setPen(Pen pen) {
        this.pen = pen;
    }

    public BallpointPen(Brand brand,String model,Color color,double price){
        super(brand,model,color);
        this.pen.setPrice(price);
    };


    @Override
    public String getDescription() {
        return "Ballpoint Pen: <%s>-<%s>-<%s>-<%s>".formatted(pen.getBrand().getName(),pen.getModel(),pen.getColor(),pen.getPrice());
    }

}
