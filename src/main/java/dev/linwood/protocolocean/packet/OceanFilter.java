package dev.linwood.protocolocean.packet;

import dev.linwood.protocolocean.ProtocolOcean;
import dev.linwood.protocolocean.mixin.GameRendererAccessor;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public final class OceanFilter extends OceanPacket {
    private final String path;


    public OceanFilter(PacketByteBuf buf) {
        this(buf.readString());
    }

    public OceanFilter(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public PacketByteBuf createPacket() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(path);
        return buf;
    }

    @Override
    public OceanPacketType getType() {
        return OceanPacketType.FILTER;
    }

    @Override
    public void apply(OceanRegistry registry) {
        ProtocolOcean.LOGGER.debug("Received filter {}", path);
        var renderer = MinecraftClient.getInstance().gameRenderer;
        MinecraftClient.getInstance().execute(() -> {
            if (path.isBlank()) renderer.disableShader();
            else ((GameRendererAccessor) renderer).invokeLoadShader(new Identifier(path));
        });
        registry.setFilter(this);
    }

}
