package dev.linwood.protocolocean.feature;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class OceanFeature {
    private final List<OceanPacket> changes = new ArrayList<>();

    public List<OceanPacket> getChanges() {
        return Collections.unmodifiableList(changes);
    }

    protected void addChange(OceanPacket packet) {
        changes.add(packet);
    }

    public void applyChanges(boolean isClient) {
        for (OceanPacket change : getChanges()) {
            handle(change, isClient);
        }
        discardChanges();
    }

    public abstract void handle(OceanPacket packet, boolean isClient);

    public void discardChanges() {
        changes.clear();
    }

    public abstract Identifier[] getIds();

    void sendPacket(ServerPlayerEntity... players) {
        for (OceanPacket change : getChanges()) {
            for (ServerPlayerEntity player : players) {
                change.sendPacket(player);
            }
        }
        applyChanges(false);
    }

    abstract void unload(boolean isClient);

    abstract void load(boolean isClient);
}
