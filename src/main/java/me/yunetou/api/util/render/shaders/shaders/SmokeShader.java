package me.yunetou.api.util.render.shaders.shaders;

import me.yunetou.api.util.render.shaders.FramebufferShader;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL20;

public class SmokeShader
extends FramebufferShader {
    private static SmokeShader INSTANCE;
    protected float time = 0.0f;

    private SmokeShader() {
        super("smoke.frag");
    }

    public static SmokeShader INSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new SmokeShader();
        }
        return INSTANCE;
    }

    @Override
    public void setupUniforms() {
        this.setupUniform("time");
        this.setupUniform("resolution");
        this.setupUniform("radius");
        this.setupUniform("divider");
        this.setupUniform("maxSample");
        this.setupUniform("texelSize");
    }

    @Override
    public void updateUniforms() {
        GL20.glUniform1f(this.getUniform("time"), this.time);
        GL20.glUniform2f(this.getUniform("resolution"), (float)new ScaledResolution(this.mc).getScaledWidth() / 2.0f, (float)new ScaledResolution(this.mc).getScaledHeight() / 2.0f);
        GL20.glUniform1f(this.getUniform("radius"), this.radius);
        GL20.glUniform1f(this.getUniform("divider"), this.divider);
        GL20.glUniform1f(this.getUniform("maxSample"), this.maxSample);
        GL20.glUniform2f(this.getUniform("texelSize"), 1.0f / (float)this.mc.displayWidth * (this.radius * this.quality), 1.0f / (float)this.mc.displayHeight * (this.radius * this.quality));
        if (!this.animation) {
            return;
        }
        this.time = this.time > 100.0f ? 0.0f : (float)((double)this.time + 0.05 * (double)this.animationSpeed);
    }
}

