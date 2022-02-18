package dev.linwood.protocolocean.packet;

import dev.linwood.protocolocean.ProtocolOcean;
import dev.linwood.protocolocean.mixin.GameOptionsAccessor;
import dev.linwood.protocolocean.mixin.KeyBindingRegistryImplAccessor;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.network.PacketByteBuf;

import java.util.Arrays;

public final class OceanKeyUnbinding extends OceanPacket {
    private final String name;

    public OceanKeyUnbinding(String name) {
        this.name = name;
    }

    public OceanKeyUnbinding(PacketByteBuf buf) {
        this(buf.readString());
    }

    public String getName() {
        return name;
    }

    @Override
    public PacketByteBuf createPacket() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(name);
        return buf;
    }

    @Override
    public OceanPacketType getType() {
        return OceanPacketType.KEY_UNBIND;
    }

    @Override
    public void apply(OceanRegistry registry) {
        ProtocolOcean.LOGGER.debug("Received key unbinding {}", name);
        KeyBindingRegistryImplAccessor.getModdedKeyBindings().removeIf(current -> current.getTranslationKey().equals(name));
        var options = ((GameOptionsAccessor) MinecraftClient.getInstance().options);
        var keyBindings = Arrays.stream(options.getAllKeys()).filter(keyBinding -> !keyBinding.getTranslationKey().equals(name)).toArray(KeyBinding[]::new);
        options.setAllKeys(keyBindings);
        MinecraftClient.getInstance().options.load();
        registry.unregisterKeyBinding(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OceanKeyUnbinding that = (OceanKeyUnbinding) o;
        return getName().equals(that.getName());
    }
}
