package de.melnichuk.prettyconfiguration.renderer;


import de.melnichuk.prettyconfiguration.model.Model;

public interface Renderer {
    void render(Model model) throws Exception;
    void render(Model model, String outputFileName) throws Exception;
}
