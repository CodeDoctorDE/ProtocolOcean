package dev.linwood.protocolocean.packet;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public enum OceanPacketType {
    KEY_BIND(new Identifier("protocolocean", "key_bind")),
    KEY_UNBIND(new Identifier("protocolocean", "key_unbind")),
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
            case KEY_UNBIND -> new OceanKeyUnbinding(buf);
            case FILTER -> new OceanFilter(buf);
        };
    }
}
