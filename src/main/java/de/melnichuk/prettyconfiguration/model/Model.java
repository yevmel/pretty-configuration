package de.melnichuk.prettyconfiguration.model;

import de.melnichuk.prettyconfiguration.model.configuration.Configuration;

import java.util.*;

public class Model {
    private final Collection<Configuration> configurations = new ArrayList<>();

    public Model(Collection<Configuration> configurations) {
        this.configurations.addAll(configurations);
    }

    public Model() {}

    public Collection<Configuration> getConfigurations() {
        return Collections.unmodifiableCollection(configurations);
    }

    public void addConfigurations(List<Configuration> configurations) {
        this.configurations.addAll(configurations);
    }

    public void addConfigurations(Configuration ...configurations) {
        addConfigurations(Arrays.asList(configurations));
    }
}
