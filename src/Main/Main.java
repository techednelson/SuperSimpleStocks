package Main;

import Exception.WrongFormatInput;
import SERVICES.SimpleStockService;
import SERVICES.SimpleTradeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        SimpleStockService stockService = new SimpleStockService();
        stockService.openDatabase();
        boolean exit = false;
        while (!exit) {
            printMenu();
            int option;
            try {
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                throw new WrongFormatInput(" You have to enter an Integer. Please run the program again again.");
            }
            if (option != 0) {
                switch (option) {
                    case 1:
                        stockService.askForTickerPrice();
                        stockService.calculateDividendYield();
                        stockService.calculatePERatio();
                        break;
                    case 2:
                        stockService.askForTickerPrice();
                        SimpleTradeService tradeService = new SimpleTradeService();
                        tradeService.recordTrade();
                        tradeService.calculateStockPrice();
                        tradeService.calculateGBCE();
                        break;
                    case 3:
                        System.out.println("\nGoodbye! To play again, run the program");
                        stockService.closeDatabase();
                        exit = true;
                        break;
                    default:
                        System.out.println("\nOption entered is not available or doesn't exist.");
                        break;
                }
            }
        }
    }

    private static void printMenu() {
            System.out.println("\n Welcome to Super Simple Stock, please choose one option entering 1, 2 or 3." +
                    " \n");
            System.out.println("1 - Calculate Dividend Yield and P/E Ratio.");
            System.out.println("2 - Record a trade, calculate stock price and GBCE");
            System.out.println("3 - Exit \n");
    }

}
