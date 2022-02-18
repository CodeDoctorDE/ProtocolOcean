package dev.linwood.protocolocean.mixin;

import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(value = KeyBindingRegistryImpl.class, remap = false)
public interface KeyBindingRegistryImplAccessor {
    @Accessor("moddedKeyBindings")
    static List<KeyBinding> getModdedKeyBindings() {
        throw new AssertionError();
    }
}
