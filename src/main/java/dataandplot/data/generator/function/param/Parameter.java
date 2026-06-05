package dataandplot.data.generator.function.param;

public enum Parameter {
    LINE_A("line.a"),
    LINE_B("line.b"),
    SIN_AMPLITUDE("sine.amplitude"),
    SIN_THETA_INIT("sine.thetaInit"),
    SIN_THETA_STEP("sine.thetaStep"),
    NOISE_LEVEL("noise.noiseLevel");

    private final String propertyName;
    private final ParameterType type;


    Parameter(String propertyName) {
        this(propertyName, ParameterType.DOUBLE);
    }

    Parameter(String propertyName, ParameterType type) {
        this.propertyName = propertyName;
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public ParameterType getType() {
        return type;
    }

    public double convertToDouble(String stringValue) {
        return switch (type) {
            case DOUBLE -> Double.parseDouble(stringValue);
            case INT -> (double) Integer.parseInt(stringValue);
            case FLOAT -> (double) Float.parseFloat(stringValue);
            case STRING -> 0.0;
        };
    }

    public float convertToFloat(String stringValue) {
        return switch (type) {
            case DOUBLE -> (float) Double.parseDouble(stringValue);
            case INT -> (float) Integer.parseInt(stringValue);
            case FLOAT -> Float.parseFloat(stringValue);
            case STRING -> 0.0F;
        };
    }

    public int convertToInt(String stringValue) {
        return switch (type) {
            case DOUBLE -> (int) Double.parseDouble(stringValue);
            case INT -> Integer.parseInt(stringValue);
            case FLOAT -> (int) Float.parseFloat(stringValue);
            case STRING -> 0;
        };
    }

}
