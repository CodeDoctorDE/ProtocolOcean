package dev.linwood.protocolocean.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class ProtocolOceanHUDMixin {

    @Shadow private int scaledWidth;

    @Shadow private int scaledHeight;

    @Shadow public abstract TextRenderer getTextRenderer();

    @Inject(method = "render", at = @At("RETURN"))
    public void onRender (MatrixStack matrices, float tickDelta, CallbackInfo info) {
        getTextRenderer().draw(matrices, "Powered by ProtocolOcean", scaledWidth - 150, scaledHeight - 10, -1);

    }

}
