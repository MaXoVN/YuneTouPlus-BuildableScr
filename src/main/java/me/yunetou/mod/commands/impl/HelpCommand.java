/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package me.yunetou.mod.commands.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.yunetou.api.managers.Managers;
import me.yunetou.mod.commands.Command;

public class HelpCommand
extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(String[] commands) {
        HelpCommand.sendMessage("Commands: ");
        for (Command command : Managers.COMMANDS.getCommands()) {
            HelpCommand.sendMessage((Object)ChatFormatting.GRAY + Managers.COMMANDS.getCommandPrefix() + command.getName());
        }
    }
}

