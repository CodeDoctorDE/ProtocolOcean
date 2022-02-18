package dev.linwood.protocolocean.packet;

import java.util.*;

public class ProtocolOceanRegistry {
    private final Set<OceanKeyBinding> keyBindings = new HashSet<>();

    public Set<OceanKeyBinding> getKeyBindings() {
        return Collections.unmodifiableSet(keyBindings);
    }

    public boolean registerKeyBinding(OceanKeyBinding keybinding) {
        return keyBindings.add(keybinding);
    }

    public boolean unregisterKeyBinding(OceanKeyBinding keybinding) {
        return keyBindings.remove(keybinding);
    }

    public boolean containsKeyBinding(String key) {
        return keyBindings.contains(new OceanKeyBinding(key, null, 0));
    }

    public void clearKeyBindings() {
        keyBindings.clear();
    }

    public void clear() {
        clearKeyBindings();
    }

    public OceanKeyBinding getKeyBinding(String name) {
        return keyBindings.stream().filter(keybinding -> keybinding.getName().equals(name)).findFirst().orElse(null);
    }
}
