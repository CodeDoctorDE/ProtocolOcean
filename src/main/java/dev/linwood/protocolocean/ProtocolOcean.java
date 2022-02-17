package dev.linwood.protocolocean;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class ProtocolOcean implements ModInitializer {
    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("protocolocean")
                    .executes(context -> {
                        context.getSource().sendFeedback(Text.of("Test command"), false);
                return 1;
            }));
        });
    }
}
