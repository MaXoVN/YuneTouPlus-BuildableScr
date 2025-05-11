package me.yunetou.mod.modules.impl.misc;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;

public class ExtraTab
        extends Module {
    public static ExtraTab INSTANCE = new ExtraTab();
    public final Setting<Integer> size = this.add(new Setting<>("Size", 250, 1, 1000));

    public ExtraTab() {
        super("ExtraTab", "Extends Tab", Category.MISC);
        INSTANCE = this;
    }
}
