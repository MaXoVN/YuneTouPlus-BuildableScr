package me.yunetou.mod.modules.impl.movement;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;

public class InventoryMove
        extends Module {
    public static InventoryMove INSTANCE = new InventoryMove();
    public final Setting<Boolean> sneak = this.add(new Setting<>("Sneak", false));

    public InventoryMove() {
        super("InvMove", "Allow walking on the interface", Category.MOVEMENT);
        INSTANCE = this;
    }
}