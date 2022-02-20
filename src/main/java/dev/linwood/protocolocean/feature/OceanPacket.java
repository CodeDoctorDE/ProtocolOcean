package dev.linwood.protocolocean.feature;


import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public record OceanPacket(Identifier id, PacketByteBuf packet) {

    public void sendPacket(ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, id, packet);
    }

    public PacketByteBuf copyPacket() {
        return PacketByteBufs.copy(packet);
    }
}