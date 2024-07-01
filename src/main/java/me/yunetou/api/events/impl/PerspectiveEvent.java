/*
 * Decompiled with CFR 0.150.
 */
package me.yunetou.api.events.impl;

import me.yunetou.api.events.Event;

public class PerspectiveEvent
extends Event {
    private float angle;

    public PerspectiveEvent(float angle) {
        this.angle = angle;
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}

