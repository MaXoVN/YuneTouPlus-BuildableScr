/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.eventhandler.Cancelable
 */
package me.yunetou.api.events.impl;

import me.yunetou.api.events.Event;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class BlockEvent
        extends Event {
    private BlockPos blockPos;
    private EnumFacing enumFacing;

    public BlockEvent(BlockPos blockPos, EnumFacing enumFacing) {
        this.blockPos = blockPos;
        this.enumFacing = enumFacing;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public void setBlockPos(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public EnumFacing getEnumFacing() {
        return this.enumFacing;
    }

    public void setEnumFacing(EnumFacing enumFacing) {
        this.enumFacing = enumFacing;
    }
}

