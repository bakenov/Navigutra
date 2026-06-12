package dataandplot.data.generator.function;

import dataandplot.data.generator.index.IndexToXDouble;

import java.util.function.DoubleUnaryOperator;

public interface DataFunction extends DoubleUnaryOperator {
    IndexToXDouble indexToXDouble();
}
