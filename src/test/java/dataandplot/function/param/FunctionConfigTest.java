package dataandplot.function.param;

import dataandplot.config.FunctionConfig;
import org.junit.jupiter.api.Test;

import static dataandplot.data.generator.function.param.Parameter.*;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionConfigTest {

    @Test
    void test() {
        // build Properties
        Properties properties = new Properties();
        properties.put("app.data.1.line.a", "1.1234");
        properties.put("app.data.1.line.b", "2");

        FunctionConfig config = new FunctionConfig();
        config.processProperties(1, properties);

        assertTrue(config.containsKey(LINE_A));
        assertTrue(config.containsKey(LINE_B));
        assertFalse(config.containsKey(SIN_AMPLITUDE));

        assertEquals(1.1234, config.getDoubleValueBy(LINE_A));
        assertEquals(1, config.getIntValueBy(LINE_A));
        assertEquals("1.1234", config.getValueBy(LINE_A));
        assertEquals(2.0, config.getDoubleValueBy(LINE_B));
        assertEquals(2, config.getIntValueBy(LINE_B));
        assertEquals("2", config.getValueBy(LINE_B));
    }
}
