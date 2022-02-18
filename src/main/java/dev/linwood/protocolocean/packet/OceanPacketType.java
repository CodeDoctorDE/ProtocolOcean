package dev.linwood.protocolocean.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public enum OceanPacketType {
    KEY_BIND(new Identifier("protocolocean", "key_bind")),
    FILTER(new Identifier("protocolocean", "filter"));

    private final Identifier identifier;

    OceanPacketType(Identifier identifier) {
        this.identifier = identifier;
    }

    public static Identifier getIdentifier(OceanPacketType type) {
        return type.getIdentifier();
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public OceanPacket create(PacketByteBuf buf) {
        return switch (this) {
            case KEY_BIND -> new OceanKeyBinding(buf);
            case FILTER -> new OceanFilter(buf);
        };
    }
}
