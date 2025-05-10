package me.yunetou.mod.modules.impl.misc;

import me.yunetou.api.util.Util;
import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShulkerBox;
import net.minecraft.util.EnumHand;

public class AutoFrameDupe extends Module {
    private final Setting<Boolean> shulkersonly  = this.add(new Setting<Boolean>("ShulkersOnly", true));
    private final Setting<Integer> range  = this.add(new Setting<Integer>("Range", 5, 0, 6));
    private final Setting<Integer> turns  = this.add(new Setting<Integer>("Turns", 1, 0, 5));
    private final Setting<Integer> ticks  = this.add(new Setting<Integer>("Ticks", 10, 1, 20));
    private int timeoutTicks = 0;

    public AutoFrameDupe() {
        super("AutoFrameDupe", "For 6b6t.org and 8b8t.me and more!", Category.MISC);
    }

    @Override
    public void onUpdate() {
        if (Util.mc.player != null && Util.mc.world != null) {
            if (shulkersonly.getValue()) {
                int shulkerSlot = getShulkerSlot();
                if (shulkerSlot != -1) {
                    Util.mc.player.inventory.currentItem = shulkerSlot;
                }
            }
            for (Entity frame : Util.mc.world.loadedEntityList) {
                if (frame instanceof EntityItemFrame) {
                    if (Util.mc.player.getDistance(frame) <= range.getValue()) {
                        if (timeoutTicks >= ticks.getValue()) {
                            if (((EntityItemFrame) frame).getDisplayedItem().getItem() == Items.AIR && !Util.mc.player.getHeldItemMainhand().isEmpty) {
                                Util.mc.playerController.interactWithEntity(Util.mc.player, frame, EnumHand.MAIN_HAND);
                            }
                            if (((EntityItemFrame) frame).getDisplayedItem().getItem() != Items.AIR) {
                                for (int i = 0; i < turns.getValue(); i++) {
                                    Util.mc.playerController.interactWithEntity(Util.mc.player, frame, EnumHand.MAIN_HAND);
                                }
                                Util.mc.playerController.attackEntity(Util.mc.player, frame);
                                timeoutTicks = 0;
                            }
                        }
                        ++timeoutTicks;
                    }
                }
            }
        }
    }

    private int getShulkerSlot() {
        int shulkerSlot = -1;
        for (int i = 0; i < 9; i++) {
            Item item = Util.mc.player.inventory.getStackInSlot(i).getItem();
            if (item instanceof ItemShulkerBox) shulkerSlot = i;
        }
        return shulkerSlot;
    }
}