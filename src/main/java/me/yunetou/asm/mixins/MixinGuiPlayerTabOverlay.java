/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiPlayerTabOverlay
 *  net.minecraft.client.network.NetworkPlayerInfo
 */
package me.yunetou.asm.mixins;

import java.util.List;

import me.yunetou.mod.modules.impl.misc.ExtraTab;
import me.yunetou.mod.modules.impl.misc.TabFriends;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.network.NetworkPlayerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={GuiPlayerTabOverlay.class})
public class MixinGuiPlayerTabOverlay
        extends Gui {
    @Redirect(method={"renderPlayerlist"}, at=@At(value="INVOKE", target="Ljava/util/List;subList(II)Ljava/util/List;"))
    public List<NetworkPlayerInfo> subListHook(List<NetworkPlayerInfo> list, int fromIndex, int toIndex) {
        return list.subList(fromIndex, ExtraTab.INSTANCE.isOn() ? Math.min(ExtraTab.INSTANCE.size.getValue(), list.size()) : toIndex);
    }

    @Inject(method={"getPlayerName"}, at={@At(value="HEAD")}, cancellable=true)
    public void getPlayerNameHook(NetworkPlayerInfo networkPlayerInfoIn, CallbackInfoReturnable<String> info) {
        if (TabFriends.INSTANCE.isOn()) {
            info.setReturnValue(TabFriends.getPlayerName(networkPlayerInfoIn));
        }
    }
}
