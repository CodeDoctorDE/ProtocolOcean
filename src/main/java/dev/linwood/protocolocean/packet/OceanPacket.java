package dev.linwood.protocolocean.packet;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public abstract class OceanPacket {

    public OceanPacket() {

    }

    public OceanPacket(PacketByteBuf buf) {
    }

    public abstract PacketByteBuf createPacket();

    public abstract OceanPacketType getType();

    public Identifier getIdentifier() {
        return getType().getIdentifier();
    }


    public void sendPacket(ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, getIdentifier(), createPacket());
    }

    public abstract void apply();
}

