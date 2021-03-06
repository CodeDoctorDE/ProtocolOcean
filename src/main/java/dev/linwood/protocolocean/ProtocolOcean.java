package dev.linwood.protocolocean;

import com.google.gson.Gson;
import dev.linwood.protocolocean.command.ProtocolOceanCommand;
import dev.linwood.protocolocean.feature.OceanServerFeatureRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtocolOcean implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("protocolocean");
    public static final OceanServerFeatureRegistry REGISTRY = new OceanServerFeatureRegistry();
    private static final Gson GSON = new Gson();

    private static void onJoin(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        // TODO: Send all registries
    }

    private static void onDisconnect(ServerPlayNetworkHandler handler, MinecraftServer server) {
        REGISTRY.unregister(handler.getPlayer().getUuid());
    }

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> ProtocolOceanCommand.register(dispatcher));
        ServerPlayConnectionEvents.JOIN.register(ProtocolOcean::onJoin);
        ServerPlayConnectionEvents.DISCONNECT.register(ProtocolOcean::onDisconnect);
        LOGGER.info("ProtocolOcean loaded");
    }

}
