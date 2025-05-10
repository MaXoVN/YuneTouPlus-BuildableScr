package me.yunetou.mod.modules.impl.render;

import me.yunetou.api.events.impl.Render3DEvent;
import me.yunetou.api.util.entity.EntityUtil;
import me.yunetou.api.util.entity.EntityUtil2;
import me.yunetou.api.util.render.RenderUtil;
import me.yunetou.api.util.render.RenderUtil2;
import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class CityRender
        extends Module {
    private final Setting<Integer> range = this.add(new Setting<Integer>("Range", 5, 1, 10));
    public EntityPlayer target;

    public CityRender() {
        super("CityRender", "CityRender", Category.RENDER);
    }

    @Override
    public void onRender3D(Render3DEvent event) {
        if (CityRender.fullNullCheck()) {
            return;
        }
        this.target = this.getTarget(this.range.getValue().intValue());
        this.surroundRender();
    }

    private void surroundRender() {
        if (this.target != null) {
            Vec3d a = this.target.getPositionVector();
            if (CityRender.mc.world.getBlockState(new BlockPos(a)).getBlock() == Blocks.OBSIDIAN || CityRender.mc.world.getBlockState(new BlockPos(a)).getBlock() == Blocks.ENDER_CHEST) {
                RenderUtil2.drawBoxESP(new BlockPos(a), new Color(255, 255, 0), false, new Color(255, 255, 0), 1.0f, false, true, 42, true);
            }
            if (EntityUtil2.getSurroundWeakness(a, -1, 1)) {
                this.surroundRender(a, -1.0, 0.0, 0.0, true);
            }
            if (EntityUtil2.getSurroundWeakness(a, -1, 2)) {
                this.surroundRender(a, 1.0, 0.0, 0.0, true);
            }
            if (EntityUtil2.getSurroundWeakness(a, -1, 3)) {
                this.surroundRender(a, 0.0, 0.0, -1.0, true);
            }
            if (EntityUtil2.getSurroundWeakness(a, -1, 4)) {
                this.surroundRender(a, 0.0, 0.0, 1.0, true);
            }
            if (EntityUtil2.getSurroundWeakness(a, -1, 5)) {
                this.surroundRender(a, -1.0, 0.0, 0.0, false);
            }
            if (EntityUtil2.getSurroundWeakness(a, -1, 6)) {
                this.surroundRender(a, 1.0, 0.0, 0.0, false);
            }
            if (EntityUtil2.getSurroundWeakness(a, -1, 7)) {
                this.surroundRender(a, 0.0, 0.0, -1.0, false);
            }
            if (EntityUtil2.getSurroundWeakness(a, -1, 8)) {
                this.surroundRender(a, 0.0, 0.0, 1.0, false);
            }
        }
    }

    private void surroundRender(Vec3d pos, double x, double y, double z, boolean red) {
        BlockPos position = new BlockPos(pos).add(x, y, z);
        if (CityRender.mc.world.getBlockState(position).getBlock() == Blocks.AIR || CityRender.mc.world.getBlockState(position).getBlock() == Blocks.FIRE) {
            return;
        }
        if (red) {
            RenderUtil2.drawBoxESP(position, new Color(255, 0, 0), false, new Color(255, 0, 0), 1.0f, false, true, 42, true);
        } else {
            RenderUtil2.drawBoxESP(position, new Color(0, 0, 255), false, new Color(0, 0, 255), 1.0f, false, true, 42, true);
        }
    }

    private EntityPlayer getTarget(double range) {
        EntityPlayer target = null;
        double distance = range;
        for (EntityPlayer player : CityRender.mc.world.playerEntities) {
            if (EntityUtil2.isntValid((Entity)player, range) || !EntityUtil2.isInHole((Entity)player)) continue;
            if (target == null) {
                target = player;
                distance = EntityUtil2.mc.player.getDistanceSq((Entity)player);
                continue;
            }
            if (EntityUtil2.mc.player.getDistanceSq((Entity)player) >= distance) continue;
            target = player;
            distance = EntityUtil2.mc.player.getDistanceSq((Entity)player);
        }
        return target;
    }
}
