package me.yunetou.mod.modules.impl.render;


import me.yunetou.YuneTou;
import me.yunetou.api.events.impl.Render3DEvent;
import me.yunetou.api.util.entity.EntityUtil;
import me.yunetou.api.util.render.RenderUtil;
import me.yunetou.api.util.render.RenderUtil2;
import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;
import me.yunetou.mod.modules.settings.Setting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class BurrowESP
        extends Module {
    private final Setting<Integer> boxRed;
    private final Setting <Integer> outlineGreen;
    private final Setting <Integer> boxGreen;
    private final Setting <Boolean> box;
    private final Setting <Boolean> cOutline;
    private final Setting <Integer> outlineBlue;
    private final Setting <Boolean> name = this.add(new Setting <>("Name", false));
    private final Setting <Integer> boxAlpha;
    private final Setting <Float> outlineWidth;
    private final Setting<Integer> outlineRed;
    private final Setting <Boolean> outline;
    private final Setting <Integer> boxBlue;
    private final Map <EntityPlayer, BlockPos> burrowedPlayers;
    private final Setting <Integer> outlineAlpha;

    public
    BurrowESP() {
        super("BurrowESP", "Renders people burrowing", Category.RENDER);
        this.box = new Setting<>( "Box", true);
        this.boxRed = this.add(new Setting <>("BoxRed", 255, 0, 255, v -> this.box.getValue()));
        this.boxGreen = this.add(new Setting <>("BoxGreen", 255, 0, 255, v -> this.box.getValue()));
        this.boxBlue = this.add(new Setting <>("BoxBlue", 255, 0, 255, v -> this.box.getValue()));
        this.boxAlpha = this.add(new Setting <>("BoxAlpha", 125, 0, 255, v -> this.box.getValue()));
        this.outline = this.add(new Setting <>("Outline", true));
        this.outlineWidth = this.add(new Setting <>("OutlineWidth", 1.0f, 0.0f, 5.0f, v -> this.outline.getValue()));
        this.cOutline = this.add(new Setting <>("CustomOutline", false, v -> this.outline.getValue()));
        this.outlineRed = this.add(new Setting <>("OutlineRed", 255, 0, 255, v -> this.cOutline.getValue()));
        this.outlineGreen = this.add(new Setting <>("OutlineGreen", 255, 0, 255, v -> this.cOutline.getValue()));
        this.outlineBlue = this.add(new Setting <>("OutlineBlue", 255, 0, 255, v -> this.cOutline.getValue()));
        this.outlineAlpha = this.add(new Setting <>("OutlineAlpha", 255, 0, 255, v -> this.cOutline.getValue()));
        this.burrowedPlayers = new HashMap <>();
    }

    private
    void getPlayers() {
        for (EntityPlayer entityPlayer : BurrowESP.mc.world.playerEntities) {
            if (entityPlayer == BurrowESP.mc.player || YuneTou.friendManager.isFriend(entityPlayer.getName()) || ! EntityUtil.isLiving(entityPlayer) || ! this.isBurrowed(entityPlayer))
                continue;
            this.burrowedPlayers.put(entityPlayer, new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ));
        }
    }

    @Override
    public
    void onEnable() {
        this.burrowedPlayers.clear();
    }

    private
    void lambda$onRender3D$8(Map.Entry entry) {
        this.renderBurrowedBlock((BlockPos) entry.getValue());
        if (this.name.getValue()) {
            RenderUtil.drawTexta(new BlockPos((BlockPos) entry.getValue()), ((EntityPlayer) entry.getKey()).getGameProfile().getName());
        }
    }

    private
    boolean isBurrowed(EntityPlayer entityPlayer) {
        BlockPos blockPos = new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY + 0.2), Math.floor(entityPlayer.posZ));
        return BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.ENDER_CHEST || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.CHEST || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.ANVIL;
    }

    @Override
    public
    void onUpdate() {
        if (BurrowESP.fullNullCheck()) {
            return;
        }
        this.burrowedPlayers.clear();
        this.getPlayers();
    }

    private
    void renderBurrowedBlock(BlockPos blockPos) {
        RenderUtil2.drawBoxESP(blockPos, new Color(this.boxRed.getValue(), this.boxGreen.getValue(), this.boxBlue.getValue(), this.boxAlpha.getValue()), true, new Color(this.outlineRed.getValue(), this.outlineGreen.getValue(), this.outlineBlue.getValue(), this.outlineAlpha.getValue()), this.outlineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
    }

    @Override
    public
    void onRender3D(Render3DEvent render3DEvent) {
        if (! this.burrowedPlayers.isEmpty()) {
            this.burrowedPlayers.entrySet().forEach(this::lambda$onRender3D$8);
        }
    }
}
