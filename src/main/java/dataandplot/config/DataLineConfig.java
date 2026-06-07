package dataandplot.config;

import dataandplot.data.generator.DataGeneratorType;

public record DataLineConfig(String name, DataGeneratorType funType, FunctionConfig functionConfig) {}
