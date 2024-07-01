/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package me.yunetou.mod.modules.impl.client;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import net.minecraft.client.gui.GuiScreen;

public class Appearance
        extends Module {
    public Appearance() {
        super("HUDEditor", "Drag HUD elements all over your screen", Category.CLIENT);
    }

    @Override
    public void onEnable() {
        mc.displayGuiScreen(me.yunetou.mod.gui.screen.Appearance.getClickGui());
    }

    @Override
    public void onTick() {
        if (!(Appearance.mc.currentScreen instanceof me.yunetou.mod.gui.screen.Appearance)) {
            this.disable();
        }
    }
}
