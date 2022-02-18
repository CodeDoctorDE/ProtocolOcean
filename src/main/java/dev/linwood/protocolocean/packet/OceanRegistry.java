package dev.linwood.protocolocean.packet;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OceanRegistry {
    private OceanFilter filter;
    private final Set<OceanKeyBinding> keyBindings = new HashSet<>();

    public Set<OceanKeyBinding> getKeyBindings() {
        return Collections.unmodifiableSet(keyBindings);
    }

    protected boolean registerKeyBinding(OceanKeyBinding keybinding) {
        return keyBindings.add(keybinding);
    }

    protected boolean unregisterKeyBinding(String key) {
        return keyBindings.remove(new OceanKeyBinding(key, 0, null));
    }

    public OceanKeyBinding getKeyBinding(String name) {
        return keyBindings.stream().filter(keybinding -> keybinding.getName().equals(name)).findFirst().orElse(null);
    }

    public boolean containsKeyBinding(String key) {
        return keyBindings.contains(new OceanKeyBinding(key, 0, null));
    }

    public OceanFilter getFilter() {
        return filter;
    }

    protected void setFilter(OceanFilter filter) {
        this.filter = filter;
    }

}
