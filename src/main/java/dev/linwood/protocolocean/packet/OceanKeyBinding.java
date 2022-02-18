package dev.linwood.protocolocean.packet;

import dev.linwood.protocolocean.ProtocolOcean;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.network.PacketByteBuf;

import java.util.Objects;

public final class OceanKeyBinding extends OceanPacket {
    private final String name;
    private final String category;
    private final int code;

    public OceanKeyBinding(String name, int code, String category) {
        this.name = name;
        this.category = category;
        this.code = code;
    }

    public OceanKeyBinding(PacketByteBuf buf) {
        this(buf.readString(), buf.readInt(), buf.readString());
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getCode() {
        return code;
    }

    @Override
    public PacketByteBuf createPacket() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeString(name);
        buf.writeInt(code);
        buf.writeString(category);
        return buf;
    }

    @Override
    public OceanPacketType getType() {
        return OceanPacketType.KEY_BIND;
    }

    @Override
    public void apply() {
        ProtocolOcean.LOGGER.debug("Received key binding {} {} {}", name, code, category);
        KeyBindingHelper.registerKeyBinding(new KeyBinding(name, code, category));
        MinecraftClient.getInstance().options.load();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OceanKeyBinding that = (OceanKeyBinding) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public String name() {
        return name;
    }

    public String category() {
        return category;
    }

    public int code() {
        return code;
    }

    @Override
    public String toString() {
        return "OceanKeyBinding[" +
                "name=" + name + ", " +
                "category=" + category + ", " +
                "code=" + code + ']';
    }

}
