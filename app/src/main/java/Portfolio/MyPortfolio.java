package Portfolio;

import org.financialApp.FinancialAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyPortfolio {

    private double value = 0;
    // total value of shares + balance
    private double balance;
    //spending power
    private String user;

    protected FinancialAPI api = new FinancialAPI("1ZZRCRJPU87KLNZS");

    private List<Investment> investments;

    public MyPortfolio(String user, double balance) {
        this.user = user;
        this.balance = balance;
        investments = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double transaction) {
        balance += transaction;
    }

    public void getValue() throws IOException, InterruptedException {

        Double currentPrice;

        for (Investment investment : investments) {
            currentPrice = api.getRealTimeQuote(investment.getSymbol()).getPrice();
            value += investment.getValue(currentPrice);
        }
    }
}
