/*
 * Decompiled with CFR 0.150.
 */
package me.yunetou.api.events.impl;

import me.yunetou.api.events.Event;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

@Cancelable
public class StepEvent
        extends Event {
    private final AxisAlignedBB axisAlignedBB;
    private float height;

    public StepEvent(AxisAlignedBB axisAlignedBB, float height) {
        this.axisAlignedBB = axisAlignedBB;
        this.height = height;
    }

    public AxisAlignedBB getAxisAlignedBB() {
        return this.axisAlignedBB;
    }

    public float getHeight() {
        return this.height;
    }

    public void setHeight(float in) {
        this.height = in;
    }
}

