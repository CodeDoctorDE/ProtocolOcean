package dev.linwood.protocolocean.packet;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OceanServerRegistry {
    private static final Map<UUID, OceanRegistry> playerRegistries = new HashMap<>();
    private static final OceanRegistry globalRegistry = new OceanRegistry();

    public static OceanRegistry getGlobalRegistry() {
        return globalRegistry;
    }

    public static OceanKeyBinding getKeyBinding(UUID playerId, String key) {
        var keyBinding = getRegistry(playerId).getKeyBinding(key);
        if (keyBinding == null) {
            keyBinding = globalRegistry.getKeyBinding(key);
        }
        return keyBinding;
    }

    public static OceanRegistry getRegistry(UUID playerId) {
        return playerRegistries.get(playerId);
    }

    public static void register(UUID playerId, OceanRegistry registry) {
        playerRegistries.put(playerId, registry);
    }

    public static void unregister(UUID playerId) {
        playerRegistries.remove(playerId);
    }

    public static void clear() {
        playerRegistries.clear();
    }
}
