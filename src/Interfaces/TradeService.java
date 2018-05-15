package Interfaces;

import Model.Stock;
import Model.Trade;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.ArrayList;

public class TradeService implements SimpleStockServices {

    @Override
    public void calculateDividendYield(ArrayList<Stock> stocks) { }

    @Override
    public void calculatePERatio(ArrayList<Stock> stocks) { }

    @Override
    public void recordTrade(ArrayList<Stock> stocks) {
        Trade trade;
        String indicator;
        System.out.println("\nStock Symbol\t Timestamp\t\t Shares\t\t Buy/Sell\t\t Price");
        for(Stock stock : stocks) {
            if(stock != null) {
                trade = new Trade();
                trade.setSharesQuantity((int)(Math.random() * 1000 + 1));
                trade.setPrice(Math.random() * 20 + 1);
                indicator = (int)(Math.random() * 2) == 0 ? Trade.indicator.buy.toString() : Trade.indicator.sell.toString();
                System.out.println("\t" + stock.getStockSymbol() + "\t\t\t" + LocalTime.now() + "\t   " + trade.getSharesQuantity() +
                        "\t\t\t" + indicator + "\t\t\t" + new DecimalFormat("#.##").format(trade.getPrice()));
                stock.setTrades(trade);
                for(int i = 1; i < 15; i++) {
                    trade = new Trade();
                    trade.setSharesQuantity((int)(Math.random() * 1000 + 1));
                    trade.setPrice(Math.random() * 20 + 1);
                    stock.setTrades(trade);
                }
            }
        }
        calculateStockPrice(stocks);
    }

    @Override
    public void calculateStockPrice(ArrayList<Stock> stocks) {
        double sumTradePrices = 0;
        double sumQuantity = 0;
        double stockPrice;
        System.out.println("\nStock Symbol\t Stock Price");
        for(Stock stock : stocks) {
            for(Trade trade : stock.getTrades()) {
                sumTradePrices += trade.getPrice();
                sumQuantity += trade.getSharesQuantity();;
            }
            stockPrice = (sumTradePrices * sumQuantity) / sumQuantity;
            System.out.println("\t" + stock.getStockSymbol() + "\t\t\t   " +
                    new DecimalFormat("#.##").format(stockPrice));
        }
        calculateGBCE(stocks);
    }

    @Override
    public void calculateGBCE(ArrayList<Stock> stocks) {
        double productTradePrices = 1;
        double geoMean;
        int count = 0;
        System.out.println("\nStock Symbol\t" + "Geometric Mean");
        for(Stock stock : stocks) {
            for(Trade trade : stock.getTrades()) {
                productTradePrices *= trade.getPrice();
                count++;
            }
            geoMean = Math.pow(productTradePrices, 1.0/ count);
            System.out.println("\t" + stock.getStockSymbol() + "\t\t\t   " + new DecimalFormat("#.##").format(geoMean));
        }
    }

}