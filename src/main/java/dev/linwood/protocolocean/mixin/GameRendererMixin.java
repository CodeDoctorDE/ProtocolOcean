package dev.linwood.protocolocean.mixin;

import dev.linwood.protocolocean.client.ProtocolOceanClient;
import dev.linwood.protocolocean.feature.OceanFilterFeature;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Shadow
    protected abstract void loadShader(Identifier identifier);

    @Inject(method = "onCameraEntitySet", at = @At("RETURN"))
    public void onCameraEntitySet(Entity entity, CallbackInfo ci) {
        var feature = ProtocolOceanClient.REGISTRY.getFeature(OceanFilterFeature.class);
        this.loadShader(new Identifier(feature.getFilter()));
    }
}
