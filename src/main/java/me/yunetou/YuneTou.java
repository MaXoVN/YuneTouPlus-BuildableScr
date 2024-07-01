/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.Util
 *  net.minecraft.util.Util$EnumOS
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.Display
 */
package me.yunetou;

import java.io.InputStream;
import java.nio.ByteBuffer;
import me.yunetou.api.managers.Managers;
import me.yunetou.api.util.render.RenderUtil;
import me.yunetou.mod.gui.screen.Gui;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Util;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid="yunetou", name="YuneTou+", version="v1.0")
public class YuneTou {
    public static final Logger LOGGER = LogManager.getLogger("YuneTou+");
    @Mod.Instance
    public static YuneTou INSTANCE;

    public static void load() {
        LOGGER.info("Loading YuneTou+...");
        Managers.load();
        if (Gui.INSTANCE == null) {
            Gui.INSTANCE = new Gui();
        }
        LOGGER.info("YuneTou+ alpha successfully loaded!\n");
    }

    public static void unload(boolean force) {
        LOGGER.info("Unloading YuneTou+...");
        Managers.unload(force);
        LOGGER.info("YuneTou+ successfully unloaded!\n");
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Display.setTitle("YuneTou+: Loading...");
        if (Util.getOSType() != Util.EnumOS.OSX) {
            try (InputStream inputStream16x = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/memory/constant/icon16x.png");
                 InputStream inputStream32x = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/memory/constant/icon32x.png")){
                ByteBuffer[] icons = new ByteBuffer[]{RenderUtil.readImageToBuffer(inputStream16x), RenderUtil.readImageToBuffer(inputStream32x)};
                Display.setIcon(icons);
            }
            catch (Exception e) {
                LOGGER.error("YuneTou+ alpha couldn't set the window icon!", e);
            }
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        YuneTou.load();
    }
}
