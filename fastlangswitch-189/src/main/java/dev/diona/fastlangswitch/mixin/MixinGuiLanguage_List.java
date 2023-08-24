package dev.diona.fastlangswitch.mixin;

import dev.diona.fastlangswitch.OptifineCompatible;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.client.gui.GuiLanguage$List")
public class MixinGuiLanguage_List {

    @Redirect(
            method = "elementClicked",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/Minecraft;refreshResources()V"
            )
    )
    private void refresh(Minecraft instance) {
        instance.getLanguageManager().onResourceManagerReload(instance.getResourceManager());
        OptifineCompatible.callOptifineReload();
    }
}
