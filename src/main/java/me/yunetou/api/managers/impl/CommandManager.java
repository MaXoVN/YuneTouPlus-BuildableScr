/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package me.yunetou.api.managers.impl;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.LinkedList;
import me.yunetou.mod.Mod;
import me.yunetou.mod.commands.Command;
import me.yunetou.mod.commands.impl.BindCommand;
import me.yunetou.mod.commands.impl.ConfigCommand;
import me.yunetou.mod.commands.impl.CoordsCommand;
import me.yunetou.mod.commands.impl.FriendCommand;
import me.yunetou.mod.commands.impl.HelpCommand;
import me.yunetou.mod.commands.impl.ModuleCommand;
import me.yunetou.mod.commands.impl.PrefixCommand;
import me.yunetou.mod.commands.impl.ReloadSoundCommand;
import me.yunetou.mod.commands.impl.ShrugCommand;
import me.yunetou.mod.commands.impl.UnloadCommand;
import me.yunetou.mod.commands.impl.WatermarkCommand;

public class CommandManager
extends Mod {
    private final ArrayList<Command> commands = new ArrayList();
    private String clientMessage = "[YuneTou+]";
    private String prefix = ";";

    public CommandManager() {
        super("Command");
        this.commands.add(new BindCommand());
        this.commands.add(new ModuleCommand());
        this.commands.add(new PrefixCommand());
        this.commands.add(new ConfigCommand());
        this.commands.add(new FriendCommand());
        this.commands.add(new HelpCommand());
        this.commands.add(new UnloadCommand());
        this.commands.add(new ReloadSoundCommand());
        this.commands.add(new CoordsCommand());
        this.commands.add(new ShrugCommand());
        this.commands.add(new WatermarkCommand());
    }

    public static String[] removeElement(String[] input, int indexToDelete) {
        LinkedList<String> result = new LinkedList<String>();
        for (int i = 0; i < input.length; ++i) {
            if (i == indexToDelete) continue;
            result.add(input[i]);
        }
        return result.toArray(input);
    }

    private static String strip(String str, String key) {
        if (str.startsWith(key) && str.endsWith(key)) {
            return str.substring(key.length(), str.length() - key.length());
        }
        return str;
    }

    public void executeCommand(String command) {
        String[] parts = command.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        String name = parts[0].substring(1);
        String[] args = CommandManager.removeElement(parts, 0);
        for (int i = 0; i < args.length; ++i) {
            if (args[i] == null) continue;
            args[i] = CommandManager.strip(args[i], "\"");
        }
        for (Command c : this.commands) {
            if (!c.getName().equalsIgnoreCase(name)) continue;
            c.execute(parts);
            return;
        }
        Command.sendMessage((Object)ChatFormatting.GRAY + "Command not found, type 'help' for the commands list.");
    }

    public Command getCommandByName(String name) {
        for (Command command : this.commands) {
            if (!command.getName().equals(name)) continue;
            return command;
        }
        return null;
    }

    public ArrayList<Command> getCommands() {
        return this.commands;
    }

    public String getClientMessage() {
        return this.clientMessage;
    }

    public void setClientMessage(String clientMessage) {
        this.clientMessage = clientMessage;
    }

    public String getCommandPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}

