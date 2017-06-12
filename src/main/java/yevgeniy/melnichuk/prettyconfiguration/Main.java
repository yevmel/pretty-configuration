package yevgeniy.melnichuk.prettyconfiguration;

import com.beust.jcommander.JCommander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import yevgeniy.melnichuk.prettyconfiguration.model.configuration.Configuration;
import yevgeniy.melnichuk.prettyconfiguration.model.Model;
import yevgeniy.melnichuk.prettyconfiguration.model.configuration.PropertiesConfigurationFactory;
import yevgeniy.melnichuk.prettyconfiguration.renderer.FreemarkerRenderer;
import yevgeniy.melnichuk.prettyconfiguration.renderer.Renderer;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String ...args) throws Exception {
        Settings settings = setupSettings(args);

        Collection<File> files = settings.getInputFiles().stream().map(path -> new File(path)).collect(Collectors.toSet());

        // TODO process files individually to be able to mix *.properties and other file types.
        List<Configuration> configurations = new PropertiesConfigurationFactory()
                .createConfigurationFromPropertiesFiles(files);

        Model model = new Model(configurations);
        Renderer renderer = setupRenderer(model, settings);
        renderer.render(model, settings.getOutputName());
    }

    private static Renderer setupRenderer(final Model model, final Settings settings) throws Exception {
        String outputDir = settings.getOutputDir();
        ensureOutputDirExists(outputDir);

        File template = new File(settings.getTemplate());
        return new FreemarkerRenderer(template, outputDir);
    }

    private static void ensureOutputDirExists(final String outputDir) {
        LOGGER.debug("checking {} exists.", outputDir);
        new File(outputDir).mkdirs();
    }

    private static Settings setupSettings(String ...args) {
        Settings settings = new Settings();
        JCommander.newBuilder()
                .addObject(settings)
                .args(args)
                .build();

        return settings;
    }
}
