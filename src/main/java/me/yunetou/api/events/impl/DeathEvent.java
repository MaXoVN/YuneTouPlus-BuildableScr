/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package me.yunetou.api.events.impl;

import me.yunetou.api.events.Event;
import net.minecraft.entity.player.EntityPlayer;

public class DeathEvent
extends Event {
    public EntityPlayer player;

    public DeathEvent(EntityPlayer player) {
        this.player = player;
    }
}

