package me.yunetou.mod.modules.impl.misc;


import me.yunetou.api.util.math.Timer;
import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import org.apache.commons.lang3.RandomStringUtils;

public class Message
        extends Module {
    private final Timer timer = new Timer();
    private final Setting<String> custom = this.add(new Setting<>("Custom", "YuneTou owns you lol"));
    private final Setting<Integer> random = this.add(new Setting<>("Random", 0, 0, 20));
    private final Setting<Integer> delay = this.add(new Setting<>("Delay", 100, 0, 10000));

    public Message() {
        super("Spammer", "Message", Category.MISC);
    }

    @Override
    public void onTick() {
        if (!this.timer.passedMs(this.delay.getValue())) {
            return;
        }
        Message.mc.player.connection.sendPacket(new CPacketChatMessage(this.custom.getValue() + " " + RandomStringUtils.randomAlphanumeric(this.random.getValue())));
        this.timer.reset();
    }
}
