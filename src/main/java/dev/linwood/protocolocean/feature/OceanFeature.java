package dev.linwood.protocolocean.feature;

import net.minecraft.server.network.ServerPlayerEntity;

public abstract class OceanFeature {
    abstract void unload();

    abstract void load();

    abstract void sendPacket(ServerPlayerEntity player);
}
