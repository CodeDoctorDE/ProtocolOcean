package dev.linwood.protocolocean.feature;

import java.util.*;

public abstract class CollectionOceanFeature<T extends CollectionOceanFeatureItem> extends AdvancedOceanFeature {
    private final Set<T> collection = new HashSet<>();
    private final List<OceanFeatureChange<T>> changes = new ArrayList<>();

    abstract String getName();

    public void add(T element) {
        collection.add(element);
        changes.add(new OceanFeatureChange<>("add", element));
    }

    public Set<T> getCollection() {
        return Collections.unmodifiableSet(collection);
    }

    @Override
    public OceanFeatureChange<T>[] getChanges() {
        //noinspection unchecked
        return changes.toArray(new OceanFeatureChange[0]);
    }
}
