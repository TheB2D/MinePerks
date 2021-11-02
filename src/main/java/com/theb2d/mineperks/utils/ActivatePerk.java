package com.theb2d.mineperks.utils;

import com.theb2d.mineperks.Perks.MinersAura;
import org.bukkit.Sound;

import org.bukkit.entity.Player;

public class ActivatePerk {

    public static void setPerk(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
        MinersAura.apply(player);
    }
}
