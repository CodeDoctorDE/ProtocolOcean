package dev.linwood.protocolocean.command;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.linwood.protocolocean.ProtocolOcean;
import dev.linwood.protocolocean.feature.OceanKeyBindFeature;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class KeyBindCommand {
    public static LiteralArgumentBuilder<ServerCommandSource> getHandler() {
        return CommandManager.literal("keybind")
                .then(CommandManager.literal("create").then(CommandManager.argument("name", StringArgumentType.string()))
                        .then(CommandManager.argument("key", IntegerArgumentType.integer()))
                        .then(CommandManager.argument("category", StringArgumentType.string()))
                        .executes(KeyBindCommand::createKeyBind))
                .then(CommandManager.literal("visibility").then(CommandManager.literal("public"))
                        .then(CommandManager.literal("private").then(CommandManager.literal("add"))
                                .then(CommandManager.literal("remove"))
                                .then(CommandManager.literal("status")
                                        .then(CommandManager.argument("player", StringArgumentType.string())))
                        ))
                .then(CommandManager.literal("delete")).then(CommandManager.literal("list"));
    }


    private static int createKeyBind(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(Text.of("Global keybinding registered"), false);
        PacketByteBuf buf = PacketByteBufs.create();
        return 1;
    }

    private static int deleteKeyBind(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        context.getSource().sendFeedback(Text.of("Keybinding unregistered"), false);
        var key = StringArgumentType.getString(context, "key");
        var players = EntityArgumentType.getPlayers(context, "player");
        players.forEach(player -> {
            var feature = ProtocolOcean.REGISTRY.getRegistry(player.getUuid()).getFeature(OceanKeyBindFeature.class);
            feature.remove(key);
            feature.sendPacket(player);
        });

        return 1;
    }

}
