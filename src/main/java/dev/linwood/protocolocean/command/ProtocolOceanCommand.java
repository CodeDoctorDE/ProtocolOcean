package dev.linwood.protocolocean.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.linwood.protocolocean.packet.OceanKeyBinding;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
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
                                        CommandManager.argument("player", EntityArgumentType.player()).then(
                                                CommandManager.argument("key", StringArgumentType.string()).then(
                                                        CommandManager.argument("code", IntegerArgumentType.integer()).then(
                                                                CommandManager.argument("category", StringArgumentType.string()).executes(ProtocolOceanCommand::registerKeyBinding))
                                                )
                                        )
                                )
                        )).then(CommandManager.literal("clear").executes(ProtocolOceanCommand::clear).then(
                                CommandManager.literal("keybindings").executes(ProtocolOceanCommand::clearKeyBindings))
                        )
        );
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
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(context.getArgument("key", String.class));
        buf.writeInt(context.getArgument("code", Integer.class));
        buf.writeString(context.getArgument("category", String.class));
        players.forEach(player -> ServerPlayNetworking.send(player, OceanKeyBinding.IDENTIFIER, buf));
        return 1;
    }
}
