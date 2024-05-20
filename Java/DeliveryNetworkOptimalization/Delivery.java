import java.util.ArrayList;

public class Delivery {
    private int costPerKm;
    private double hoursWorked;
    private int labourCost;
    private ArrayList<Order> orders;

    public Delivery(int costPerKm, double hoursWorked, int labourCost) {
        this.costPerKm = costPerKm;
        this.hoursWorked = hoursWorked;
        this.labourCost = labourCost;
    }

    public double hoursWorked() {
        return hoursWorked;
    }

    public double getTotalCost() {
        return costPerKm * hoursWorked + labourCost;
    }
}
