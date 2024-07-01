/*
 * Decompiled with CFR 0.150.
 */
package me.yunetou.mod.commands.impl;

import me.yunetou.YuneTou;
import me.yunetou.mod.commands.Command;

public class UnloadCommand
extends Command {
    public UnloadCommand() {
        super("unload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        YuneTou.unload(true);
    }
}

