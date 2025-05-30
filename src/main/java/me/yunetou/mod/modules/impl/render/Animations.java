package me.yunetou.mod.modules.impl.render;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;
import net.minecraft.entity.player.EntityPlayer;

public class Animations
        extends Module {
    public static Animations INSTANCE;
    public static Setting<AnimationVersion> swingAnimationVersion;
    public static Setting<Boolean> playersDisableAnimations;
    public static Setting<Boolean> changeMainhand;
    public static Setting<Float> mainhand;
    public static Setting<Boolean> changeOffhand;
    public static Setting<Float> offhand;
    public static Setting<Boolean> changeSwing;
    public static Setting<Integer> swingDelay;

    public Animations() {
        super("Animations", "Allows you to change animations in your hand", Category.RENDER);
        swingAnimationVersion = this.add(new Setting<AnimationVersion>("Version", AnimationVersion.OneDotEight));
        playersDisableAnimations = this.add(new Setting<Boolean>("Disable Animations", false));
        changeMainhand = this.add(new Setting<Boolean>("Change Mainhand", true));
        mainhand = this.add(new Setting<Float>("Mainhand", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(4.7509747f) ^ 0x7F1807FC)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.63819E38f) ^ 0x7EF67CC9)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(30.789412f) ^ 0x7E7650B7))));
        changeOffhand = this.add(new Setting<Boolean>("Change Offhand", true));
        offhand = this.add(new Setting<Float>("Offhand", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(15.8065405f) ^ 0x7EFCE797)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(3.3688825E38f) ^ 0x7F7D7251)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(7.3325067f) ^ 0x7F6AA3E5))));
        changeSwing = this.add(new Setting<Boolean>("Swing Speed", false));
        swingDelay = this.add(new Setting<Integer>("Swing Delay", 6, 1, 20));
        INSTANCE = this;
    }

    @Override
    public void onUpdate() {
        if (playersDisableAnimations.getValue().booleanValue()) {
            for (EntityPlayer player : Animations.mc.world.playerEntities) {
                player.limbSwing = Float.intBitsToFloat(Float.floatToIntBits(1.8755627E38f) ^ 0x7F0D1A06);
                player.limbSwingAmount = Float.intBitsToFloat(Float.floatToIntBits(6.103741E37f) ^ 0x7E37AD83);
                player.prevLimbSwingAmount = Float.intBitsToFloat(Float.floatToIntBits(4.8253957E37f) ^ 0x7E11357F);
            }
        }
        if (changeMainhand.getValue().booleanValue() && Animations.mc.entityRenderer.itemRenderer.equippedProgressMainHand != mainhand.getValue().floatValue()) {
            Animations.mc.entityRenderer.itemRenderer.equippedProgressMainHand = mainhand.getValue().floatValue();
            Animations.mc.entityRenderer.itemRenderer.itemStackMainHand = Animations.mc.player.getHeldItemMainhand();
        }
        if (changeOffhand.getValue().booleanValue() && Animations.mc.entityRenderer.itemRenderer.equippedProgressOffHand != offhand.getValue().floatValue()) {
            Animations.mc.entityRenderer.itemRenderer.equippedProgressOffHand = offhand.getValue().floatValue();
            Animations.mc.entityRenderer.itemRenderer.itemStackOffHand = Animations.mc.player.getHeldItemOffhand();
        }
        if (swingAnimationVersion.getValue() == AnimationVersion.OneDotEight && (double)Animations.mc.entityRenderer.itemRenderer.prevEquippedProgressMainHand >= 0.9) {
            Animations.mc.entityRenderer.itemRenderer.equippedProgressMainHand = 1.0f;
            Animations.mc.entityRenderer.itemRenderer.itemStackMainHand = Animations.mc.player.getHeldItemMainhand();
        }
    }

    public static enum AnimationVersion {
        OneDotEight,
        OneDotTwelve;

    }
}
