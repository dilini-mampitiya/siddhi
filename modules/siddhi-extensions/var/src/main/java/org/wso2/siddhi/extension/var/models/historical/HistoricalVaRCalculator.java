package org.wso2.siddhi.extension.var.models.historical;

import org.wso2.siddhi.extension.var.models.util.Event;
import org.wso2.siddhi.extension.var.models.util.asset.Asset;
import org.wso2.siddhi.extension.var.models.util.asset.HistoricalAsset;
import org.wso2.siddhi.extension.var.models.util.portfolio.HistoricalPortfolio;
import org.wso2.siddhi.extension.var.models.util.portfolio.Portfolio;
import org.wso2.siddhi.extension.var.models.VaRCalculator;

import java.util.Map;

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

        //for historical simulate there should be at least one return value
        if (asset.getNumberOfReturnValues() > 0) {
            double var = historicalPortfolio.getHistoricalVarValue();

            double previousReturnValue = asset.getPreviousReturnValue();
            double currentReturnValue = asset.getCurrentReturnValue();

            int previousAssetQuantities = historicalPortfolio.getPreviousAssetQuantities(symbol);
            int currentAssetQuantities = historicalPortfolio.getCurrentAssetQuantities(symbol);

            double previousPrice = asset.getPreviousStockPrice();
            double currentPrice = asset.getCurrentStockPrice();

            //remove the contribution of the asset before the price was changed
            var -= previousReturnValue * previousPrice * previousAssetQuantities;

            //add the new contribution of the asset after the price changed.
            var += currentReturnValue * currentPrice * currentAssetQuantities;

            historicalPortfolio.setHistoricalVarValue(var);

            return var;
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
            asset.setPreviousReturnValue(asset.getCurrentReturnValue());
            double currentReturnValue = asset.getPercentile((1 - getConfidenceInterval()) * 100);
            asset.setCurrentReturnValue(currentReturnValue);
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
