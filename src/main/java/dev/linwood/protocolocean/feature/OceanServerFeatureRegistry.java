package dev.linwood.protocolocean.feature;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OceanServerFeatureRegistry {
    private static final Map<UUID, OceanFeatureRegistry> playerRegistries = new HashMap<>();
    private static final OceanFeatureRegistry globalRegistry = new OceanFeatureRegistry();

    public static OceanFeatureRegistry getGlobalRegistry() {
        return globalRegistry;
    }


    public static @NotNull OceanFeatureRegistry getRegistry(UUID playerId) {
        OceanFeatureRegistry registry = playerRegistries.get(playerId);
        if (registry == null) {
            registry = new OceanFeatureRegistry();
            register(playerId, registry);
        }
        return registry;
    }

    private static void register(UUID playerId, OceanFeatureRegistry registry) {
        playerRegistries.put(playerId, registry);
    }

    public static void unregister(UUID playerId) {
        playerRegistries.remove(playerId);
    }

    public static void clear() {
        playerRegistries.clear();
    }
}
