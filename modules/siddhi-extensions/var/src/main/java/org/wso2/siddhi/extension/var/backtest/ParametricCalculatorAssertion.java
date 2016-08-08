//package org.wso2.siddhi.extension.var.backtest;
//
//import org.wso2.siddhi.extension.var.batchmode.MonteCarloVarCalculator;
//import org.wso2.siddhi.extension.var.models.Asset;
//import org.wso2.siddhi.extension.var.realtime.ParametricVaRCalculator;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Map;
//
///**
// * Created by flash on 7/14/16.
// */
//public class ParametricCalculatorAssertion extends VarModelAssertion {
//    //    private Map<String, Asset> assets;
//    private ParametricVaRCalculator calculator = null;
//
//    public ParametricCalculatorAssertion(int sampleSize, Map<String, Asset> portfolio,
//                               double confidenceInterval, int limit) {
//        super(sampleSize, portfolio, confidenceInterval, limit);
//
//    }
//
//    @Override
//    protected Double[] calculateVar() throws IOException {
//        Double tempVar[] = new Double[this.getSampleSize()];
//
//        Map<String, ArrayList<Double>> priceLists = this.getDataFromFile();
//        String[] key = this.getPortfolio().keySet().toArray(new String[this.getPortfolio().size()]);
//        calculator = new ParametricVaRCalculator(this.getBatchSize(), this.getConfidenceInterval(), this.getPortfolio());
//
//        for (int i = 0; i < tempVar.length; i++) {
//            for (int j = 0; j < key.length; j++) {
//                Object input[] = {key[j], priceLists.get(key[j]).get(i + this.getBatchSize())};
//                calculator.addEvent(input);
//                calculator.removeEvent(key[j]);
//            }
//            tempVar[i] = (Double) calculator.processData();
//        }
//        return tempVar;
//    }
//}
