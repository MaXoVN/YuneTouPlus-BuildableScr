/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package me.yunetou.asm.mixins;

import me.yunetou.api.util.Wrapper;
import me.yunetou.mod.modules.impl.movement.NoJumpDelay;
import me.yunetou.mod.modules.impl.render.Animations;
import me.yunetou.mod.modules.impl.render.ItemModel;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EntityLivingBase.class})
public class MixinEntityLivingBase
        implements Wrapper {
    @Shadow
    private int jumpTicks;

    @Inject(method = {"onLivingUpdate"}, at = {@At(value = "HEAD")})
    private void headLiving(CallbackInfo callbackInfo) {
        if (NoJumpDelay.INSTANCE.isOn()) {
            this.jumpTicks = 0;
        }
    }

    @Inject(method = {"getArmSwingAnimationEnd"}, at = {@At(value = "HEAD")}, cancellable = true)
    private void getArmSwingAnimationEnd(CallbackInfoReturnable<Integer> info) {
        ItemModel mod = ItemModel.INSTANCE;
        if (mod.isOn() && mod.customSwing.getValue() && mod.swing.getValue() == ItemModel.Swing.SERVER) {
            info.setReturnValue(-1);
        } else if (mod.isOn() && mod.slowSwing.getValue()) {
            info.setReturnValue(mod.swingSpeed.getValue());
        }
        if (Animations.INSTANCE.isOn() && Animations.changeSwing.getValue().booleanValue()) {
            info.setReturnValue((int) Animations.swingDelay.getValue());
        }
    }
}

