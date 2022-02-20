package dev.linwood.protocolocean.feature;

import dev.linwood.protocolocean.mixin.GameRendererAccessor;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

public class OceanFilterFeature extends OceanFeature {
    private static final Identifier FILTER_ID = new Identifier("protocolocean", "filter");
    private String filter = "";

    @Override
    public void handle(OceanPacket packet, boolean isClient) {
        this.filter = packet.getPacket().readString();
        if (isClient) {
            var renderer = MinecraftClient.getInstance().gameRenderer;
            MinecraftClient.getInstance().execute(() -> {
                if (filter.isBlank()) renderer.disableShader();
                else ((GameRendererAccessor) renderer).invokeLoadShader(new Identifier(filter));
            });
        }
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        var buf = PacketByteBufs.create();
        buf.writeString(filter);
        addChange(new OceanPacket(FILTER_ID, buf));
    }

    public boolean hasFilter() {
        return !filter.isEmpty();
    }

    @Override
    public Identifier[] getIds() {
        return new Identifier[]{FILTER_ID};
    }

    @Override
    void unload(boolean isClient) {

    }

    @Override
    void load(boolean isClient) {

    }
}
