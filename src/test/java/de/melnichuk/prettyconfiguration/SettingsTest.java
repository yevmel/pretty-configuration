package de.melnichuk.prettyconfiguration;

import com.beust.jcommander.JCommander;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


public class SettingsTest {

    @Test
    public void shouldSetOutputDir() {
        String value = "/foo/bar";
        String[] args = {"--output-dir", value};
        Settings settings = setupSettings(args);

        Assert.assertEquals(settings.getOutputDir(), value);
    }

    @Test
    public void shouldSetOutputName() {
        String value = "foo";
        String[] args = {"--output-name", value};
        Settings settings = setupSettings(args);

        Assert.assertEquals(settings.getOutputName(), value);
    }

    @Test
    public void shouldSetInputDir() {
        String value = "/foo/bar";
        String[] args = {"--input-dir", value};
        Settings settings = setupSettings(args);

        Assert.assertEquals(settings.getInputDir(), value);
    }

    @Test
    public void shouldSetInputFiles() {
        String firstFile = "/foo/bar";
        String secondFile = "/foo/baz";
        Settings settings = setupSettings(new String[]{"--input-files", firstFile, secondFile});

        Assert.assertEquals(2, settings.getInputFiles().size());
        Assert.assertTrue(settings.getInputFiles().contains(firstFile));
        Assert.assertTrue(settings.getInputFiles().contains(secondFile));
    }

    @Test
    public void shouldSetInputMixinFiles() {
        String firstFile = "/foo/bar";
        String secondFile = "/foo/baz";
        Settings settings = setupSettings(new String[]{"--input-mixin-files", firstFile, secondFile});

        Assert.assertEquals(2, settings.getInputMixinFiles().size());
        Assert.assertTrue(settings.getInputMixinFiles().contains(firstFile));
        Assert.assertTrue(settings.getInputMixinFiles().contains(secondFile));
    }

    @Test
    public void shouldSetTemplate() {
        String value = "template.html";
        String[] args = {"--template", value};
        Settings settings = setupSettings(args);

        Assert.assertEquals(settings.getTemplate(), value);
    }

    @Test
    public void shouldSetStaticResource() {
        final String firstStaticResource = "/foo";
        final String secondStaticResource = "/bar";
        String[] args = {"--static-resource", firstStaticResource, "-s", secondStaticResource};
        Settings settings = setupSettings(args);

        List<String> staticResources = settings.getStaticResources();
        Assert.assertEquals(2, staticResources.size());
        Assert.assertTrue(staticResources.contains(firstStaticResource));
        Assert.assertTrue(staticResources.contains(secondStaticResource));
    }

    private Settings setupSettings(String[] args) {
        Settings settings = new Settings();
        JCommander.newBuilder()
                .addObject(settings)
                .args(args)
                .build();

        return settings;
    }
}