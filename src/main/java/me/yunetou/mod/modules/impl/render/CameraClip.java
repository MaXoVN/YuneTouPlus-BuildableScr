package me.yunetou.mod.modules.impl.render;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;

public class CameraClip
        extends Module {
    public static CameraClip INSTANCE = new CameraClip();
    public final Setting<Double> distance = this.add(new Setting<>("Distance", 4.0, -0.5, 15.0));

    public CameraClip() {
        super("CameraClip", "CameraClip", Category.RENDER);
        INSTANCE = this;
    }
}