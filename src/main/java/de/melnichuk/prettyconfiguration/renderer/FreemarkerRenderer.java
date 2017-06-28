package de.melnichuk.prettyconfiguration.renderer;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import de.melnichuk.prettyconfiguration.model.Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FreemarkerRenderer implements Renderer {
    private final File template;
    private final String outputDir;

    public FreemarkerRenderer(File template, String outputDir) {
        this.template = template;
        this.outputDir = outputDir;
    }

    private Configuration createConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setTemplateLoader(new FileTemplateLoader(template.getParentFile()));
        return cfg;
    }

    @Override
    public void render(Model model) throws Exception {
        render(model, getDefaultOutputFileName());
    }

    @Override
    public void render(Model model, String outputFileNameCandidate) throws Exception {
        final String outputFileName = outputFileNameCandidate == null ? getDefaultOutputFileName() : outputFileNameCandidate;
        final File output = new File(outputDir, outputFileName);
        createConfiguration()
                .getTemplate(this.template.getName())
                .process(model, new FileWriter(output));
    }

    private String getDefaultOutputFileName() {
        return template.getName();
    }
}
