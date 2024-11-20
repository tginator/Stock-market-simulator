package Quotes;

public class StockQuote {

    private String symbol;
    private double price;
    private String timestamp;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String input) {
        this.symbol = input;
    }

    public void setPrice(double input) {
        this.price = input;
    }

    public void setTimestamp(String input) {
        this.timestamp = input;
    }

    public double getPrice() {
        return price;
    }
}
