package dataandplot.config;

import dataandplot.data.generator.DataGeneratorType;

public record DataLineConfig(String name, DataGeneratorType type, FunctionConfig functionConfig) {}
