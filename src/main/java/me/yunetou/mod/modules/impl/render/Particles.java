package me.yunetou.mod.modules.impl.render;

import me.yunetou.mod.modules.Category;
import me.yunetou.mod.modules.Module;

public class Particles extends Module
{
    public Particles() {
        super("Particles", "Display Particle.", Category.RENDER);
    }

    public void onUpdate() {
        final int x = (int)(Math.random() * 5.0 + 0.0);
        final int y = (int)(Math.random() * 3.0 + 1.0);
        final int z = (int)(Math.random() * 5.0 - 1.0);
        final int particleId = (int)(Math.random() * 47.0 + 1.0);
        if (particleId != 1 && particleId != 2 && particleId != 41) {
            Particles.mc.effectRenderer.spawnEffectParticle(particleId, Particles.mc.player.posX + 1.5 + -x, Particles.mc.player.posY + y, Particles.mc.player.posZ + 1.5 + -z, 0.0, 0.5, 0.0, new int[] { 10 });
        }
    }
}
