import java.util.ArrayList;
import java.util.List;

public class Client {
    private List<Pen> pens;

    public Client() {
        this.pens = new ArrayList<>();
    }
    public void AddPen(Pen pen){
        pens.add(pen);
    }
    public void printInventoryReceipt(){
        for (Pen p:pens){
            System.out.println(p.toString());
        }
    }
   
}
