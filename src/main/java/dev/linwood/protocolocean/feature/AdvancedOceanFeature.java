package dev.linwood.protocolocean.feature;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public abstract class AdvancedOceanFeature extends OceanFeature {
    public abstract OceanFeatureChange[] getChanges();

    public abstract void discardChanges();

    public abstract Identifier[] getIds();

    @Override
    void sendPacket(ServerPlayerEntity player) {
        for (OceanFeatureChange change : getChanges()) {
            change.sendPacket(player);
        }
    }
}
