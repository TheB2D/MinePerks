package com.theb2d.mineperks.Perks;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LightningTempo {

    private static int haste_duration = 30;
    private static int haste_power = 255;

    public static void apply(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, haste_duration*20, haste_power));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You felt a sudden burst of speed!"));
        player.getWorld().strikeLightningEffect(player.getLocation());
    }
}