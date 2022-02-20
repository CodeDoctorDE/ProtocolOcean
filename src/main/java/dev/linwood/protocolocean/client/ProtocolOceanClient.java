package dev.linwood.protocolocean.client;

import com.google.gson.Gson;
import dev.linwood.protocolocean.feature.OceanFeature;
import dev.linwood.protocolocean.feature.OceanFeatureRegistry;
import dev.linwood.protocolocean.feature.OceanPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ProtocolOceanClient implements ClientModInitializer {
    private static final Gson GSON = new Gson();
    public static final OceanFeatureRegistry REGISTRY = new OceanFeatureRegistry();

    @Override
    public void onInitializeClient() {
        for (OceanFeature feature : REGISTRY.getFeatures()) {
            for (Identifier id : feature.getIds()) {
                ClientPlayNetworking.registerGlobalReceiver(
                        id,
                        (client, handler, buf, responseSender) -> feature.handle(new OceanPacket(id, buf), true)
                );
            }
        }
    }

}
