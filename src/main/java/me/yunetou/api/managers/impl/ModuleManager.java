/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.EventBus
 *  org.lwjgl.input.Keyboard
 */
package me.yunetou.api.managers.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.yunetou.api.events.impl.Render2DEvent;
import me.yunetou.api.events.impl.Render3DEvent;
import me.yunetou.api.managers.Managers;
import me.yunetou.mod.Mod;
import me.yunetou.mod.gui.screen.Gui;
import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.impl.client.*;
import me.yunetou.mod.modules.impl.combat.*;
import me.yunetou.mod.modules.impl.exploit.*;
import me.yunetou.mod.modules.impl.hud.BindList;
import me.yunetou.mod.modules.impl.hud.InventoryPreview;
import me.yunetou.mod.modules.impl.hud.Notifications;
import me.yunetou.mod.modules.impl.hud.TargetHUD;
import me.yunetou.mod.modules.impl.misc.*;
import me.yunetou.mod.modules.impl.movement.*;
import me.yunetou.mod.modules.impl.player.*;
import me.yunetou.mod.modules.impl.render.*;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import org.lwjgl.input.Keyboard;

public class ModuleManager
extends Mod {
    public static Minecraft mc = Minecraft.getMinecraft();
    public ArrayList<Module> modules = new ArrayList();
    public List<Module> sortedLength = new ArrayList<Module>();
    public List<String> sortedAbc = new ArrayList<String>();

    public void init() {
        this.registerModules();
    }

    public void sortModules() {
        this.sortedLength = this.getEnabledModules().stream().filter(Module::isDrawn).sorted(Comparator.comparing(module -> Managers.TEXT.getStringWidth(HUD.INSTANCE.lowerCase.getValue() ? module.getArrayListInfo().toLowerCase() : module.getArrayListInfo()) * -1)).collect(Collectors.toList());
        this.sortedAbc = new java.util.ArrayList<>(this.getEnabledModulesString());
        this.sortedAbc.sort(String.CASE_INSENSITIVE_ORDER);
    }


    public ArrayList<Module> getEnabledModules() {
        ArrayList<Module> modules = new ArrayList<Module>();
        for (Module module : this.modules) {
            if (!module.isOn()) continue;
            modules.add(module);
        }
        return modules;
    }

    public ArrayList<String> getEnabledModulesString() {
        ArrayList<String> modules = new ArrayList<String>();
        for (Module module : this.modules) {
            if (!module.isOn() || !module.isDrawn()) continue;
            modules.add(module.getArrayListInfo());
        }
        return modules;
    }

    public Module getModuleByName(String name) {
        for (Module module : this.modules) {
            if (!module.getName().equalsIgnoreCase(name)) continue;
            return module;
        }
        return null;
    }

    public ArrayList<Module> getModulesByCategory(Category category) {
        ArrayList<Module> modules = new ArrayList<Module>();
        this.modules.forEach(module -> {
            if (module.getCategory() == category) {
                modules.add((Module)module);
            }
        });
        return modules;
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public List<Category> getCategories() {
        return Arrays.asList(Category.values());
    }

    public void onUnloadPre() {
        this.modules.forEach(((EventBus)MinecraftForge.EVENT_BUS)::unregister);
        this.modules.forEach(Module::onUnload);
    }

    public void onUnloadPost() {
        for (Module module : this.modules) {
            module.enabled.setValue(false);
        }
    }

    public void onKeyInput(int key) {
        if (key == 0 || !Keyboard.getEventKeyState() || ModuleManager.mc.currentScreen instanceof Gui) {
            return;
        }
        this.modules.forEach(module -> {
            if (module.getBind().getKey() == key) {
                module.toggle();
            }
        });
    }

    public void onLoad() {
        this.modules.stream().filter(Module::isListening).forEach(((EventBus)MinecraftForge.EVENT_BUS)::register);
        this.modules.forEach(Module::onLoad);
    }

    public void onUpdate() {
        this.modules.stream().filter(Module::isOn).forEach(Module::onUpdate);
    }

    public void onTick() {
        this.modules.stream().filter(Module::isOn).forEach(Module::onTick);
    }

    public void onRender2D(Render2DEvent event) {
        this.modules.stream().filter(Module::isOn).forEach(module -> module.onRender2D(event));
    }

    public void onRender3D(Render3DEvent event) {
        this.modules.stream().filter(Module::isOn).forEach(module -> module.onRender3D(event));
    }

    public void onTotemPop(EntityPlayer player) {
        this.modules.stream().filter(Module::isOn).forEach(module -> module.onTotemPop(player));
    }

    public void onDeath(EntityPlayer player) {
        this.modules.stream().filter(Module::isOn).forEach(module -> module.onDeath(player));
    }

    public void onLogout() {
        this.modules.forEach(Module::onLogout);
    }

    public void onLogin() {
        this.modules.forEach(Module::onLogin);
    }

    private void registerModules() {
        //CLIENT
        this.modules.add(new Appearance());
        this.modules.add(new ArrayListPlus());
        this.modules.add(new ClickGui());
        this.modules.add(new Desktop());
        this.modules.add(new DiscordRPC());
        this.modules.add(new FakeFPS());
        this.modules.add(new FontMod());
        this.modules.add(new FovMod());
        this.modules.add(new GuiAnimation());
        this.modules.add(new HUD());
        this.modules.add(new Title());
        //COMBAT
        this.modules.add(new AntiBurrow());
        this.modules.add(new AntiCity());
        this.modules.add(new AntiPiston());
        this.modules.add(new AntiRegear());
        this.modules.add(new AntiWeak());
        this.modules.add(new AnvilAura());
        this.modules.add(new Aura());
        this.modules.add(new AutoPush());
        this.modules.add(new AutoReplenish());
        this.modules.add(new AutoTotem());
        this.modules.add(new AutoTrap());
        this.modules.add(new AutoArmor());
        this.modules.add(new Blocker());
        this.modules.add(new Burrow());
        this.modules.add(new CombatSetting());
        this.modules.add(new CityRecode());
        this.modules.add(new AutoCity());
        this.modules.add(new Criticals());
        this.modules.add(new PacketExp());
        this.modules.add(new PacketMine());
        this.modules.add(new PullCrystal());
        this.modules.add(new Surround());
        this.modules.add(new AutoWeb());
        this.modules.add(new AutoWire());
        this.modules.add(new CatCrystal());
        this.modules.add(new CrystalBot());
        this.modules.add(new Filler());
        this.modules.add(new HoleFiller());
        this.modules.add(new ObiPlacer());
        this.modules.add(new PistonCrystal());
        this.modules.add(new SelfWeb());
        this.modules.add(new TestPush());
        this.modules.add(new TrapSelf());
        this.modules.add(new WebTrap());



        //EXPLOIT
        this.modules.add(new BetterPortal());
        this.modules.add(new Blink());
        this.modules.add(new Clip());
        this.modules.add(new Crasher());
        this.modules.add(new FakePearl());
        this.modules.add(new PacketFly());
        this.modules.add(new PearlSpoof());
        this.modules.add(new Phase());
        this.modules.add(new Stresser());
        this.modules.add(new SuperBow());
        this.modules.add(new SuperThrow());
        this.modules.add(new TPCoordLog());
        this.modules.add(new XCarry());
        //HUD
        this.modules.add(new BindList());
        this.modules.add(new InventoryPreview());
        this.modules.add(new Notifications());
        this.modules.add(new TargetHUD());
        //MISC
        this.modules.add(new AntiNullPointer());
        this.modules.add(new AntiSpam());
        this.modules.add(new AutoEZ());
        this.modules.add(new AutoFrameDupe());
        this.modules.add(new AutoKit());
        this.modules.add(new AutoLogin());
        this.modules.add(new AutoReconnect());
        this.modules.add(new AutoTNT());
        this.modules.add(new BetterChat());
        this.modules.add(new Coords());
        this.modules.add(new Debug());
        this.modules.add(new ExtraTab());
        this.modules.add(new GhastNotifier());
        this.modules.add(new KillEffects());
        this.modules.add(new LightningDetect());
        this.modules.add(new MCF());
        this.modules.add(new Message());
        this.modules.add(new PearlNotify());
        this.modules.add(new Peek());
        this.modules.add(new PopCounter());
        this.modules.add(new SilentDisconnect());
        this.modules.add(new TabFriends());
        this.modules.add(new TNTTime());
        this.modules.add(new ToolTips());
        this.modules.add(new UnfocusedCPU());
        //MOVEMENT
        this.modules.add(new AntiGlide());
        this.modules.add(new AntiVoid());
        this.modules.add(new AntiWeb());
        this.modules.add(new AutoCenter());
        this.modules.add(new AutoWalk());
        this.modules.add(new ElytraFly());
        this.modules.add(new FastFall());
        this.modules.add(new FastSwim());
        this.modules.add(new FastWeb());
        this.modules.add(new Flight());
        this.modules.add(new HoleSnap());
        this.modules.add(new InventoryMove());
        this.modules.add(new LongJump());
        this.modules.add(new NewStep());
        this.modules.add(new NoJumpDelay());
        this.modules.add(new NoSlowDown());
        this.modules.add(new SafeWalk());
        this.modules.add(new Scaffold());
        this.modules.add(new Speed());
        this.modules.add(new Sprint());
        this.modules.add(new Step());
        this.modules.add(new Strafe());
        this.modules.add(new TargetStrafe());
        this.modules.add(new Velocity());

        //PLAYER
        this.modules.add(new Announcer());
        this.modules.add(new AntiAim());
        this.modules.add(new AntiOpen());
        this.modules.add(new ArmorWarner());
        this.modules.add(new AutoFish());
        this.modules.add(new AutoFuck());
        this.modules.add(new AutoRespawn());
        this.modules.add(new BlockTweaks());
        this.modules.add(new FakePlayer());
        this.modules.add(new FastPlace());
        this.modules.add(new FlagDetect());
        this.modules.add(new Freecam());
        this.modules.add(new FreeLook());
        this.modules.add(new KeyPearl());
        this.modules.add(new NameProtect());
        this.modules.add(new NoFall());
        this.modules.add(new NoRotate());
        this.modules.add(new PacketEat());
        this.modules.add(new Replenish());
        this.modules.add(new SpeedMine());
        this.modules.add(new TimerModule());
        this.modules.add(new TpsSync());
        //RENDER
        this.modules.add(new Ambience());
        this.modules.add(new Animations());
        this.modules.add(new AutoEsu());
        this.modules.add(new BreadCrumbs());
        this.modules.add(new BreakESP());
        this.modules.add(new BreakingESP());
        this.modules.add(new BurrowESP());
        this.modules.add(new Chams());
        this.modules.add(new ChinaHat());
        this.modules.add(new CityRender());
        this.modules.add(new CrystalChams());
        this.modules.add(new CrystalSpawns());
        this.modules.add(new DMGParticles());
        this.modules.add(new EarthPopChams());
        this.modules.add(new EntityCircle());
        this.modules.add(new ESP());
        this.modules.add(new ESP2D());
        this.modules.add(new ExplosionSpawn());
        this.modules.add(new GlintModify());
        this.modules.add(new Highlight());
        this.modules.add(new HoleESP());
        this.modules.add(new ItemModel());
        this.modules.add(new ItemPhysics());
        this.modules.add(new LogOutSpots());
        this.modules.add(new Models());
        this.modules.add(new NameTags());
        this.modules.add(new NoLag());
        this.modules.add(new Particles());
        this.modules.add(new PenisESP());
        this.modules.add(new PlaceRender());
        this.modules.add(new PopChams());
        this.modules.add(new PortalESP());
        this.modules.add(new RenderSetting());
        this.modules.add(new Rotations());
        this.modules.add(new Shader());
        this.modules.add(new ShaderChams());
        this.modules.add(new Shaders());
        this.modules.add(new TileESP());
        this.modules.add(new Tracers());
        this.modules.add(new Trajectories());
        this.modules.add(new VoidESP());
    }

    public static enum Ordering {
        ABC,
        LENGTH;

    }
}

