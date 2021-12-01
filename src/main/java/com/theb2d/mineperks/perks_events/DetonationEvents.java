package com.theb2d.mineperks.perks_events;

import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.Perks.Detonation;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class DetonationEvents implements Listener {

    private static float explosion_power;
    private static boolean setFire = false;
    private static int chance = 5;

    private static MinePerks mainClass;

    public DetonationEvents(MinePerks main){
        this.mainClass = main; //main instance
    }

    @EventHandler
    public static void onBreak(BlockBreakEvent e){

        explosion_power = (float) mainClass.getConfig().getInt("perks.detonation.power");

        Block block = e.getBlock();

        Player player = e.getPlayer();

        Random rand_int = new Random();

        if(Detonation.getPlayersAffected().contains(player)) {
            if (rand_int.nextInt(chance+1) == 3) {
                player.setNoDamageTicks(1);
                player.getWorld().createExplosion(block.getLocation(), explosion_power, setFire, true);
            }
        }
    }
}
