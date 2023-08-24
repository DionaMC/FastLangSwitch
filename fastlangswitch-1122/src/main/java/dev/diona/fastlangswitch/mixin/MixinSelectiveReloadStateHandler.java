package dev.diona.fastlangswitch.mixin;

import net.minecraftforge.client.resource.IResourceType;
import net.minecraftforge.client.resource.SelectiveReloadStateHandler;
import net.minecraftforge.client.resource.VanillaResourceType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(value = SelectiveReloadStateHandler.class, remap = false)
public class MixinSelectiveReloadStateHandler {

    @Shadow private @Nullable Predicate<IResourceType> currentPredicate;

    @Inject(method = "get", at = @At("HEAD"), cancellable = true, remap = false)
    private void inj(CallbackInfoReturnable<Predicate<IResourceType>> cir) {
        if (currentPredicate != null && currentPredicate.test(VanillaResourceType.LANGUAGES)) {
            cir.setReturnValue(currentPredicate);
        }
    }
}
