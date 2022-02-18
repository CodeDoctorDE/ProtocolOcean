package dev.linwood.protocolocean.packet;

import net.minecraft.util.Identifier;

import java.util.Objects;

public class OceanKeyBinding {
    public static Identifier IDENTIFIER = new Identifier("protocolocean", "key_bind");

    private final String name, category;
    private final int code;

    public OceanKeyBinding(String name, String category, int code) {
        this.name = name;
        this.category = category;
        this.code = code;
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
}
