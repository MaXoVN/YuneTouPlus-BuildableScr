package me.yunetou.api.util.shaders.impl.fill;

import java.awt.Color;
import java.util.HashMap;
import me.yunetou.api.util.shaders.FramebufferShader;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class AquaShader
extends FramebufferShader {
    public static final AquaShader INSTANCE = new AquaShader();
    public float time;

    @Override
    public void setupUniforms() {
        this.setupUniform("resolution");
        this.setupUniform("time");
        this.setupUniform("rgba");
        this.setupUniform("lines");
        this.setupUniform("tau");
    }

    public void stopDraw(Color color, float f, float f2, float f3, int n, double d) {
        this.mc.gameSettings.entityShadows = this.entityShadows;
        this.framebuffer.unbindFramebuffer();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        this.mc.getFramebuffer().bindFramebuffer(true);
        this.red = (float)color.getRed() / 255.0f;
        this.green = (float)color.getGreen() / 255.0f;
        this.blue = (float)color.getBlue() / 255.0f;
        this.alpha = (float)color.getAlpha() / 255.0f;
        this.radius = f;
        this.quality = f2;
        this.mc.entityRenderer.disableLightmap();
        RenderHelper.disableStandardItemLighting();
        this.startShader(f3, color, n, d);
        this.mc.entityRenderer.setupOverlayRendering();
        this.drawFramebuffer(this.framebuffer);
        this.stopShader();
        this.mc.entityRenderer.disableLightmap();
        GlStateManager.popMatrix();
        GlStateManager.popAttrib();
    }

    public void startShader(float f, Color color, int n, double d) {
        GL11.glPushMatrix();
        GL20.glUseProgram(this.program);
        if (this.uniformsMap == null) {
            this.uniformsMap = new HashMap();
            this.setupUniforms();
        }
        this.updateUniforms(f, color, n, d);
    }

    public void updateUniforms(float f, Color color, int n, double d) {
        GL20.glUniform2f(this.getUniform("resolution"), (float)new ScaledResolution(this.mc).getScaledWidth() / f, (float)new ScaledResolution(this.mc).getScaledHeight() / f);
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform4f(this.getUniform("rgba"), (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        GL20.glUniform1i(this.getUniform("lines"), n);
        GL20.glUniform1f(this.getUniform("tau"), (float)d);
    }

    public AquaShader() {
        super("aqua.frag");
    }

    public void update(double d) {
        this.time = (float)((double)this.time + d);
    }
}

