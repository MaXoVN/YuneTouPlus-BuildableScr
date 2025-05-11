package me.yunetou.mod.modules.impl.render;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;

public class ItemPhysics
        extends Module {
    public static ItemPhysics INSTANCE = new ItemPhysics();
    public final Setting<Float> Scaling = this.add(new Setting<>("Scale", 0.5f, 0.1f, 1.0f));
    public final Setting<Float> rotateSpeed = this.add(new Setting<>("RotateSpeed", 0.5f, 0.0f, 1.0f));
    public final Setting<Float> shulkerBox = this.add(new Setting<>("ShulkerBoxScale", 0.5f, 0.0f, 4.0f));

    public ItemPhysics() {
        super("ItemPhysics", "Apply physics to items", Category.RENDER);
        INSTANCE = this;
    }
}
