package dataandplot.data.provider.builder;

import dataandplot.config.DataConfig;
import dataandplot.data.generator.builder.StepDataGeneratorBuilder;
import dataandplot.data.generator.builder.StepDataGeneratorBuilderImpl;

import java.util.Properties;

import static dataandplot.data.generator.DataGeneratorType.*;

public class DataProviderBuilderImpl implements DataProviderBuilder {

    private final StepDataGeneratorBuilder stepDataGeneratorBuilder;

    public DataProviderBuilderImpl() {
        this.stepDataGeneratorBuilder = new StepDataGeneratorBuilderImpl(null);
    }

    @Override
    public void build(final DataConfig dataConfig, final Properties config) {
//        FunctionInfo functionInfo1, functionInfo2;
//        FunctionStepDataGenerator stepDataGenerator = null;
//        DataHolderFloat dataHolder = new DataHolderFloatImpl(dataConfig);
//        switch (dataConfig.getType()) {
//            case SINE:
//            case LINE:
//            case NOISE:
//                functionInfo1 = functionInfoBuilder.buildFunctionInfo(dataConfig.getType(), config);
//                IO.println("DataProviderBuilderImpl.build()   functionInfo1:" + functionInfo1);
//                stepDataGenerator = stepDataGeneratorBuilder.buildStepGenerator(dataConfig, functionInfo1);
//                break;
//            case SINE_NOISE:
//                functionInfo1 = functionInfoBuilder.buildFunctionInfo(SINE, config);
//                functionInfo2 = functionInfoBuilder.buildFunctionInfo(NOISE, config);
//                stepDataGenerator = stepDataGeneratorBuilder.buildStepGenerator(dataConfig, functionInfo1, functionInfo2);
//                break;
//            case LINE_NOISE:
//                functionInfo1 = functionInfoBuilder.buildFunctionInfo(LINE, config);
//                functionInfo2 = functionInfoBuilder.buildFunctionInfo(NOISE, config);
//                stepDataGenerator = stepDataGeneratorBuilder.buildStepGenerator(dataConfig, functionInfo1, functionInfo2);
//                break;
//            case SINE_LINE:
//                functionInfo2 = functionInfoBuilder.buildFunctionInfo(LINE, config);
//                functionInfo1 = functionInfoBuilder.buildFunctionInfo(SINE, config);
//                stepDataGenerator = stepDataGeneratorBuilder.buildStepGenerator(dataConfig, functionInfo1, functionInfo2);
//                break;
//            case SINE_LINE_NOISE:
//                functionInfo1 = functionInfoBuilder.buildFunctionInfo(SINE, config);
//                functionInfo2 = functionInfoBuilder.buildFunctionInfo(LINE, config);
//                FunctionInfo functionInfo3 = functionInfoBuilder.buildFunctionInfo(NOISE, config);
//                stepDataGenerator = stepDataGeneratorBuilder.buildStepGenerator(dataConfig, functionInfo1, functionInfo2, functionInfo3);
//        }
//        return new DataProviderImpl(generatorInfo, stepDataGenerator, dataHolder);
    }
}
