package dev.linwood.protocolocean.feature;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public abstract class SimpleOceanFeature extends OceanFeature {
    abstract Identifier getId();

    abstract PacketByteBuf getPacket();

    abstract void handle(PacketByteBuf buf);

    @Override
    public void sendPacket(ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, getId(), getPacket());
    }
}
