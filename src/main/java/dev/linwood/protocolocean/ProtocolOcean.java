package dev.linwood.protocolocean;

import com.google.gson.Gson;
import dev.linwood.protocolocean.command.ProtocolOceanCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtocolOcean implements ModInitializer {
    private static final Gson GSON = new Gson();
    public static final Logger LOGGER = LoggerFactory.getLogger("protocolocean");

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> ProtocolOceanCommand.register(dispatcher));
        LOGGER.info("ProtocolOcean loaded");
    }
}
