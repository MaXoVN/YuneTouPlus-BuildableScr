/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 */
package me.yunetou.api.managers.impl;

import me.yunetou.mod.Mod;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;

public class PositionManager
extends Mod {
    private double x;
    private double y;
    private double z;
    private boolean onGround;

    public void updatePosition() {
        this.x = PositionManager.mc.player.posX;
        this.y = PositionManager.mc.player.posY;
        this.z = PositionManager.mc.player.posZ;
        this.onGround = PositionManager.mc.player.onGround;
    }

    public void restorePosition() {
        PositionManager.mc.player.posX = this.x;
        PositionManager.mc.player.posY = this.y;
        PositionManager.mc.player.posZ = this.z;
        PositionManager.mc.player.onGround = this.onGround;
    }

    public void setPlayerPosition(double x, double y, double z) {
        PositionManager.mc.player.posX = x;
        PositionManager.mc.player.posY = y;
        PositionManager.mc.player.posZ = z;
    }

    public void setPlayerPosition(double x, double y, double z, boolean onGround) {
        PositionManager.mc.player.posX = x;
        PositionManager.mc.player.posY = y;
        PositionManager.mc.player.posZ = z;
        PositionManager.mc.player.onGround = onGround;
    }

    public void setPositionPacket(double x, double y, double z, boolean onGround, boolean setPos, boolean noLagBack) {
        PositionManager.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, onGround));
        if (setPos) {
            PositionManager.mc.player.setPosition(x, y, z);
            if (noLagBack) {
                this.updatePosition();
            }
        }
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}

