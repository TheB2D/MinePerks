package com.theb2d.mineperks.Perks;

import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.utils.AmountProgress;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class MinersAura {

    private static final int duration = 30;
    private static final boolean givePoints = true; //give points for every 3 blocks mined

    private static MinePerks mainClass;
    private static float angle = 0f;

    public MinersAura(MinePerks main){
        this.mainClass=main;
    }

    private static float radius = (float) mainClass.getConfig().getInt("perks.miners_aura.power");

    public static List<Player> players_affected = new ArrayList<Player>();

    public static List<Player> getPlayersAffected(){
        return players_affected;
    }

    public static void apply(Player player){
        players_affected.add(player);


        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&l&4Miner's Aura activated!"));

        new BukkitRunnable() {
            int amount_mined = 0;
            int i = 0;
            public void run() {

                i++;


                Location location = player.getLocation();
                World p_world = location.getWorld();
                double x = (radius * Math.sin(angle));
                double z = (radius * Math.cos(angle));

                Block block_aura_at = p_world.getBlockAt((int)(location.getX()+x), (int)location.getY()+1, (int)(location.getZ()+z)); // the block where the aura will break
                Block block_aura_at2 = p_world.getBlockAt((int)(location.getX()+x), (int)location.getY()+2, (int)(location.getZ()+z));
                Block block_aura_at3 = p_world.getBlockAt((int)(location.getX()+x), (int)location.getY()+3, (int)(location.getZ()+z));


                if(block_aura_at.getType() != Material.AIR || block_aura_at2.getType() != Material.AIR || block_aura_at3.getType()!=Material.AIR){
                    player.getInventory().addItem(new ItemStack(block_aura_at.getType()), new ItemStack(block_aura_at2.getType()), new ItemStack(block_aura_at3.getType()));

                    if(givePoints!=false){AmountProgress.triggerProgress(player);}

                    block_aura_at.setType(Material.AIR, true);
                    block_aura_at2.setType(Material.AIR, true);
                    block_aura_at3.setType(Material.AIR, true);
                    player.playSound(location, Sound.BLOCK_STONE_BREAK, 1.0F, 1.0F);
                    amount_mined+=3;
                }

                p_world.spawnParticle(Particle.DRIP_LAVA, location.getX()+x, location.getY()+1.0F, location.getZ()+z, 20, 0, 1, 0);

                if(i == duration*20) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&l&4Miner's Aura has mined you &b&l" + amount_mined + " blocks! &7It has now worn out..."));
                    players_affected.remove(player);
                    this.cancel();
                }
                angle += 1;
            }
        }.runTaskTimer(mainClass, 0, 1);




    }
}
