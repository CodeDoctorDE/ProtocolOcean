package dev.linwood.protocolocean.mixin;

import dev.linwood.protocolocean.ProtocolOcean;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class ProtocolOceanInfoMixim {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/Screen;init(Lnet/minecraft/client/MinecraftClient;II)V"), method = "init()V")
    private void init(CallbackInfo info) {
        ProtocolOcean.LOGGER.info("You are using ProtocolOcean!");
    }
}