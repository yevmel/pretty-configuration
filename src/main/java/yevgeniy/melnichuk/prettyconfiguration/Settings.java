package yevgeniy.melnichuk.prettyconfiguration;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class Settings {

    @Parameter(names = {"--template", "-t"})
    private String template;

    @Parameter(names = {"--output-dir"})
    private String outputDir;

    @Parameter(names = {"--output-name"})
    private String outputName;

    @Parameter(names = {"--static-resource", "-s"})
    private List<String> staticResources = new ArrayList<>();

    @Parameter(names = "--input-dir" )
    private String inputDir;

    @Parameter(names = "--input-files", variableArity = true)
    private List<String> inputFiles;

    public String getTemplate() {
        return template;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public String getOutputName() {
        return outputName;
    }

    public List<String> getStaticResources() {
        return staticResources;
    }

    public String getInputDir() {
        return inputDir;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }
}
