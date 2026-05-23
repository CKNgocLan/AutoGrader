import java.time.LocalDate;
import java.util.UUID;

public class Discount {
    private UUID id;
    private double percent;
    private LocalDate startDate;
    private LocalDate endDate;

    public Discount(double percent, LocalDate endDate) {
        this.id = UUID.randomUUID();
        this.percent = percent;
        this.endDate = endDate;
        this.startDate = LocalDate.now();
    }

    public UUID getID() {
        return this.id;
    }

    public void setID(UUID id){
        this.id = id;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    
}
