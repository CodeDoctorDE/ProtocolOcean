package dev.linwood.protocolocean.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SoundOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SoundOptionsScreen.class)
public abstract class ProtocolOceanSoundOptionScreenMixim extends GameOptionsScreen {

    private ProtocolOceanSoundOptionScreenMixim(Screen parent, GameOptions gameOptions, Text title) {
        super(parent, gameOptions, title);
    }

    @ModifyVariable(at = @At(value = "FIELD", target = "Lnet/minecraft/client/option/Option;AUDIO_DEVICE:Lnet/minecraft/client/option/CyclingOption;", shift = At.Shift.BEFORE),
            method = "init",
            index = 3)
    private int modifyOptions(int in) {
        return in + 10;
    }

}