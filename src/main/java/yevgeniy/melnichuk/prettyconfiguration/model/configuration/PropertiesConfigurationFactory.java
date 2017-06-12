package yevgeniy.melnichuk.prettyconfiguration.model.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class PropertiesConfigurationFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesConfigurationFactory.class);

    public List<Configuration> createConfigurationFromPropertiesFiles(Collection<File> files) {
        return files.stream()
                .map(this::createConfigurationFromPropertiesFile)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Configuration> createConfigurationFromPropertiesFilesInDirectory(final String path) throws Exception {
        File[] files = new File(path).listFiles((dir, name) -> name.endsWith(".properties"));
        return createConfigurationFromPropertiesFiles(Arrays.asList(files));
    }

    public Configuration createConfigurationFromPropertiesFile(final File file) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (Exception e) {
            LOGGER.error("failed to load properties from " + file.getAbsolutePath(), e);
            return null;
        }

        return createConfigurationFromProperties(properties, file.getName());
    }

    public Configuration createConfigurationFromProperties(final Properties properties, final String identifier) {
        Configuration model = new Configuration(identifier);

        properties.forEach((key, value) -> {
            PropertyNameInformation propertyNameInformation = PropertyNameInformation.extractFromPropertyName(key.toString());

            String name = propertyNameInformation.getName();
            Property property = model.getProperty(name);
            if (property == null) {
                property = new Property(name);
                model.addProperty(property);
            }

            String meta = propertyNameInformation.getMeta();
            if (meta == null) {
                property.setValue(value.toString());
            } else {
                property.addMeta(meta, value.toString());
            }
        });

        return model;
    }
}
