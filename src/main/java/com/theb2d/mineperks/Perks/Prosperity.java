package com.theb2d.mineperks.Perks;

import com.theb2d.mineperks.MinePerks;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Prosperity {

    private static int duration;
    public static int chance = 3;
    public static final ItemStack when_diamond_mined = new ItemStack(Material.DIAMOND_BLOCK);
    public static final ItemStack when_emerald_mined = new ItemStack(Material.EMERALD_BLOCK);
    public static final ItemStack when_redstone_mined = new ItemStack(Material.REDSTONE_BLOCK);
    public static final ItemStack when_iron_mined = new ItemStack(Material.IRON_BLOCK);
    public static final ItemStack when_coal_mined = new ItemStack(Material.COAL_BLOCK);
    public static final ItemStack when_lapis_mined = new ItemStack(Material.LAPIS_BLOCK);
    public static final ItemStack when_gold_mined = new ItemStack(Material.GOLD_BLOCK);

    private static MinePerks mainClass;

    public Prosperity(MinePerks main){
        this.mainClass=main;
        duration = main.getConfig().getInt("perks.prosperity.duration");
    }

    public static List<Player> players_affected = new ArrayList<Player>();

    public static List getPlayersAffected(){
        return players_affected;
    }

    public static void apply(Player player){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&l&9Fortuna has blessed you with prosperity!"));
        players_affected.add(player);

        new BukkitRunnable() {
            @Override
            public void run() {
                players_affected.remove(player);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&l&9The prosperity within you has worn off..."));
            }
        }.runTaskLater(mainClass, duration*20); //FIXME

    }
}
