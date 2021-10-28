package com.theb2d.mineperks.utils;

import com.theb2d.mineperks.Perks.Detonation;
import com.theb2d.mineperks.Perks.LightningTempo;
import com.theb2d.mineperks.Perks.Prosperity;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ActivatePerk {

    public static void setPerk(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
        Prosperity.apply(player);
    }
}
