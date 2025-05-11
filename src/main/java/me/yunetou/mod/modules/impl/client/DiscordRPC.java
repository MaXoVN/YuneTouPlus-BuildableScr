package me.yunetou.mod.modules.impl.client;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.yunetou.YuneTou;
import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;

import java.util.Random;

public class DiscordRPC extends Module {
    public Setting<Boolean> showIP;

    public static DiscordRPC INSTANCE;

    private final club.minnced.discord.rpc.DiscordRPC rpc = club.minnced.discord.rpc.DiscordRPC.INSTANCE;

    private final DiscordRichPresence presence = new DiscordRichPresence();

    private Thread thread;

    private final String[] state = {
            "YuneTou On Top",
            "NWuyet is handsome",
            "Crystal pvp",
            "YuneTou+ v1.0",
            "I wonder why you're angry",
            "I want to be a billionaire$$",
            "Loli is so cute",
            "Gaming",
            "Love Femboi",
            "2320080000 MBbank",
            "FB: Nguyen Quyet",
            "Owned By Nguyen Quyet",
            "Ohara Rinne"
    };

    public DiscordRPC() {
        super("DiscordRPC", "Discord rich presence", Category.CLIENT);
        this.showIP = (Setting<Boolean>)this.add(new Setting("ShowIP", true));
        DiscordRPC.INSTANCE = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        start();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        stop();
    }

    @Override
    public void onLoad() {
        super.onLoad();

        if (isOn()) {
            start();
        }
    }

    private void start() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();

        rpc.Discord_Initialize("1370059402681254009", handlers, true, "");

        presence.startTimestamp = System.currentTimeMillis() / 1000L;

        presence.details = ((Minecraft.getMinecraft().currentScreen instanceof GuiMainMenu) ? "In the main menu." : ("Playing " + ((Minecraft.getMinecraft().getCurrentServerData() != null) ? (DiscordRPC.INSTANCE.showIP.getValue() ? ("on " + Minecraft.getMinecraft().getCurrentServerData().serverIP + ".") : " multiplayer.") : " singleplayer.")));

        presence.state = state[new Random().nextInt(state.length)];

        presence.largeImageKey = "rinneflushed";
        presence.largeImageText = "Ohara Rinne" ;

        presence.smallImageKey = "ohararinne";
        presence.smallImageText = "OwO";
        presence.partyId = "YuneTouOnTop";
        presence.partySize = 1;
        presence.partyMax = 2;
        presence.joinSecret = "You got my heart,babe..";

        rpc.Discord_UpdatePresence(presence);

        thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                rpc.Discord_RunCallbacks();
                String string = "";
                StringBuilder sb = new StringBuilder();
                new StringBuilder().append("Playing ");

                if (Minecraft.getMinecraft().getCurrentServerData() != null) {
                    if (DiscordRPC.INSTANCE.showIP.getValue()) {
                        string = "Being cat with friends |  " + Minecraft.getMinecraft().getCurrentServerData().serverIP + ".";
                    } else {
                        string = "Meowing~ with friends - MultiPlayer";
                    }
                }
                else {
                    string = "Enjoying the client - Main menu.";
                }
                presence.details = sb.append(string).toString();
                presence.state = state[new Random().nextInt(state.length)];
                rpc.Discord_UpdatePresence(presence);

                try {
                    Thread.sleep(2000L);

                } catch (InterruptedException ignored) {

                }
            }
        }, "DiscordRPC-Callback-Handler");

        thread.start();
    }

    private void stop() {
        if (thread != null && !thread.isInterrupted()) {
            thread.interrupt();
        }

        rpc.Discord_Shutdown();
    }
}