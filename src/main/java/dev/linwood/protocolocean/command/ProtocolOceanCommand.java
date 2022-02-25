package dev.linwood.protocolocean.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.linwood.protocolocean.ProtocolOcean;
import dev.linwood.protocolocean.feature.OceanFilterFeature;
import dev.linwood.protocolocean.feature.OceanKeyBindFeature;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ProtocolOceanCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("protocolocean").then(CommandManager.literal("reload").executes(ProtocolOceanCommand::reload))
                        .then(KeyBindCommand.getHandler()).then(CommandManager.literal("filter").then(CommandManager.argument("path", StringArgumentType.string()).then(
                                CommandManager.argument("player", EntityArgumentType.player()).executes(ProtocolOceanCommand::registerFilter)
                        )))
        );
    }

    private static int registerFilter(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        context.getSource().sendFeedback(Text.of("Filter registered"), false);
        var players = EntityArgumentType.getPlayers(context, "player");
        var path = StringArgumentType.getString(context, "path");
        players.forEach(player -> {
            var feature = ProtocolOcean.REGISTRY.getRegistry(player.getUuid()).getFeature(OceanFilterFeature.class);
            feature.setFilter(path);
            feature.sendPacket(player);
        });
        return 1;
    }


    private static int reload(CommandContext<ServerCommandSource> context) {
        return 1;
    }

    private static int registerKeyBinding(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        context.getSource().sendFeedback(Text.of("Keybinding registered"), false);
        var players = EntityArgumentType.getPlayers(context, "player");
        var key = StringArgumentType.getString(context, "key");
        var code = IntegerArgumentType.getInteger(context, "code");
        var category = StringArgumentType.getString(context, "category");
        players.forEach(player -> {
            var feature = ProtocolOcean.REGISTRY.getRegistry(player.getUuid()).getFeature(OceanKeyBindFeature.class);
            feature.add(key, code, category);
            feature.sendPacket(player);
        });
        return 1;
    }
}
