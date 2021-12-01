package com.theb2d.mineperks.Perks;

import com.theb2d.mineperks.MinePerks;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class LightningTempo {

    private static int duration = 30;
    public static List<Player> players_affected = new ArrayList<Player>();

    private static MinePerks mainClass;

    public LightningTempo(MinePerks main){
        this.mainClass=main;
    }

    public static List<Player> getPlayersAffected(){
        return players_affected;
    }

    public static void apply(Player player){
        int power = mainClass.getConfig().getInt("perks.lightning_tempo.power");
        players_affected.add(player);
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, duration*20, power));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6You felt a sudden burst of speed!"));
        player.getWorld().strikeLightningEffect(player.getLocation());

        new BukkitRunnable() {
            @Override
            public void run() {
                players_affected.remove(player);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lThe explosive enrichment of your tool has worn off..."));
            }
        }.runTaskLater(mainClass, duration*20);
    }


}