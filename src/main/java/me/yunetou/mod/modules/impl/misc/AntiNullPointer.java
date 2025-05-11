package me.yunetou.mod.modules.impl.misc;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;

public class AntiNullPointer
        extends Module {
    public static AntiNullPointer INSTANCE = new AntiNullPointer();
    public final Setting<Boolean> debug = this.add(new Setting<>("Debug", true));

    public AntiNullPointer() {
        super("AntiNull", "anti null pointer kick", Category.MISC);
        INSTANCE = this;
    }

    public void sendWarning(Throwable Throwable2) {
        if (this.debug.getValue()) {
            this.sendMessage("Patch null point kick!");
        }
        Throwable2.printStackTrace();
    }
}
