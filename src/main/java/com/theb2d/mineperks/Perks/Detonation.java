package com.theb2d.mineperks.Perks;

import com.theb2d.mineperks.MinePerks;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Detonation {

    // other players may get damaged from the explosion, but the user will not be damaged

    private static int duration = 30; // in secs

    private static MinePerks mainClass;

    public Detonation(MinePerks main){
        this.mainClass=main;
    }

    public static List<Player> players_affected = new ArrayList<Player>();

    public static List getPlayersAffected(){
        return players_affected;
    }

    public static void apply(Player player){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lYour tool has been enriched with explosives!"));
        players_affected.add(player);

        new BukkitRunnable() {
            @Override
            public void run() {
                players_affected.remove(player);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lThe explosive enrichment of your tool has worn off..."));
            }
        }.runTaskLater(mainClass, duration*20);


    }
}
