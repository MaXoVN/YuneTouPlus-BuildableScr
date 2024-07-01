/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package me.yunetou.mod.commands.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.yunetou.mod.commands.Command;
import me.yunetou.mod.modules.impl.client.FontMod;
import me.yunetou.mod.modules.impl.client.HUD;

public class WatermarkCommand
extends Command {
    public WatermarkCommand() {
        super("watermark", new String[]{"<watermark>"});
    }

    @Override
    public void execute(String[] commands) {
        if (commands.length == 2) {
            FontMod fontMod = FontMod.INSTANCE;
            boolean customFont = fontMod.isOn();
            if (commands[0] != null) {
                if (customFont) {
                    fontMod.disable();
                }
                HUD.INSTANCE.watermarkString.setValue(commands[0]);
                if (customFont) {
                    fontMod.enable();
                }
                WatermarkCommand.sendMessage("Watermark set to " + (Object)ChatFormatting.GREEN + commands[0]);
            } else {
                WatermarkCommand.sendMessage("Not a valid command... Possible usage: <New Watermark>");
            }
        }
    }
}

