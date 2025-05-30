/*
 * Decompiled with CFR 0.150.
 */
package me.yunetou.api.managers;

import me.yunetou.api.managers.impl.ColorManager;
import me.yunetou.api.managers.impl.CommandManager;
import me.yunetou.api.managers.impl.ConfigManager;
import me.yunetou.api.managers.impl.EventManager;
import me.yunetou.api.managers.impl.FileManager;
import me.yunetou.api.managers.impl.FpsManager;
import me.yunetou.api.managers.impl.FriendManager;
import me.yunetou.api.managers.impl.InteractionManager;
import me.yunetou.api.managers.impl.ModuleManager;
import me.yunetou.api.managers.impl.PositionManager;
import me.yunetou.api.managers.impl.ReloadManager;
import me.yunetou.api.managers.impl.RotationManager;
import me.yunetou.api.managers.impl.ServerManager;
import me.yunetou.api.managers.impl.SpeedManager;
import me.yunetou.api.managers.impl.TextManager;
import me.yunetou.api.managers.impl.TimerManager;

public class Managers {
    private static boolean loaded = true;
    public static InteractionManager INTERACTIONS;
    public static RotationManager ROTATIONS;
    public static CommandManager COMMANDS;
    public static ModuleManager MODULES;
    public static ConfigManager CONFIGS;
    public static FriendManager FRIENDS;
    public static ColorManager COLORS;
    public static EventManager EVENTS;
    public static FileManager FILES;
    public static PositionManager POSITION;
    public static ReloadManager RELOAD;
    public static ServerManager SERVER;
    public static TimerManager TIMER;
    public static SpeedManager SPEED;
    public static TextManager TEXT;
    public static FpsManager FPS;

    public static void load() {
        loaded = true;
        if (RELOAD != null) {
            RELOAD.unload();
            RELOAD = null;
        }
        EVENTS = new EventManager();
        TEXT = new TextManager();
        INTERACTIONS = new InteractionManager();
        ROTATIONS = new RotationManager();
        POSITION = new PositionManager();
        COMMANDS = new CommandManager();
        CONFIGS = new ConfigManager();
        MODULES = new ModuleManager();
        FRIENDS = new FriendManager();
        SERVER = new ServerManager();
        COLORS = new ColorManager();
        SPEED = new SpeedManager();
        TIMER = new TimerManager();
        FILES = new FileManager();
        FPS = new FpsManager();
        MODULES.init();
        CONFIGS.init();
        EVENTS.init();
        TEXT.init();
        MODULES.onLoad();
    }

    public static void unload(boolean force) {
        if (force) {
            RELOAD = new ReloadManager();
            RELOAD.init(COMMANDS != null ? COMMANDS.getCommandPrefix() : ".");
        }
        Managers.onUnload();
        INTERACTIONS = null;
        ROTATIONS = null;
        POSITION = null;
        COMMANDS = null;
        CONFIGS = null;
        MODULES = null;
        FRIENDS = null;
        SERVER = null;
        COLORS = null;
        EVENTS = null;
        SPEED = null;
        TIMER = null;
        FILES = null;
        TEXT = null;
        FPS = null;
    }

    public static void onUnload() {
        if (Managers.isLoaded()) {
            EVENTS.onUnload();
            MODULES.onUnloadPre();
            CONFIGS.saveConfig(Managers.CONFIGS.config.replaceFirst("YuneTou/", ""));
            MODULES.onUnloadPost();
            loaded = false;
        }
    }

    public static boolean isLoaded() {
        return loaded;
    }
}

