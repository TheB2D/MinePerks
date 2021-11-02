package com.theb2d.mineperks.perks_events;

import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.Perks.Detonation;
import com.theb2d.mineperks.Perks.Prosperity;
import com.theb2d.mineperks.utils.ActivatePerk;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.Random;

public class ProsperityEvents implements Listener {

    private static MinePerks mainClass;

    public ProsperityEvents(MinePerks main){
        this.mainClass = main; //main instance
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){


        Block block = e.getBlock();
        Material block_mat = block.getType();
        Location block_loc = block.getLocation();

        Player player = e.getPlayer();
        World player_world = player.getWorld();

        Random rand_int = new Random();

        if(Prosperity.getPlayersAffected().contains(player)) {
            if (rand_int.nextInt(Prosperity.chance)==3) { //true will be changed to rand_int
                switch(block_mat){
                    case DIAMOND_ORE:
                        player_world.dropItem(block_loc, Prosperity.when_diamond_mined);
                        break;
                    case EMERALD_ORE:
                        player_world.dropItem(block_loc, Prosperity.when_emerald_mined);
                        break;
                    case LAPIS_ORE:
                        player_world.dropItem(block_loc, Prosperity.when_lapis_mined);
                        break;
                    case REDSTONE_ORE:
                        player_world.dropItem(block_loc, Prosperity.when_redstone_mined);
                        break;
                    case GOLD_ORE:
                        player_world.dropItem(block_loc, Prosperity.when_gold_mined);
                        break;
                    case IRON_ORE:
                        player_world.dropItem(block_loc, Prosperity.when_iron_mined);
                        break;
                    case COAL_ORE:
                        player_world.dropItem(block_loc, Prosperity.when_coal_mined);
                        break;
                    default:
                        return;
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6>&b>&a> &7Your prosperity has added an additional drop!"));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1.0F);

            }
        }
    }
}
