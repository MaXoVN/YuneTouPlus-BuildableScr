package me.yunetou.mod.modules.impl.player;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;

public class TpsSync
        extends Module {
    public static TpsSync INSTANCE;
    public final Setting<Boolean> attack = this.add(new Setting<>("Attack", false));
    public final Setting<Boolean> mining = this.add(new Setting<>("Mine", true));

    public TpsSync() {
        super("TpsSync", "Syncs your client with the TPS", Category.PLAYER);
        INSTANCE = this;
    }
}