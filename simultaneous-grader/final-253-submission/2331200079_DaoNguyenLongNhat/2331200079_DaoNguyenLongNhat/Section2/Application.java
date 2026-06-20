//package Section2;

import Section2.Pen.PenBuilder;

public class Application {
    public static void main(String[] args) {
        PenBuilder ballpointPen = new Pen.PenBuilder().setBrand("Platinum").setModel("BNB-5000").setPrice(57.93); 
        PenBuilder fountainPen = new Pen.PenBuilder().setBrand("Montblanc").setModel("LeGrand").setPrice(1145);

        //System.out.println(toString);
    }
}
