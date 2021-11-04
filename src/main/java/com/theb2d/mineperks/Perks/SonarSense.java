package com.theb2d.mineperks.Perks;

import com.theb2d.mineperks.MinePerks;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SonarSense {

    public static int interval = 10; //secs
    public static Material find_for = Material.DIAMOND_ORE;
    private static int duration = 30;

    private static MinePerks mainClass;

    public SonarSense(MinePerks main){
        this.mainClass=main;
    }

    public static List<Player> players_affected = new ArrayList<Player>();

    public static List<Player> getPlayersAffected(){
        return players_affected;
    }

    public static Location findNearMaterial(Chunk chunk) {
        int bx = chunk.getX()<<4;
        int bz = chunk.getZ()<<4;

        World world = chunk.getWorld();

        for(int xx = bx; xx < bx+16; xx++) {
            for (int zz = bz; zz < bz + 16; zz++) {
                for (int yy = 0; yy < 128; yy++) {
                    Material typeId = world.getBlockAt(xx, yy, zz).getType();
                    if (typeId == find_for) {
                        return world.getBlockAt(xx, yy + 1, zz).getLocation();
                    }
                }
            }
        }
        return null;
    }

    public static void apply(Player player){
        players_affected.add(player);

        new BukkitRunnable() {
            public void run() {
                if(players_affected.contains(player)){
                    if(findNearMaterial(player.getLocation().getChunk())!=null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYour sonar sense has detected some diamonds nearby... Listen to your head!"));
                    }else{
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bYour sonar sense is not detecting any diamonds nearby..."));
                    }
                }else{
                    this.cancel();
                }
            }
        }.runTaskTimer(mainClass, 60, interval*20);

        new BukkitRunnable() {
            @Override
            public void run() {
                if(players_affected.contains(player)) {
                    if (findNearMaterial(player.getLocation().getChunk()) != null) {
                        player.playSound(findNearMaterial(player.getLocation().getChunk()), Sound.BLOCK_NOTE_BLOCK_HARP, 5.0F, 5.0F);
                        player.getWorld().spawnParticle(Particle.NOTE, findNearMaterial(player.getLocation().getChunk()), 1);
                    }
                }else{
                    this.cancel();
                }
            }
        }.runTaskTimer(mainClass, 60, 5);

        new BukkitRunnable() {
            @Override
            public void run() {
                players_affected.remove(player);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lThe music in your head has &r&7worn out..."));
            }
        }.runTaskLater(mainClass, duration*20);
    }
}
