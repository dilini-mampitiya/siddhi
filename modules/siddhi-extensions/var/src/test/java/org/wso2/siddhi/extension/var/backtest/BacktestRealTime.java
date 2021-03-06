package org.wso2.siddhi.extension.var.backtest;

import org.apache.commons.math3.distribution.BinomialDistribution;
import org.json.JSONObject;
import org.wso2.siddhi.extension.var.models.VaRCalculator;
import org.wso2.siddhi.extension.var.models.parametric.ParametricVaRCalculator;
import org.wso2.siddhi.extension.var.models.util.Event;
import org.wso2.siddhi.extension.var.models.util.asset.Asset;
import org.wso2.siddhi.extension.var.models.util.portfolio.Portfolio;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by dilip on 09/01/17.
 */
public class BacktestRealTime {

    private static final int BATCH_SIZE = 251;
    private static final double VAR_CI = 0.95;
    private static final double BACKTEST_CI = 0.05;
    private static final int NUMBER_OF_ASSETS = 1;
    private static final int SAMPLE_SIZE = 1;
    private static final int VAR_PER_SAMPLE = 130;
    private static final String PORTFOLIO_KEY = "Portfolio 1";
    private ArrayList<Double> calculatedVarList;
    private ArrayList<Double> actualVarList;
    private Double previousPortfolioValue;

    public BacktestRealTime() {
        calculatedVarList = new ArrayList();
        actualVarList = new ArrayList();
        previousPortfolioValue = null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        new org.wso2.siddhi.extension.var.backtest.BacktestRealTime().runBackTest();
    }

    private void runBackTest() throws FileNotFoundException {

        //VaRCalculator varCalculator = new HistoricalVaRCalculator(BATCH_SIZE, VAR_CI);
        VaRCalculator varCalculator = new ParametricVaRCalculator(BATCH_SIZE, VAR_CI);
        //VaRCalculator varCalculator = new MonteCarloVarCalculator(BATCH_SIZE, VAR_CI, 2500,100,0.01);

        ArrayList<Event> list = readBacktestData();
        int i = 0;
        int totalEvents = (BATCH_SIZE + 1) * NUMBER_OF_ASSETS + VAR_PER_SAMPLE * NUMBER_OF_ASSETS * SAMPLE_SIZE + 1;
        System.out.println("Read Total Events : " + totalEvents);

        while (i < totalEvents) {
            // fill lists
            if (i < (BATCH_SIZE + 1) * NUMBER_OF_ASSETS) {
                varCalculator.calculateValueAtRisk(list.get(i));
                i++;
            } else {
                System.out.print("Event " + (i) + " : ");
                String jsonString = (String) varCalculator.calculateValueAtRisk(list.get(i));
                JSONObject jsonObject = new JSONObject(jsonString);
                Double calculatedVar = (Double) jsonObject.get(PORTFOLIO_KEY);  // hardcoded for portfolio ID 1
                System.out.print(String.format("CV : %-15f", calculatedVar));
                calculatedVarList.add(calculatedVar);                           // should filter
                calculateActualLoss(varCalculator.getPortfolioPool().get("1"), varCalculator.getAssetPool());
                System.out.println();
                i++;
            }
        }

        runStandardCoverageTest();
    }

    private void runStandardCoverageTest() {

        BinomialDistribution dist = new BinomialDistribution(VAR_PER_SAMPLE, 1 - VAR_CI);
        double leftEnd = dist.inverseCumulativeProbability(BACKTEST_CI / 2);
        double rightEnd = dist.inverseCumulativeProbability(1 - (BACKTEST_CI / 2));

        System.out.println("Left End :" + leftEnd);
        System.out.println("Right End :" + rightEnd);

        int numberOfExceptions = 0;
//        int successCount = 0;
        for (int j = 0; j < SAMPLE_SIZE * NUMBER_OF_ASSETS; j++) {
            for (int i = j * VAR_PER_SAMPLE; i < (j + 1) * VAR_PER_SAMPLE; i++) {
                //System.out.println(actualVarList.get(i) + " " + calculatedVarList.get(i));
                if (actualVarList.get(i) <= calculatedVarList.get(i))
                    numberOfExceptions++;
            }
            System.out.println("Sample Set : " + (j + 1) + " Exceptions : " + numberOfExceptions);

//            if (rightEnd >= numberOfExceptions && leftEnd <= numberOfExceptions) {
//                successCount++;
//            }
        }
        System.out.println("Failure Rate : " + (((double) numberOfExceptions) / (VAR_PER_SAMPLE)) * 100);

    }

    private void calculateActualLoss(Portfolio portfolio, Map<String, Asset> assetList) {
        Double currentPortfolioValue = 0.0;
        Asset temp;
        Object symbol;
        Set<String> keys = portfolio.getAssetListKeySet();
        Iterator itr = keys.iterator();
        while (itr.hasNext()) {
            symbol = itr.next();
            temp = assetList.get(symbol);
            currentPortfolioValue += temp.getCurrentStockPrice() * portfolio.getCurrentAssetQuantities((String) symbol);
        }
        if (previousPortfolioValue != null) {
            actualVarList.add(currentPortfolioValue - previousPortfolioValue);
            System.out.print(String.format("AV : %-15f", currentPortfolioValue - previousPortfolioValue));
        }
        previousPortfolioValue = currentPortfolioValue;
        //System.out.print(String.format("Portfolio Value : %-15f", currentPortfolioValue));
    }

    public ArrayList<Event> readBacktestData() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        Scanner scan = new Scanner(new File(classLoader.getResource("BackTestDataNew.csv").getFile()));
        ArrayList<Event> list = new ArrayList();
        Event event;
        String[] split;

        while (scan.hasNext()) {
            event = new Event();
            split = scan.nextLine().split(",");
            if (split.length == 2) {
                event.setSymbol(split[0]);
                event.setPrice(Double.parseDouble(split[1]));
            } else {
                event.setPortfolioID(split[0]);                     //portfolio id
                event.setQuantity(Integer.parseInt(split[1]));      //shares
                event.setSymbol(split[2]);                          //symbol
                event.setPrice(Double.parseDouble(split[3]));       //price
            }
            list.add(event);
        }
        return list;
    }

}