package dev.linwood.protocolocean.client;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dev.linwood.protocolocean.ProtocolOcean;
import dev.linwood.protocolocean.packet.ProtocolOceanPackets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.fabricmc.fabric.mixin.client.keybinding.KeyBindingAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.minecraft.client.gui.screen.option.ControlsListWidget;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ProtocolOceanClient implements ClientModInitializer {
    private static final Gson GSON = new Gson();
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(ProtocolOceanPackets.KEY_BIND, (client, handler, buf, responseSender) -> {
            assert client.player != null;
            var key = buf.readString();
            var code = buf.readInt();
            var category = buf.readString();
            client.execute(() -> {
                ProtocolOcean.LOGGER.debug("Received key binding {} {} {}", key, code, category);
                KeyBindingHelper.registerKeyBinding(new KeyBinding(key, code, category));
                client.options.load();
            });
        });
    }
}
