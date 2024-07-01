package me.yunetou.mod.modules.impl.render;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;

public class RenderSetting
        extends Module {
    public static RenderSetting INSTANCE;
    public final Setting<Float> outlineWidth = this.add(new Setting<>("OutlineWidth", 1.0f, 0.1f, 4.0f));

    public RenderSetting() {
        super("RenderSetting", "idk", Category.RENDER);
        INSTANCE = this;
    }
}
