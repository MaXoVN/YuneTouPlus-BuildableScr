package me.yunetou.mod.modules.impl.render;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;

import java.awt.*;

public class GlintModify
        extends Module {
    public static GlintModify INSTANCE = new GlintModify();
    public final Setting<Color> color = this.add(new Setting<>("Color", new Color(-557395713, true)).hideAlpha());

    public GlintModify() {
        super("GlintModify", "Changes the enchant glint color", Category.RENDER);
        INSTANCE = this;
    }

    public static Color getColor() {
        return GlintModify.INSTANCE.color.getValue();
    }
}