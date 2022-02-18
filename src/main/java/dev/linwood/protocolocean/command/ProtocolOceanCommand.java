package dev.linwood.protocolocean.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.linwood.protocolocean.packet.OceanFilter;
import dev.linwood.protocolocean.packet.OceanKeyBinding;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ProtocolOceanCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("protocolocean").then(CommandManager.literal("reload").executes(ProtocolOceanCommand::reload))
                        .then(CommandManager.literal("register").then(
                                CommandManager.literal("keybinding").then(
                                        CommandManager.argument("key", StringArgumentType.string()).then(
                                                CommandManager.argument("code", IntegerArgumentType.integer()).then(
                                                        CommandManager.argument("category", StringArgumentType.string()).executes(ProtocolOceanCommand::registerGlobalKeyBinding).then(
                                                                CommandManager.argument("player", EntityArgumentType.player()).executes(ProtocolOceanCommand::registerKeyBinding))
                                                )
                                        )
                                )).then(CommandManager.literal("filter").then(CommandManager.argument("path", StringArgumentType.string()).then(
                                        CommandManager.argument("player", EntityArgumentType.player()).executes(ProtocolOceanCommand::registerFilter)
                                ))
                        )).then(CommandManager.literal("clear").executes(ProtocolOceanCommand::clear).then(
                                CommandManager.literal("keybindings").executes(ProtocolOceanCommand::clearKeyBindings))
                        )
        );
    }

    private static int registerFilter(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        context.getSource().sendFeedback(Text.of("Filter registered"), false);
        var players = EntityArgumentType.getPlayers(context, "player");
        var path = StringArgumentType.getString(context, "path");
        var filter = new OceanFilter(path);
        players.forEach(filter::sendPacket);
        return 1;
    }

    private static int registerGlobalKeyBinding(CommandContext<ServerCommandSource> context) {
        context.getSource().sendFeedback(Text.of("Global keybinding registered"), false);
        PacketByteBuf buf = PacketByteBufs.create();
        return 1;
    }

    private static int clearKeyBindings(CommandContext<ServerCommandSource> context) {
        return 1;
    }

    private static int clear(CommandContext<ServerCommandSource> context) {
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
        var keyBinding = new OceanKeyBinding(key, code, category);
        players.forEach(keyBinding::sendPacket);
        return 1;
    }
}
