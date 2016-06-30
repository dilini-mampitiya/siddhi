package org.wso2.siddhi.extension.var;

import org.wso2.siddhi.core.config.ExecutionPlanContext;
import org.wso2.siddhi.core.event.ComplexEvent;
import org.wso2.siddhi.core.event.ComplexEventChunk;
import org.wso2.siddhi.core.event.stream.StreamEvent;
import org.wso2.siddhi.core.event.stream.StreamEventCloner;
import org.wso2.siddhi.core.event.stream.populater.ComplexEventPopulater;
import org.wso2.siddhi.core.exception.ExecutionPlanCreationException;
import org.wso2.siddhi.core.executor.ConstantExpressionExecutor;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.query.processor.Processor;
import org.wso2.siddhi.core.query.processor.stream.StreamProcessor;
import org.wso2.siddhi.extension.var.realtime.HistoricalVaRCalcForPortfolio;
import org.wso2.siddhi.extension.var.realtime.VaRPortfolioCalc;
import org.wso2.siddhi.query.api.definition.AbstractDefinition;
import org.wso2.siddhi.query.api.definition.Attribute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilini92 on 6/26/16.
 */
public class HistoricalPortfolioSP extends StreamProcessor {
    private int batchSize = 251;                                        // Maximum # of events, used for regression calculation
    private double ci = 0.95;                                           // Confidence Interval
    private VaRPortfolioCalc varCalculator = null;
    private int paramPosition = 0;
    private String symbols[];
    private int noOfShares[];

    @Override
    protected void process(ComplexEventChunk<StreamEvent> streamEventChunk, Processor nextProcessor, StreamEventCloner streamEventCloner, ComplexEventPopulater complexEventPopulater) {
        synchronized (this) {
            while (streamEventChunk.hasNext()) {
                ComplexEvent complexEvent = streamEventChunk.next();
                Object inputData[] = new Object[2];
                inputData[0] = attributeExpressionExecutors[paramPosition].execute(complexEvent);
                inputData[1] = attributeExpressionExecutors[paramPosition + 1].execute(complexEvent);

                Object outputData[] = new Object[1];
                outputData[0] = varCalculator.calculateValueAtRisk(inputData);

                // Skip processing if user has specified calculation interval
                if (outputData[0] == null) {
                    streamEventChunk.remove();
                } else {
                    complexEventPopulater.populateComplexEvent(complexEvent, outputData);
                }
            }
        }
        nextProcessor.process(streamEventChunk);
    }

    @Override
    protected List<Attribute> init(AbstractDefinition inputDefinition, ExpressionExecutor[] attributeExpressionExecutors, ExecutionPlanContext executionPlanContext) {
        // Capture constant inputs
        if (attributeExpressionExecutors[0] instanceof ConstantExpressionExecutor) {
            paramPosition = (attributeExpressionLength + 4)/2;
            try {
                batchSize = ((Integer) attributeExpressionExecutors[0].execute(null));
            } catch (ClassCastException c) {
                throw new ExecutionPlanCreationException("Calculation interval, batch size and range should be of type int");
            }
            try {
                ci = ((Double) attributeExpressionExecutors[1].execute(null));
                symbols = new String[(attributeExpressionLength - 4)/2];
                noOfShares = new int[(attributeExpressionLength - 4)/2];
                for (int i = 0; i < symbols.length; i++) {
                    symbols[i] = attributeExpressionExecutors[i + 4].execute(null).toString();
                    noOfShares[i] = ((Integer)attributeExpressionExecutors[paramPosition + i].execute(null));
                }
            } catch (ClassCastException c) {
                throw new ExecutionPlanCreationException("Confidence interval should be of type double and a value between 0 and 1");
            }
        }

        // set the var calculator
        varCalculator = new HistoricalVaRCalcForPortfolio(batchSize, ci, symbols, noOfShares);

        // Add attribute for var
        ArrayList<Attribute> attributes = new ArrayList<Attribute>(1);
        attributes.add(new Attribute("var", Attribute.Type.DOUBLE));

        return attributes;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public Object[] currentState() {
        return new Object[0];
    }

    @Override
    public void restoreState(Object[] state) {

    }
}