package me.yunetou.mod.modules.impl.misc;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;

public class SilentDisconnect
        extends Module {
    public static SilentDisconnect INSTANCE = new SilentDisconnect();

    public SilentDisconnect() {
        super("SilentDisconnect", "Silent disconnect", Category.MISC);
        INSTANCE = this;
    }
}