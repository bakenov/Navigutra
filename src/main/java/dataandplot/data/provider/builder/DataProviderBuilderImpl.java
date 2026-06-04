package dataandplot.data.provider.builder;

import dataandplot.data.generator.builder.StepDataGeneratorBuilder;
import dataandplot.data.generator.builder.StepDataGeneratorBuilderImpl;
import dataandplot.data.generator.config.*;
import dataandplot.data.generator.config.builder.FunctionInfoBuilder;
import dataandplot.data.generator.step.FunctionStepDataGenerator;
import dataandplot.data.holder.DataHolderFloat;
import dataandplot.data.holder.DataHolderFloatImpl;
import dataandplot.data.provider.DataProvider;
import dataandplot.data.provider.DataProviderImpl;

import java.util.Properties;

import static dataandplot.data.generator.config.DataGeneratorType.*;

public class DataProviderBuilderImpl implements DataProviderBuilder {

    private final StepDataGeneratorBuilder stepDataGeneratorBuilder;
    private final FunctionInfoBuilder functionInfoBuilder;

    public DataProviderBuilderImpl() {
        this.stepDataGeneratorBuilder = new StepDataGeneratorBuilderImpl();
        this.functionInfoBuilder = new FunctionInfoBuilder();
    }

    @Override
    public void build(final GeneratorInfo generatorInfo, final Properties config) {
        FunctionInfo functionInfo1, functionInfo2;
        FunctionStepDataGenerator stepDataGenerator = null;
        DataHolderFloat dataHolder = new DataHolderFloatImpl(generatorInfo);
        switch (generatorInfo.type()) {
            case SINE:
            case LINE:
            case NOISE:
                functionInfo1 = functionInfoBuilder.buildFunctionInfo(generatorInfo.type(), config);
                IO.println("DataProviderBuilderImpl.build()   functionInfo1:" + functionInfo1);
                stepDataGenerator = stepDataGeneratorBuilder.buildStepGenerator(generatorInfo, functionInfo1);
                break;
            case SINE_NOISE:
                functionInfo1 = functionInfoBuilder.buildFunctionInfo(SINE, config);
                functionInfo2 = functionInfoBuilder.buildFunctionInfo(NOISE, config);
                stepDataGenerator = stepDataGeneratorBuilder.buildStepGenerator(generatorInfo, functionInfo1, functionInfo2);
                break;
            case LINE_NOISE:
                functionInfo1 = functionInfoBuilder.buildFunctionInfo(LINE, config);
                functionInfo2 = functionInfoBuilder.buildFunctionInfo(NOISE, config);
                stepDataGenerator = stepDataGeneratorBuilder.buildStepGenerator(generatorInfo, functionInfo1, functionInfo2);
                break;
            case SINE_LINE:
                functionInfo2 = functionInfoBuilder.buildFunctionInfo(LINE, config);
                functionInfo1 = functionInfoBuilder.buildFunctionInfo(SINE, config);
                stepDataGenerator = stepDataGeneratorBuilder.buildStepGenerator(generatorInfo, functionInfo1, functionInfo2);
                break;
            case SINE_LINE_NOISE:
                functionInfo1 = functionInfoBuilder.buildFunctionInfo(SINE, config);
                functionInfo2 = functionInfoBuilder.buildFunctionInfo(LINE, config);
                FunctionInfo functionInfo3 = functionInfoBuilder.buildFunctionInfo(NOISE, config);
                stepDataGenerator = stepDataGeneratorBuilder.buildStepGenerator(generatorInfo, functionInfo1, functionInfo2, functionInfo3);
        }
//        return new DataProviderImpl(generatorInfo, stepDataGenerator, dataHolder);
    }
}
