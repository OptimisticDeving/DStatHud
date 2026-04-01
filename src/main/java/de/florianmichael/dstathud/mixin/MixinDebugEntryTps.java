/*
 * This file is part of DStatHud - https://github.com/FlorianMichael/DStatHud
 * Copyright (C) 2023 FlorianMichael/EnZaXD and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.florianmichael.dstathud.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import de.florianmichael.dstathud.NetworkTrafficHandler;
import de.florianmichael.dstathud.StringUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugEntryTps;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DebugEntryTps.class)
public class MixinDebugEntryTps {
    @Inject(method = "display", at = @At(value = "RETURN", target = "addLine"))
    public void showDStat(CallbackInfo ci, @Local(argsOnly = true, name = "displayer") DebugScreenDisplayer displayer) {
        if (Minecraft.getInstance().isSingleplayer()) return;
        displayer.addLine(ChatFormatting.GOLD + StringUtils.formatBytes(NetworkTrafficHandler.outgoing) + "/s Up, " + StringUtils.formatBytes(NetworkTrafficHandler.incoming) + "/s Down");
    }
}
