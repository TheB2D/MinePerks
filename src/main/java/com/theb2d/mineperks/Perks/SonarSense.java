package com.theb2d.mineperks.Perks;

import com.theb2d.mineperks.MinePerks;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;

public class SonarSense {

    /*
    * TODO: Activation message
    * TODO: Different messages for each response whether or not diamonds is in range*/

    public static int interval = 10; //secs
    public static Material find_for = Material.DIAMOND_ORE;
    private static int duration = 120;
    private static int minimum_distance_trigger = 16; //in blocks

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

    synchronized private static float getPitch(Player player, Location loc){
        double distance = player.getLocation().distance(loc);

        return 2.0F-((float)(ceil(distance)/10));
    }

    public static void apply(Player player){
        players_affected.add(player);

        new BukkitRunnable() {
            public void run() {
                if(players_affected.contains(player)){
                    if(findNearMaterial(player.getLocation().getChunk())!=null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l♪ &8► &7Your sonar sense has detected some diamonds nearby... Listen to your head!"));
                    }else{
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l♪ &8► &7Your sonar sense is not detecting any diamonds nearby..."));
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
                        player.getWorld().spawnParticle(Particle.NOTE, findNearMaterial(player.getLocation().getChunk()), 1);
                        player.playSound(findNearMaterial(player.getLocation().getChunk()), Sound.BLOCK_NOTE_BLOCK_HARP, 5.0F, getPitch(player, findNearMaterial(player.getLocation().getChunk())));
                    }
                }else{
                    this.cancel();
                }
            }
        }.runTaskTimer(mainClass, 60, 5); //period: 5

        new BukkitRunnable() {
            @Override
            public void run() {
                players_affected.remove(player);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lThe music in your head has &r&7worn out..."));
            }
        }.runTaskLater(mainClass, duration*20);
    }
}
