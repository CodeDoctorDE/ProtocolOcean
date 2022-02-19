package dev.linwood.protocolocean.client;

import com.google.gson.Gson;
import dev.linwood.protocolocean.feature.OceanFeatureRegistry;
import dev.linwood.protocolocean.packet.OceanPacketType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class ProtocolOceanClient implements ClientModInitializer {
    private static final Gson GSON = new Gson();
    private final OceanFeatureRegistry registry = new OceanFeatureRegistry();

    @Override
    public void onInitializeClient() {
        for (OceanPacketType type : OceanPacketType.values()) {
            ClientPlayNetworking.registerGlobalReceiver(
                    type.getIdentifier(),
                    (client, handler, buf, responseSender) -> type.create(buf).apply(registry)
            );
        }
    }

    public OceanFeatureRegistry getRegistry() {
        return registry;
    }

}
