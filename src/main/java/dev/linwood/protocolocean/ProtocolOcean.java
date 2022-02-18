package dev.linwood.protocolocean;

import com.google.gson.Gson;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.linwood.protocolocean.packet.ProtocolOceanPackets;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtocolOcean implements ModInitializer {
    private static final Gson GSON = new Gson();
    public static final Logger LOGGER = LoggerFactory.getLogger("protocolocean");

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(CommandManager.literal("protocolocean").then(CommandManager.argument("key", StringArgumentType.string()).then(CommandManager.argument("code", IntegerArgumentType.integer())
                .then(CommandManager.argument("category", StringArgumentType.string()).executes(context -> {
                    context.getSource().sendFeedback(Text.of("Test command"), false);
                    PacketByteBuf buf = PacketByteBufs.create();
                    buf.writeString(context.getArgument("key", String.class));
                    buf.writeInt(context.getArgument("code", Integer.class));
                    buf.writeString(context.getArgument("category", String.class));
                    ServerPlayNetworking.send(context.getSource().getPlayer(), ProtocolOceanPackets.KEY_BIND, buf);
            return 1;
        }))))));
        LOGGER.info("ProtocolOcean loaded");
    }
}
