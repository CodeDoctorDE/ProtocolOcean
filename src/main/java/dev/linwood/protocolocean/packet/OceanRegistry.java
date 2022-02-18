package dev.linwood.protocolocean.packet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OceanRegistry {
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
        return keyBindings.contains(new OceanKeyBinding(key, 0, null));
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
