package dev.linwood.protocolocean.feature;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OceanServerFeatureRegistry {
    private final Map<UUID, OceanFeatureRegistry> playerRegistries = new HashMap<>();
    private final OceanFeatureRegistry globalRegistry = new OceanFeatureRegistry();

    public OceanFeatureRegistry getGlobalRegistry() {
        return globalRegistry;
    }


    public @NotNull OceanFeatureRegistry getRegistry(UUID playerId) {
        OceanFeatureRegistry registry = playerRegistries.get(playerId);
        if (registry == null) {
            registry = new OceanFeatureRegistry();
            register(playerId, registry);
        }
        return registry;
    }

    private void register(UUID playerId, OceanFeatureRegistry registry) {
        playerRegistries.put(playerId, registry);
    }

    public void unregister(UUID playerId) {
        playerRegistries.remove(playerId);
    }

    public void clear() {
        playerRegistries.clear();
    }
}
