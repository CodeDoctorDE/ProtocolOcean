package dev.linwood.protocolocean.feature;

import java.util.Collections;
import java.util.Set;

public class OceanFeatureRegistry {
    private final Set<OceanFeature> features = Set.of(new OceanKeyBindFeature());

    public Set<OceanFeature> getFeatures() {
        return Collections.unmodifiableSet(features);
    }

    public <T extends OceanFeature> T getFeature(Class<T> clazz) {
        return features.stream().filter(clazz::isInstance).map(clazz::cast).findFirst().orElse(null);
    }

    public void unload() {
        features.forEach(OceanFeature::unload);
    }

    public void load() {
        features.forEach(OceanFeature::load);
    }
}
