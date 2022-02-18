package dev.linwood.protocolocean.mixin;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GameOptions.class)
public interface GameOptionsAccessor {
    @Accessor("allKeys")
    KeyBinding[] getAllKeys();

    @Accessor("allKeys")
    @Mutable
    void setAllKeys(KeyBinding[] keys);
}
