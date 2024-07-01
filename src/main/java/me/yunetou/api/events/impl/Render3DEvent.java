/*
 * Decompiled with CFR 0.150.
 */
package me.yunetou.api.events.impl;

import me.yunetou.api.events.Event;

public class Render3DEvent
extends Event {
    private final float partialTicks;

    public Render3DEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }
}

