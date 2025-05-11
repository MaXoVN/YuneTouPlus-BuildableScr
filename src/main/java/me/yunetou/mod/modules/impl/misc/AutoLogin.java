package me.yunetou.mod.modules.impl.misc;

import me.yunetou.api.util.math.Timer;
import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;

public class AutoLogin
        extends Module {
    final Timer timer = new Timer();
    private final Setting<String> password = this.add(new Setting<>("password", "password"));
    boolean needLogin = false;

    public AutoLogin() {
        super("AutoLogin", "Auto login", Category.MISC);
    }

    @Override
    public void onTick() {
        if (this.needLogin && this.timer.passedMs(1000L)) {
            this.needLogin = false;
            AutoLogin.mc.player.connection.sendPacket(new CPacketChatMessage("/login " + this.password.getValue()));
        }
    }

    @Override
    public void onLogin() {
        this.needLogin = true;
        this.timer.reset();
    }
}
