package me.yunetou.mod.modules.impl.render;


import me.yunetou.YuneTou;
import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.settings.Setting;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.Sphere;
import me.yunetou.mod.modules.Module;
import me.yunetou.api.events.impl.Render3DEvent;


public class PenisESP
        extends Module {
    private Setting<Boolean> self = this.register(new Setting<Boolean>("Self", true));
    private Setting<Float> selfLength = this.register(new Setting<Float>("SelfLength", 1.0f, 0.1f, 5.0f, v -> self.getValue()));
    private Setting<Boolean> friends = this.register(new Setting<Boolean>("Friends", true));
    private Setting<Float> friendLength = this.register(new Setting<Float>("FriendsLength", 0.8f, 0.1f, 5.0f, v -> friends.getValue()));
    private Setting<Boolean> others = this.register(new Setting<Boolean>("Others", true));
    private Setting<Float> othersLength = this.register(new Setting<Float>("OthersLength", 0.4f, 0.1f, 5.0f, v -> others.getValue()));
    private Setting<Float> penisSize = this.register(new Setting<Float>("Scale", 1.5f, 0.1f, 5.0f));
    public PenisESP() {
        super("PenisESP", "Cause you have a small dick", Category.RENDER);
    }

    @Override
    public void onRender3D(Render3DEvent event) {
        for (final Object o : mc.world.loadedEntityList) {
            if (o instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer) o;
                final double n = player.lastTickPosX + (player.posX - player.lastTickPosX) * mc.timer.renderPartialTicks;
                mc.getRenderManager();
                final double x = n - mc.getRenderManager().renderPosX;
                final double n2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * mc.timer.renderPartialTicks;
                mc.getRenderManager();
                final double y = n2 - mc.getRenderManager().renderPosY;
                final double n3 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * mc.timer.renderPartialTicks;
                mc.getRenderManager();
                final double z = n3 - mc.getRenderManager().renderPosZ;
                GL11.glPushMatrix();
                RenderHelper.disableStandardItemLighting();
                this.esp(player, x, y, z);
                RenderHelper.enableStandardItemLighting();
                GL11.glPopMatrix();
            }
        }
    }

    private boolean shouldRenderPenis(EntityPlayer player) {
        if (player.entityUniqueID == mc.player.entityUniqueID) {
            return self.getValue();
        }

        if (YuneTou.friendManager.isFriend(player)) {
            return friends.getValue();
        }

        return others.getValue();
    }

    private float getPenisLength(EntityPlayer player) {
        if (player.entityUniqueID == mc.player.entityUniqueID) {
            return selfLength.getValue();
        }

        if (YuneTou.friendManager.isFriend(player)) {
            return friendLength.getValue();
        }

        return othersLength.getValue();
    }

    public void esp(final EntityPlayer player, final double x, final double y, final double z) {
        if (!shouldRenderPenis(player)) return;

        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glEnable(2848);
        GL11.glDepthMask(true);
        GL11.glLineWidth(1.0f);
        GL11.glTranslated(x, y, z);
        GL11.glRotatef(-player.rotationYaw, 0.0f, player.height, 0.0f);
        GL11.glTranslated(-x, -y, -z);
        GL11.glTranslated(x, y + player.height / 2.0f - 0.22499999403953552, z);
        GL11.glColor4f(1.38f, 0.55f, 2.38f, 1.0f);
        GL11.glRotated((player.isSneaking() ? 35 : 0), 1.0f, 0.0f, 0);
        GL11.glTranslated(0.0, 0.0, 0.07500000298023224);
        final Cylinder shaft = new Cylinder();
        shaft.setDrawStyle(100013);
        shaft.draw(0.1f * this.penisSize.getValue(), 0.11f, getPenisLength(player), 25, 20);
        GL11.glTranslated(0.0, 0.0, -0.12500000298023223);
        GL11.glTranslated(-0.09000000074505805, 0.0, 0.0);
        final Sphere right = new Sphere();
        right.setDrawStyle(100013);
        right.draw(0.14f * this.penisSize.getValue(), 10, 20);
        GL11.glTranslated(0.16000000149011612, 0.0, 0.0);
        final Sphere left = new Sphere();
        left.setDrawStyle(100013);
        left.draw(0.14f * this.penisSize.getValue(), 10, 20);
        GL11.glColor4f(1.35f, 0.0f, 0.0f, 1.0f);
        GL11.glTranslated(-0.07000000074505806, 0.0, getPenisLength(player) + 0.189999952316284);
        final Sphere tip = new Sphere();
        tip.setDrawStyle(100013);
        tip.draw(0.13f * this.penisSize.getValue(), 15, 20);
        GL11.glDepthMask(true);
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
    }
}