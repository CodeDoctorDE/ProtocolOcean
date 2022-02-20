package dev.linwood.protocolocean.feature;


import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class OceanPacket {
    private final Identifier id;
    private final PacketByteBuf packet;

    public OceanPacket(Identifier id, PacketByteBuf packet) {
        this.id = id;
        this.packet = packet;
    }

    public Identifier getId() {
        return id;
    }

    public PacketByteBuf getPacket() {
        return packet;
    }

    public void sendPacket(ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, id, packet);
    }
}