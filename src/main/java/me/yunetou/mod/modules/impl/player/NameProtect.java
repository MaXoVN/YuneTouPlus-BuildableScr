/*
 * Decompiled with CFR 0.150.
 */
package me.yunetou.mod.modules.impl.player;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;

public class NameProtect
extends Module {
    public static NameProtect INSTANCE;
    public final Setting<String> name = this.add(new Setting<String>("Name", "Me"));

    public NameProtect() {
        super("NameProtect", "To keep your alts in secret.", Category.PLAYER);
        INSTANCE = this;
    }
}

