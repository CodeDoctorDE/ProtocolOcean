package dev.linwood.protocolocean.feature;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class OceanKeyBindFeature extends AdvancedOceanFeature {
    private final List<OceanKeyBind> keyBinds = new ArrayList<>();

    @Override
    void unload() {

    }

    @Override
    void load() {

    }

    @Override
    public OceanFeatureChange[] getChanges() {
        return new OceanFeatureChange[0];
    }

    @Override
    public Identifier[] getIds() {
        return new Identifier[0];
    }

    private record OceanKeyBind(Identifier id, int keyCode, String category) {
        public void write(PacketByteBuf buf) {
            buf.writeIdentifier(id);
            buf.writeInt(keyCode);
            buf.writeInt(keyModifiers);
        }
    }

    private class OceanKeyBindAdd extends OceanFeatureChange {

        private OceanKeyBindAdd() {
            super(type, value);
        }

        @Override
        public Identifier getId() {
            return null;
        }

        @Override
        public PacketByteBuf getPacket() {
            return null;
        }

        @Override
        void apply(OceanFeature feature) {

        }
    }
}
