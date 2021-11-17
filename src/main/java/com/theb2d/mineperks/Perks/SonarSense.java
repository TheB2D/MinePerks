package com.theb2d.mineperks.Perks;

import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.utils.MaterialMatcher;
import com.theb2d.mineperks.utils.PlayerSonarSenseBindsTo;
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
    private static int duration = 60;

    private static MinePerks mainClass;

    public SonarSense(MinePerks main){
        this.mainClass=main;
    }

    public static List<Player> players_affected = new ArrayList<Player>();

    public static List<Player> getPlayersAffected(){
        return players_affected;
    }

    public static Location findNearMaterial(Chunk chunk, Player player) {

        Material find_for = PlayerSonarSenseBindsTo.getPlayerTargetOre(player, true).getType();

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
        String ore = MaterialMatcher.matchMaterialOreFormStr(PlayerSonarSenseBindsTo.getPlayerTargetOre(player, false).getType(), false);

        new BukkitRunnable() {
            public void run() {
                if(players_affected.contains(player)){

                    if(findNearMaterial(player.getLocation().getChunk(), player)!=null){
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l♪ &8► &7Your sonar sense has detected some " + ore + " nearby... Listen to your head!"));
                    }else{
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l♪ &8► &7Your sonar sense is not detecting any " + ore + " nearby..."));
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
                    if (findNearMaterial(player.getLocation().getChunk(), player) != null) {
                        player.getWorld().spawnParticle(Particle.NOTE, findNearMaterial(player.getLocation().getChunk(), player), 1);
                        player.playSound(findNearMaterial(player.getLocation().getChunk(), player), Sound.BLOCK_NOTE_BLOCK_HARP, 5.0F, getPitch(player, findNearMaterial(player.getLocation().getChunk(), player)));
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
