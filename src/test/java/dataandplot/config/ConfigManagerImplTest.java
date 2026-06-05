package dataandplot.config;

import dataandplot.data.generator.DataGeneratorType;
import dataandplot.data.generator.function.param.Parameter;
import dataandplot.plot.Insets;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigManagerImplTest {

    @Test
    void test() {
        ConfigManagerImpl manager = new ConfigManagerImpl(ConfigManagerImplTest.class.getClassLoader());
        File file = manager.getConfigDir();
        assertTrue(file.getAbsolutePath().endsWith("dataConfig"));
        UIConfig uiConfig = manager.getUIConfig();
        assertEquals(new Insets(51, 21, 21, 41), uiConfig.plotInsets());
        assertEquals(new Dimension(501, 501), uiConfig.dimension());


        boolean result = manager.dataConfigFileChanged(getFileInTestResources("test1.properties"));
        assertTrue(result);
        DataConfig dataConfig = manager.getDataConfig();
        assertEquals("Test title", dataConfig.getTitle());
        assertEquals(101, dataConfig.getSize());
        assertEquals(2.0, dataConfig.getStepMultiplicator());

        List<DataLineConfig> dataLineConfigs = dataConfig.getDataLineConfigs();
        assertEquals(1, dataLineConfigs.size());
        DataLineConfig dataLineConfig1 = dataLineConfigs.get(0);
        assertEquals("Just Line", dataLineConfig1.name());
        assertEquals(DataGeneratorType.LINE, dataLineConfig1.type());

        FunctionConfig funConfig = dataLineConfig1.functionConfig();
        assertEquals(1.0, funConfig.getDoubleValueBy(Parameter.LINE_A));
        assertEquals(0.0, funConfig.getDoubleValueBy(Parameter.LINE_B));

        result = manager.dataConfigFileChanged(getFileInTestResources("test1.properties"));
        assertFalse(result);
    }

    private File getFileInTestResources(String fileName) {
        URL resourceUrl = getClass().getClassLoader().getResource(ConfigManager.APP_CONFIG_DIR_NAME + "/" + fileName);
        if (resourceUrl != null) {
            // Convert URL to a standard File object
            try {
                return new File(resourceUrl.toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("No config dir in application's resource folder");
    }



}
