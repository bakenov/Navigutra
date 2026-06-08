package dataandplot.data.model.data.generator;

import dataandplot.config.*;
import dataandplot.data.generator.DataGeneratorType;
import dataandplot.data.generator.function.param.Parameter;

import java.util.Properties;

public class BaseGeneratorTest {

    public FunctionConfig buildFunctionConfig() {
        FunctionConfig functionConfig = new FunctionConfig();
        functionConfig.set(Parameter.LINE_A, "2.5");
        functionConfig.set(Parameter.LINE_B, "1.0");
        return functionConfig;
    }

    public DataLineConfig buildDataLineConfig() {
        return new DataLineConfig("testName", DataGeneratorType.LINE, buildFunctionConfig());
    }

    public DataConfig buildDataConfig() {
        return new DataConfigImpl(buildDefaultTestProperties());
    }

    public Properties buildDefaultTestProperties() {
        Properties properties = new Properties();
        properties.put("app.data.title", "Test title");
        properties.put("app.data.size", "101");
        properties.put("app.data.stepMultiplicator", "2.0");
        properties.put("app.data.1.name", "Just Line");
        properties.put("app.data.1.funType", "LINE");
        properties.put("app.data.1.dataType", "DOUBLE");
        properties.put("app.data.1.line.a", "1.0");
        properties.put("app.data.1.line.b", "0");
        return properties;
    }

}
