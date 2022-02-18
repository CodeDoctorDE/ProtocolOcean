package dev.linwood.protocolocean.packet;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProtocolOceanServerRegistry {
    private static final Map<UUID, ProtocolOceanRegistry> playerRegistries = new HashMap<>();
    private static final ProtocolOceanRegistry globalRegistry = new ProtocolOceanRegistry();

    public static ProtocolOceanRegistry getGlobalRegistry() {
        return globalRegistry;
    }

    public static OceanKeyBinding getKeyBinding(UUID playerId, String key) {
        var keyBinding = getRegistry(playerId).getKeyBinding(key);
        if (keyBinding == null) {
            keyBinding = globalRegistry.getKeyBinding(key);
        }
        return keyBinding;
    }

    public static ProtocolOceanRegistry getRegistry(UUID playerId) {
        return playerRegistries.get(playerId);
    }

    public static void register(UUID playerId, ProtocolOceanRegistry registry) {
        playerRegistries.put(playerId, registry);
    }

    public static void unregister(UUID playerId) {
        playerRegistries.remove(playerId);
    }

    public static void clear() {
        playerRegistries.clear();
    }
}
