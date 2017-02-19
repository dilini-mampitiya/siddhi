package org.wso2.siddhi.extension.var.models.util.asset;

import java.util.LinkedList;

/**
 * Created by dilini92 on 1/9/17.
 */
public class HistoricalAsset extends Asset {

    private double previousLossReturn;
    private double currentLossReturn;

    private double[] previousSimulatedPriceList;
    private double[] currentSimulatedPriceList;

    public HistoricalAsset(int windowSize) {
        super(windowSize);
    }

    public double getPreviousReturnValue() {
        return previousLossReturn;
    }

    public void setPreviousReturnValue(double previousLossReturn) {
        this.previousLossReturn = previousLossReturn;
    }

    public double getCurrentReturnValue() {
        return currentLossReturn;
    }

    public void setCurrentReturnValue(double currentLossReturn) {
        this.currentLossReturn = currentLossReturn;
    }

    public double[] getPreviousSimulatedPriceList() {
        return previousSimulatedPriceList;
    }

    public double[] getCurrentSimulatedPriceList() {
        return currentSimulatedPriceList;
    }

    public void setPreviousSimulatedPriceList(double[] previousSimulatedPriceList) {
        this.previousSimulatedPriceList = previousSimulatedPriceList;
    }

    public void setCurrentSimulatedPriceList(double[] currentSimulatedPriceList) {
        this.currentSimulatedPriceList = currentSimulatedPriceList;
    }
}
