package me.yunetou.api.util.render.shaders;

import me.yunetou.api.util.render.shaders.FramebufferShader;

@FunctionalInterface
public interface ShaderProducer {
    public FramebufferShader INSTANCE();
}
