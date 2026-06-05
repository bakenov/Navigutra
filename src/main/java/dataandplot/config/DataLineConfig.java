package dataandplot.config;

import dataandplot.data.generator.DataGeneratorType;

public record DataLineConfig(String name, DataType dataType, DataGeneratorType funType, FunctionConfig functionConfig) {}
