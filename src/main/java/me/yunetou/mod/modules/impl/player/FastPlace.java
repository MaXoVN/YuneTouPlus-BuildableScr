package me.yunetou.mod.modules.impl.player;


import me.yunetou.api.util.math.Timer;
import me.yunetou.api.util.world.InventoryUtil;
import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.item.ItemExpBottle;

public class FastPlace
        extends Module {
    public final Setting<Boolean> enderChest = this.add(new Setting<>("OnlyEnderChest", true));
    public final Setting<Boolean> exp = this.add(new Setting<>("OnlyEXP", true));
    private final Setting<Integer> delay = this.add(new Setting<>("Delay", 20, 0, 100));
    private final Timer delayTimer = new Timer();

    public FastPlace() {
        super("FastPlace", "Fast projectile", Category.PLAYER);
    }

    @Override
    public void onUpdate() {
        if (FastPlace.fullNullCheck()) {
            return;
        }
        if (this.enderChest.getValue() && !InventoryUtil.holdingItem(BlockEnderChest.class) && !this.exp.getValue()) {
            return;
        }
        if (this.exp.getValue() && !InventoryUtil.holdingItem(ItemExpBottle.class)) {
            return;
        }
        if (this.delayTimer.passedMs(this.delay.getValue())) {
            FastPlace.mc.rightClickDelayTimer = 1;
            this.delayTimer.reset();
        }
    }
}