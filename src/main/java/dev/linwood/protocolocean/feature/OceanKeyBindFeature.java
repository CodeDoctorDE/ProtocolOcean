package dev.linwood.protocolocean.feature;

import dev.linwood.protocolocean.ProtocolOcean;
import dev.linwood.protocolocean.mixin.GameOptionsAccessor;
import dev.linwood.protocolocean.mixin.KeyBindingRegistryImplAccessor;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OceanKeyBindFeature extends OceanFeature {
    private static final Identifier ADD_ID = new Identifier("protocolocean", "add_keybind");
    private static final Identifier REMOVE_ID = new Identifier("protocolocean", "remove_keybind");
    private static final Identifier CLEAR_ID = new Identifier("protocolocean", "clear_keybinds");
    private final List<OceanKeyBind> keyBinds = new ArrayList<>();

    @Override
    void unload(boolean isClient) {
        clear();
        applyChanges(isClient);
    }

    @Override
    void load(boolean isClient) {

    }

    @Override
    public void handle(OceanPacket packet, boolean isClient) {
        if (ADD_ID.equals(packet.getId())) {
            var buf = packet.getPacket();
            var keyBind = new OceanKeyBind(buf.readString(), buf.readInt(), buf.readString());
            keyBinds.add(keyBind);
            if (isClient) {
                ProtocolOcean.LOGGER.debug("Received key binding {} {} {}", keyBind.key, keyBind.keyCode, keyBind.category);
                KeyBindingHelper.registerKeyBinding(new KeyBinding(keyBind.key, keyBind.keyCode, keyBind.category));
                MinecraftClient.getInstance().options.load();
            }
        } else if (REMOVE_ID.equals(packet.getId())) {
            var buf = packet.getPacket();
            var key = buf.readString();
            for (var keyBind : keyBinds) {
                if (key.equals(keyBind.key)) {
                    keyBinds.remove(keyBind);
                    ProtocolOcean.LOGGER.debug("Received key unbinding {}", key);
                    KeyBindingRegistryImplAccessor.getModdedKeyBindings().removeIf(current -> current.getTranslationKey().equals(key));
                    var options = ((GameOptionsAccessor) MinecraftClient.getInstance().options);
                    var keyBindings = Arrays.stream(options.getAllKeys()).filter(keyBinding -> !keyBinding.getTranslationKey().equals(key)).toArray(KeyBinding[]::new);
                    options.setAllKeys(keyBindings);
                    MinecraftClient.getInstance().options.load();
                    break;
                }
            }
        } else if (CLEAR_ID.equals(packet.getId())) {
            keyBinds.clear();
        }
    }

    @Override
    public Identifier[] getIds() {
        return new Identifier[]{ADD_ID, REMOVE_ID, CLEAR_ID};
    }

    public OceanKeyBind add(String key, int keyCode, String category) {
        var keyBind = new OceanKeyBind(key, keyCode, category);
        addChange(new OceanPacket(ADD_ID, keyBind.getPacket()));
        return keyBind;
    }

    public void remove(String key) {
        var buf = PacketByteBufs.create();
        buf.writeString(key);
        addChange(new OceanPacket(REMOVE_ID, buf));
    }

    public void clear() {
        addChange(new OceanPacket(CLEAR_ID, PacketByteBufs.empty()));
    }

    private record OceanKeyBind(String key, int keyCode, String category) {
        public PacketByteBuf getPacket() {
            var buf = PacketByteBufs.create();
            buf.writeString(key);
            buf.writeInt(keyCode);
            buf.writeString(category);
            return buf;
        }
    }
}
