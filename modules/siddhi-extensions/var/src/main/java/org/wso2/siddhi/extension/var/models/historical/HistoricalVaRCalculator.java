package org.wso2.siddhi.extension.var.models.historical;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.wso2.siddhi.extension.var.models.util.Event;
import org.wso2.siddhi.extension.var.models.util.asset.Asset;
import org.wso2.siddhi.extension.var.models.util.asset.HistoricalAsset;
import org.wso2.siddhi.extension.var.models.util.portfolio.HistoricalPortfolio;
import org.wso2.siddhi.extension.var.models.util.portfolio.Portfolio;
import org.wso2.siddhi.extension.var.models.VaRCalculator;

import java.util.*;

/**
 * Created by dilini92 on 6/26/16.
 */
public class HistoricalVaRCalculator extends VaRCalculator {

    /**
     * @param batchSize
     * @param ci
     */
    public HistoricalVaRCalculator(int batchSize, double ci) {
        super(batchSize, ci);

    }

    /**
     * @return the var of the portfolio
     * Calculate the contribution of the changed asset to the portfolio and then adjust the previous VaR value
     * using Historical data
     */
    @Override
    public Double processData(Portfolio portfolio, Event event) {
        HistoricalPortfolio historicalPortfolio = (HistoricalPortfolio) portfolio;
        String symbol = event.getSymbol();
        HistoricalAsset asset = (HistoricalAsset) getAssetPool().get(symbol);
        double[] currentSimulatedPriceList = asset.getCurrentSimulatedPriceList();
        double[] cumulativeLossValues = historicalPortfolio.getCumulativeLossValues();

        if(asset.getNumberOfReturnValues() > 0) {
            DescriptiveStatistics descriptiveStatistics = new DescriptiveStatistics(currentSimulatedPriceList.length);
            if(cumulativeLossValues == null){
                cumulativeLossValues = new double[getBatchSize() - 1];
            }

            double previousSimulatedPriceList[] = asset.getPreviousSimulatedPriceList();
            int previousQty = portfolio.getPreviousAssetQuantities(symbol);
            int currentQty = portfolio.getCurrentAssetQuantities(symbol);
            for (int i = 0; i < currentSimulatedPriceList.length; i++) {
                if(i < previousSimulatedPriceList.length){
                    cumulativeLossValues[i] = cumulativeLossValues[i] -
                            (previousSimulatedPriceList[i] * previousQty);
                }

                cumulativeLossValues[i] += (currentSimulatedPriceList[i] * currentQty);
                descriptiveStatistics.addValue(cumulativeLossValues[i]);
            }
            historicalPortfolio.setCumulativeLossValues(cumulativeLossValues);
            return descriptiveStatistics.getPercentile((1 - getConfidenceInterval()) * 100);
        }
        return null;
    }

    /**
     * simulate the changed asset once
     *
     * @param symbol
     */
    @Override
    public void simulateChangedAsset(String symbol) {
        HistoricalAsset asset = (HistoricalAsset) getAssetPool().get(symbol);
        if (asset.getNumberOfReturnValues() > 0) {
            double returnValues[] = asset.getReturnValues();
            double[] currentSimulatedPriceList = asset.getCurrentSimulatedPriceList();
            if(currentSimulatedPriceList == null){
                asset.setPreviousSimulatedPriceList(new double[1]);
            }else{
                asset.setPreviousSimulatedPriceList(currentSimulatedPriceList);
            }

            double temp[] = new double[returnValues.length];

            for (int i = 0; i < returnValues.length; i++) {
                temp[i] = -returnValues[i] * asset.getCurrentStockPrice();
            }
            asset.setCurrentSimulatedPriceList(temp);
        }
    }

    @Override
    public Portfolio createPortfolio(String id, Map<String, Integer> assets) {
        return new HistoricalPortfolio(id, assets);
    }

    @Override
    public Asset createAsset(int windowSize) {
        return new HistoricalAsset(windowSize);
    }
}
