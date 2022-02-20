package dev.linwood.protocolocean.feature;

import java.util.Set;

public class OceanFeatureRegistry {
    private final Set<OceanFeature> features = Set.of(new OceanKeyBindFeature(), new OceanFilterFeature());

    public Set<OceanFeature> getFeatures() {
        return features;
    }

    public <T> T getFeature(Class<T> clazz) {
        return features.stream().filter(clazz::isInstance).map(clazz::cast).findFirst().orElse(null);
    }

    public void unload(boolean isClient) {
        features.forEach(oceanFeature -> oceanFeature.unload(isClient));
    }

    public void load(boolean isClient) {
        features.forEach(oceanFeature -> oceanFeature.load(isClient));
    }
}
