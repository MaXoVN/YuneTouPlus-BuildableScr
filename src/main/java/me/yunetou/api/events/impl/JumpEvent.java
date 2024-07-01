/*
 * Decompiled with CFR 0.150.
 */
package me.yunetou.api.events.impl;

import me.yunetou.api.events.Event;

public class JumpEvent
extends Event {
    public double motionX;
    public double motionY;

    public JumpEvent(double motionX, double motionY) {
        this.motionX = motionX;
        this.motionY = motionY;
    }
}

