package dev.linwood.protocolocean.feature;


public class OceanFeatureChange<T> {
    private final String type;
    private final T value;

    public OceanFeatureChange(String type, T value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public T getValue() {
        return value;
    }
}