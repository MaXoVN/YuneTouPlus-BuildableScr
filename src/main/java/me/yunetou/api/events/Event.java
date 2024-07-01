/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package me.yunetou.api.events;

import me.yunetou.YuneTou;

public class Event
extends net.minecraftforge.fml.common.eventhandler.Event {
    private int stage;

    public Event() {
    }

    public Event(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return this.stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void cancel() {
        try {
            this.setCanceled(true);
        }
        catch (Exception e) {
            YuneTou.LOGGER.info(((Object)((Object)this)).getClass().toString() + " Isn't cancellable!");
        }
    }
}

