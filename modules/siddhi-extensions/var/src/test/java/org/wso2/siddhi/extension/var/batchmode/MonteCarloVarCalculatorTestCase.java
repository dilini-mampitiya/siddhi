package org.wso2.siddhi.extension.var.batchmode;

import org.junit.Test;
import org.wso2.siddhi.extension.var.models.Asset;
import org.wso2.siddhi.extension.var.models.Portfolio;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by flash on 7/1/16.
 */
public class MonteCarloVarCalculatorTestCase {

    @Test
    public void testProcessData() throws Exception {

        double historicValues_1[] = {
                168.875046,
                167.5,
                166.624954,
                165.750076,
                163.249924,
                168.5,
                171.249924,
                169.249924,
                168.249924,
                163.5,
                160.750076,
                158.124954,
                160,
                159.624954,
                163.5,
                163,
                162.875046,
                162.875046,
                179.750076,
                187.249924,
                182.875046,
                188.249924,
                193,
                191.750076,
                199.875046,
                196,
                197.624954,
                198.624954,
                201.624954,
                210.249924,
                215.5,
                220.124954,
                212,
                217.750076,
                217.750076,
                218.249924,
                212,
                207.5,
                212,
                212.249924,
                215.5,
                220,
                214.5,
                215.375046,
                219.249924,
                217,
                219.5,
                215.5,
                213,
                217,
                215.5,
                218.750076,
                214.750076,
                214.5,
                209.5,
                209.124954,
                204.624954,
                210,
                209.875046,
                207.5,
                206.624954,
                206.124954,
                203.750076,
                198,
                198,
                197.875046,
                201.5,
                209.5,
                208.124954,
                205,
                209.5,
                209.5,
                212.750076,
                212.5,
                209.375046,
                213.750076,
                211.875046,
                208,
                206.875046,
                206,
                202.5,
                202.249924,
                209.875046,
                209.249924,
                207.624954,
                213.750076,
                209.750076,
                212.375046,
                209.750076,
                214.5,
                216,
                223.5,
                223.5,
                217.5,
                216.624954,
                219,
                216,
                212.750076,
                214,
                218.875046,
                216.124954,
                213.375046,
                212.5,
                215,
                218,
                218.5,
                216.624954,
                218,
                213.5,
                212,
                211.5,
                210.249924,
                208.5,
                206.124954,
                208.249924,
                203.5,
                204.249924,
                208.375046,
                206.375046,
                213,
                211.5,
                210.5,
                209.249924,
                209,
                209,
                208.5,
                206.875046,
                206.5,
                204,
                206.124954,
                209.5,
                204.750076,
                205.5,
                208,
                206.750076,
                204.624954,
                202.375046,
                201.875046,
                201,
                197.5,
                193,
                194.249924,
                190.5,
                190.624954,
                192,
                194.875046,
                190.249924,
                189.249924,
                187.750076,
                184.375046,
                186,
                183,
                180,
                183.5,
                182,
                177.5,
                179.750076,
                182.249924,
                182,
                178.249924,
                176.5,
                177.5,
                180.249924,
                182,
                178.249924,
                181,
                186.249924,
                186.249924,
                181.249924,
                182.750076,
                182,
                180,
                183.5,
                179,
                180,
                181.249924,
                180,
                179.875046,
                178.124954,
                179.750076,
                186.249924,
                189.249924,
                186.5,
                188.5,
                191,
                192,
                196,
                190.750076,
                188.249924,
                183.375046,
                188.750076,
                195.249924,
                201,
                201.249924,
                206,
                205.249924,
                206,
                210.5,
                208.5,
                208.750076,
                209.375046,
                207.249924,
                211.5,
                212.875046,
                213.249924,
                215.375046,
                209,
                211.124954,
                215,
                211,
                211.124954,
                212.249924,
                211.249924,
                213.5,
                215.750076,
                217,
                216.249924,
                216.624954,
                219.750076,
                224.750076,
                221.375046,
                222.5,
                225,
                220.375046,
                219.5,
                221,
                221.750076,
                222.249924,
                224.624954,
                225,
                226.375046,
                224.124954,
                220.750076,
                219,
                220.124954,
                214.624954,
                215,
                215.875046,
                217.5,
                214.750076,
                215.624954,
                216.750076,
                219.875046,
                219.750076,
                219.5,
                217.750076,
                216,
                220,
                223.249924,
                223.249924,
                222.5
        };
        double[] set_2 = {
                33.74976,
                32.62512,
                32.62512,
                33.624959,
                33.74976,
                34.12512,
                35.875199,
                36.37488,
                35.499839,
                36,
                36.37488,
                34.750079,
                35.000161,
                34.624801,
                35.875199,
                34.5,
                35.375041,
                36.37488,
                37.750079,
                39.124801,
                38.375041,
                38.62512,
                39.37488,
                38.62512,
                39.74976,
                40.12512,
                39.74976,
                39.999839,
                39.999839,
                41.375041,
                42.124801,
                42.875041,
                42.37488,
                42.875041,
                43.624801,
                44.749921,
                43.87488,
                42.250079,
                42.500161,
                42.999839,
                44.124959,
                44.875199,
                45,
                44.000161,
                45.500161,
                46.375199,
                48.875038,
                48.250082,
                46.249921,
                45.500161,
                46.375199,
                48.74976,
                48,
                47.000161,
                46.375199,
                46.5,
                44.62512,
                45.500161,
                46.87488,
                46.750079,
                45.999839,
                45.875041,
                45.624959,
                45.37488,
                45.124801,
                44.375041,
                45.124801,
                46.750079,
                47.749921,
                48.500158,
                48.875038,
                48.875038,
                48,
                45.999839,
                44.499839,
                45.37488,
                45.624959,
                45,
                44.875199,
                44.749921,
                44.749921,
                44.875199,
                45.999839,
                47.000161,
                48.250082,
                47.875199,
                45.875041,
                47.124959,
                47.62512,
                47.875199,
                47.375041,
                46.750079,
                47.375041,
                47.124959,
                46.249921,
                46.87488,
                45.500161,
                44.875199,
                45.250079,
                45.999839,
                45.500161,
                44.875199,
                44.875199,
                45.37488,
                46.87488,
                47.375041,
                47.25024,
                47.375041,
                47.25024,
                46.5,
                45.74976,
                45.74976,
                45,
                45.999839,
                46.624801,
                45.875041,
                46.375199,
                47.875199,
                47.749921,
                48.37488,
                48.875038,
                50.875202,
                51.875038,
                52.249918,
                52.624798,
                52.249918,
                51.500158,
                52.249918,
                51.74976,
                51.624962,
                52.12512,
                51.999842,
                50.375038,
                50.25024,
                50.375038,
                50.499842,
                50.000158,
                50.25024,
                49.624798,
                49.375202,
                49.375202,
                48.999842,
                47.375041,
                47.000161,
                46.87488,
                47.25024,
                47.25024,
                46.5,
                45.624959,
                45.124801,
                45,
                45.250079,
                44.62512,
                44.749921,
                44.25024,
                43.375199,
                43.375199,
                44.375041,
                44.875199,
                44.25024,
                41.749921,
                42.250079,
                44.000161,
                44.749921,
                43.5,
                44.499839,
                47.000161,
                46.249921,
                44.25024,
                45.250079,
                46.12512,
                46.249921,
                45.875041,
                43.5,
                44.000161,
                43.750079,
                43.249921,
                42.74976,
                42.250079,
                42.624959,
                43.249921,
                44.499839,
                43.5,
                43.750079,
                44.375041,
                44.62512,
                45.500161,
                44.25024,
                44.000161,
                42.500161,
                44.25024,
                45.624959,
                45.37488,
                45.875041,
                47.124959,
                48,
                47.499839,
                48.250082,
                47.499839,
                47.749921,
                47.62512,
                47.499839,
                48.37488,
                48.500158,
                48.74976,
                48.999842,
                48.37488,
                48.124798,
                48.37488,
                47.875199,
                48.250082,
                48.124798,
                47.62512,
                47.62512,
                47.875199,
                47.749921,
                47.499839,
                47.499839,
                48.37488,
                49.5,
                49.249918,
                48.875038,
                49.624798,
                49.624798,
                48.500158,
                47.875199,
                47.875199,
                48,
                48.624962,
                48.124798,
                48.250082,
                48.500158,
                48.124798,
                45.999839,
                45.999839,
                45.500161,
                46.249921,
                46.5,
                47.000161,
                46.5,
                46.5,
                47.25024,
                46.87488,
                45.74976,
                46.249921,
                45.500161,
                45.74976,
                45.500161,
                45.37488,
                46.5,
                45.74976
        };
        double[] set_3 = {
                66.249924,
                67.249924,
                66.249924,
                66.874878,
                66.49984,
                67.249924,
                69,
                68.624962,
                68.750076,
                69.125122,
                68.375038,
                67.125122,
                67.874878,
                67.375038,
                68.125122,
                68.750076,
                69.624962,
                72.125122,
                72.624962,
                74,
                72.49984,
                73.125122,
                74.249924,
                75.750076,
                73.750076,
                73.249924,
                73.249924,
                73.125122,
                72.750076,
                72.375038,
                73.750076,
                73.624962,
                74.125122,
                75.249924,
                77.750076,
                77.249924,
                76.249924,
                75.750076,
                76.750076,
                77,
                77.375038,
                78,
                78.249924,
                75.249924,
                75.874878,
                76.249924,
                77,
                77.624962,
                76.750076,
                76,
                76.125122,
                75.375038,
                73,
                72,
                72.874878,
                73,
                71,
                71.50016,
                74,
                73.624962,
                74,
                74,
                73.375038,
                71.750076,
                70.750076,
                70.375038,
                71.624962,
                73.50016,
                74,
                75,
                76.125122,
                77.874878,
                76.874878,
                78.249924,
                77.750076,
                77.50016,
                78.624962,
                78.375038,
                79.375038,
                80,
                80,
                79.624962,
                81,
                83.50016,
                84.750076,
                86.249924,
                82.375038,
                82.125122,
                81.874878,
                82.125122,
                82.49984,
                83.375038,
                83.375038,
                82.750076,
                82.49984,
                81.750076,
                81.249924,
                80,
                80.874878,
                81.874878,
                83.125122,
                83.874878,
                85.375038,
                87.624962,
                89.125122,
                87.874878,
                86.49984,
                87.125122,
                88,
                87.249924,
                88.249924,
                89.375038,
                89.624962,
                91.125122,
                92,
                90.249924,
                88.874878,
                90.49984,
                91,
                91.249924,
                91.375038,
                91.249924,
                91.874878,
                91.874878,
                92.49984,
                91.874878,
                91.750076,
                91.750076,
                90.49984,
                89.750076,
                90.375038,
                90.375038,
                91.375038,
                91.750076,
                91.375038,
                89.750076,
                89.125122,
                89,
                88.874878,
                87.50016,
                87.874878,
                87.50016,
                87.375038,
                87.375038,
                87.50016,
                87.624962,
                87,
                87.874878,
                86.750076,
                87,
                86.375038,
                86.624962,
                86,
                86,
                87.50016,
                87.125122,
                86,
                86.249924,
                86.249924,
                86.750076,
                84.874878,
                85.375038,
                85,
                85.249924,
                85,
                84.624962,
                86.125122,
                86.750076,
                86.624962,
                86.375038,
                86,
                86.125122,
                86.49984,
                86.249924,
                86.750076,
                86.249924,
                86.249924,
                85.874878,
                85.874878,
                86.125122,
                87.624962,
                89.50016,
                89.249924,
                91.50016,
                91.750076,
                90.49984,
                90.874878,
                88.624962,
                87.50016,
                86.874878,
                87.874878,
                90.249924,
                90.624962,
                91.125122,
                92.375038,
                92.49984,
                91.125122,
                92.375038,
                92.624962,
                92.750076,
                93.375038,
                92,
                92.375038,
                92.874878,
                92,
                92.375038,
                90.125122,
                88.874878,
                90.375038,
                90,
                89.624962,
                89.125122,
                88.750076,
                88.874878,
                87.50016,
                87.50016,
                86.49984,
                86.750076,
                87.375038,
                88.49984,
                88.249924,
                88.125122,
                88.375038,
                88.624962,
                87.624962,
                86,
                84.49984,
                84.750076,
                86,
                86.375038,
                86,
                85.50016,
                85.125122,
                83.624962,
                84.249924,
                83.750076,
                84.375038,
                85.249924,
                86.249924,
                86.125122,
                86.375038,
                86.375038,
                87.624962,
                87.50016,
                87.874878,
                87.624962,
                86.750076,
                86.874878,
                87.874878,
                88.49984,
                89.249924
        };

        double ci = 0.95, timeSlice = 0.01;
        int limit = 250, calculationsPerDay = 100, numberOfTrials = 500000;

        Map<String, Asset> assets = new HashMap<>();
        Asset asset_1 = new Asset("APPL");
        Asset asset_2 = new Asset("GOOG");
        Asset asset_3 = new Asset("FB");

//        for (int i = 0; i < historicValues_1.length; i++) {
//            asset_1.addHistoricalValue(historicValues_1[i]);
//        }
//
//        assets.put("GOOGL", asset_1);
//        for (int i = 0; i < set_2.length; i++) {
//            asset_2.addHistoricalValue(set_2[i]);
//        }
//        assets.put("APPL", asset_2);
//
//        for (int i = 0; i < set_3.length; i++) {
//            asset_3.addHistoricalValue(set_3[i]);
//        }
        assets.put("FB", asset_3);

        Map<String, Integer> assetSet = new HashMap<>();
        assetSet.put("APPL", 100);
        assetSet.put("GOOG", 130);
        assetSet.put("FB", 230);
        Portfolio portfolio = new Portfolio(1, assetSet);

        Map<String, Asset> assetList = new HashMap<>();
        assetList.put("APPL", asset_1);
        assetList.put("GOOG", asset_2);
        assetList.put("FB", asset_3);

        MonteCarloVarCalculator calc = new MonteCarloVarCalculator(limit, ci, numberOfTrials, calculationsPerDay, timeSlice);
//        calc.assetList = assetList;
        long start = System.currentTimeMillis();
        System.out.println(calc.processData(portfolio));
        portfolio.setIncomingEventLabel("APPL");
        System.out.println(calc.processData(portfolio));
        portfolio.setIncomingEventLabel("APPL");
        System.out.println(calc.processData(portfolio));
        portfolio.setIncomingEventLabel("GOOG");
        System.out.println(calc.processData(portfolio));
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000);
    }
}