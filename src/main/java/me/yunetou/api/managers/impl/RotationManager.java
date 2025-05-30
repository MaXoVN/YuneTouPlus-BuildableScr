/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Rotation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package me.yunetou.api.managers.impl;

import me.yunetou.api.util.Wrapper;
import me.yunetou.api.util.interact.BlockUtil;
import me.yunetou.api.util.math.MathUtil;
import me.yunetou.asm.accessors.IEntityPlayerSP;
import me.yunetou.mod.Mod;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class RotationManager extends Mod {

    private float yaw;
    private float pitch;

    //Client-side rotations
    public static float[] calculateAngle(Vec3d vec3d, Vec3d vec3d2) {
        double d = vec3d2.x - vec3d.x;
        double d2 = (vec3d2.y - vec3d.y) * -1.0;
        double d3 = vec3d2.z - vec3d.z;
        double d4 = MathHelper.sqrt(d * d + d3 * d3);
        float f = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(d3, d)) - 90.0);
        float f2 = (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(d2, d4)));
        if (f2 > 90.0f) {
            f2 = 90.0f;
        } else if (f2 < -90.0f) {
            f2 = -90.0f;
        }
        return new float[]{f, f2};
    }

    public void updateRotations() {
        yaw = mc.player.rotationYaw;
        pitch = mc.player.rotationPitch;
    }

    public void resetRotations() {
        mc.player.rotationYaw = yaw;
        mc.player.rotationYawHead = yaw;
        mc.player.rotationPitch = pitch;
    }

    public void setRotations(float yaw, float pitch) {
        mc.player.rotationYaw = yaw;
        mc.player.rotationYawHead = yaw;
        mc.player.rotationPitch = pitch;
    }

    public void lookAtPos(BlockPos pos) {
        float[] angle = MathUtil.calcAngle(mc.player.getPositionEyes(Wrapper.mc.getRenderPartialTicks()), new Vec3d((float) pos.getX() + 0.5f, (float) pos.getY() - 0.5f, (float) pos.getZ() + 0.5f));
        setRotations(angle[0], angle[1]);
    }

    public void lookAtVec3d(Vec3d vec3d) {
        float[] angle = MathUtil.calcAngle(mc.player.getPositionEyes(Wrapper.mc.getRenderPartialTicks()), new Vec3d(vec3d.x, vec3d.y, vec3d.z));
        setRotations(angle[0], angle[1]);
    }

    //Packet rotations

    public void lookAtVec3dPacket(Vec3d vec, boolean update) {
        float[] angle = this.getAngle(vec);
        RotationManager.mc.player.connection.sendPacket(new CPacketPlayer.Rotation(angle[0], angle[1], RotationManager.mc.player.onGround));
        if (update) {
            ((IEntityPlayerSP)RotationManager.mc.player).setLastReportedYaw(angle[0]);
            ((IEntityPlayerSP)RotationManager.mc.player).setLastReportedPitch(angle[1]);
        }
    }

    public void lookAtVec3dPacket(Vec3d vec) {
        float[] angle = this.getAngle(vec);
        RotationManager.mc.player.connection.sendPacket(new CPacketPlayer.Rotation(angle[0], angle[1], RotationManager.mc.player.onGround));
    }

    public void resetRotationsPacket() {
        float[] angle = new float[]{mc.player.rotationYaw, mc.player.rotationPitch};
        mc.player.connection.sendPacket(new CPacketPlayer.Rotation(angle[0], angle[1], mc.player.onGround));
    }

    //Getters and other calc stuff

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public float[] getAngle(Vec3d vec) {
        Vec3d eyesPos = new Vec3d(mc.player.posX, mc.player.posY + (double) mc.player.getEyeHeight(), mc.player.posZ);
        double diffX = vec.x - eyesPos.x;
        double diffY = vec.y - eyesPos.y;
        double diffZ = vec.z - eyesPos.z;
        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        float yaw = (float) Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f;
        float pitch = (float) (-Math.toDegrees(Math.atan2(diffY, diffXZ)));
        return new float[]{ mc.player.rotationYaw + MathHelper.wrapDegrees(yaw - mc.player.rotationYaw), mc.player.rotationPitch + MathHelper.wrapDegrees(pitch - mc.player.rotationPitch)};
    }

    /**
     * make sure angle[] isn't null to prevent crashes
     * - asphyxia
     */

    public float[] injectYawStep(float[] angle, float steps) {

        if (steps < 0.1f) steps = 0.1f;

        if (steps > 1) steps = 1;

        if (steps < 1 && angle != null) {

            float packetYaw = ((IEntityPlayerSP) mc.player).getLastReportedYaw();
            float diff = MathHelper.wrapDegrees(angle[0] - packetYaw);

            if (Math.abs(diff) > 180 * steps) {
                angle[0] = (packetYaw + (diff * ((180 * steps) / Math.abs(diff))));
            }
        }

        return new float[] {
                angle[0],
                angle[1]
        };
    }

    public int getYaw4D() {
        return MathHelper.floor((double) (mc.player.rotationYaw * 4.0f / 360.0f) + 0.5) & 3;
    }

    public String getDirection4D(boolean northRed) {
        int yaw = getYaw4D();

        if (yaw == 0) {
            return "South (+Z)";
        }
        if (yaw == 1) {
            return "West (-X)";
        }
        if (yaw == 2) {
            return (northRed ? "\u00c2\u00a7c" : "") + "North (-Z)";
        }
        if (yaw == 3) {
            return "East (+X)";
        }
        return "Loading...";
    }

    public boolean isInFov(BlockPos pos) {
        int yaw = getYaw4D();

        if (yaw == 0 && (double) pos.getZ() - BlockUtil.mc.player.getPositionVector().z < 0.0) {
            return false;
        }
        if (yaw == 1 && (double) pos.getX() - BlockUtil.mc.player.getPositionVector().x > 0.0) {
            return false;
        }
        if (yaw == 2 && (double) pos.getZ() - BlockUtil.mc.player.getPositionVector().z > 0.0) {
            return false;
        }

        return yaw != 3 || (double) pos.getX() - BlockUtil.mc.player.getPositionVector().x >= 0.0;
    }
}