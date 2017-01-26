package org.wso2.siddhi.extension.var.realtime.util;

import org.wso2.siddhi.extension.var.realtime.historical.HistoricalVaRCalculator;
import org.wso2.siddhi.extension.var.realtime.montecarlo.MonteCarloVarCalculator;
import org.wso2.siddhi.extension.var.realtime.parametric.ParametricVaRCalculator;

/**
 * Created by dilini92 on 9/1/16.
 */
public class RealTimeVaRConstants {
    public static String PORTFOLIO = "Portfolio ";
    public static int NUMBER_OF_PARAMETERS = 4;

    public static final String HISTORICAL = "HistoricalVaRCalculator";
    public static final String PARAMETRIC = "ParametricVaRCalculator";
    public static final String MONTE_CARLO = "MonteCarloVarCalculator";

    public static final String OUTPUT_NAME = "var";

    /**
     * Array Indices
     */
    public static final int PORTFOLIO_ID_INDEX = 0;
    public static final int SHARES_INDEX = 1;
    public static final int SYMBOL_INDEX = 2;
    public static final int PRICE_INDEX = 3;
    public static final int BATCH_SIZE_INDEX = 4;
    public static final int CI_INDEX = 5;
    public static final int MONTE_CARLO_VERTICAL_SIMULATION_COUNT_INDEX = 6;
    public static final int MONTE_CARLO_HORIZONTAL_SIMULATION_COUNT_INDEX = 7;
    public static final int MONTE_CARLO_TIME_SLICE_INDEX = 8;
}
