package Portfolio;

import org.financialApp.FinancialAPI;

public class Investment {

    private int quantity;
    private double value;
    private String symbol;

    public Investment(int quantity, String symbol) {
        this.quantity = quantity;
        this.symbol = symbol;
    }

    public void setValue(Double price) {

        value = quantity * price;
    }

    public double getValue(Double price) {
        return quantity * price;
    }

    public String getSymbol() {
        return symbol;
    }

}
