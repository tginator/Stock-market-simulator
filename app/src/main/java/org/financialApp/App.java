/*
 * This source file was generated by the Gradle 'init' task
 */
package org.financialApp;

import Portfolio.Investment;
import Portfolio.MyPortfolio;
import Quotes.StockQuote;

import java.io.IOException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Welcome to the Stock Market Simulator.");
        System.out.println("1. Create portfolio.");

        MyPortfolio portfolio = createPortfolio();


        FinancialAPI api = new FinancialAPI("1ZZRCRJPU87KLNZS");
        StockQuote quote = api.getRealTimeQuote("AAPL");
        System.out.println(quote.getPrice());

        Investment share = new Investment(5, "APPL");
        share.setValue(quote.getPrice());


    }

    private static MyPortfolio createPortfolio() {

        Scanner scanner = new Scanner(System.in);

        String user = "";
        double balance = 0;

        System.out.println("Enter portfolio name: ");
        user = scanner.nextLine();
        System.out.println("Set balance to: ");
        balance = scanner.nextDouble();

        MyPortfolio newPortfolio = new MyPortfolio(user, balance);

        System.out.println("Portfolio created!");

        return newPortfolio;
    }

}