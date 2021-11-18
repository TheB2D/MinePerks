package com.theb2d.mineperks.Perks;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.handler.PickupHandler;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.utils.InventoryUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HerculesMight {
    private static List<Player> players_affected = new ArrayList<Player>();
    private static MinePerks mainClass;

    public HerculesMight(MinePerks main){
        this.mainClass=main;
    }

    public static List<Player> getPlayersAffected(){
        return players_affected;
    }
    public static List<ArmorStand> armor_stands_deployed = new ArrayList<ArmorStand>();

    public static void apply(Player player){
        //armor_stands_deployed.add(as);
    }

    public static void loopAnimation(Player player, Location location){
        ItemStack item = player.getInventory().getItemInMainHand();
        Block block = location.getBlock();

        if(!item.getType().toString().endsWith("PICKAXE")){
            return;
        }

        ArmorStand am = player.getLocation().getWorld().spawn(location.add(0.71, 0.7 ,0), ArmorStand.class);
        HerculesMight.armor_stands_deployed.add(am);
        am.setInvisible(true);
        am.setInvulnerable(true);


        ItemStack filler_item = InventoryUtils.create(ChatColor.GOLD+"Your pickaxe is currently in use", Material.GHAST_TEAR, Collections.singletonList(ChatColor.GRAY + "Do not throw away this item!"), true);
        player.getInventory().remove(item);
        player.getInventory().addItem(filler_item);
        ItemStack break_effect = new ItemStack(Material.BEDROCK);

        new BukkitRunnable() {
            int x = -360;
            @Override
            public void run() {
                if(!armor_stands_deployed.contains(am)){
                    am.remove();
                    this.cancel();
                }

                location.setPitch(x);
                am.teleport(location);
                am.getEquipment().setItemInMainHand(item);
                am.setRightArmPose(new EulerAngle( Math.toRadians(x) , 0 , 0));
                x+=10;
            }
        }.runTaskTimer(mainClass, 0, 1);

        Hologram hologram = HologramsAPI.createHologram(mainClass, location.add(0, 2, 0));
        hologram.insertTextLine(0, ChatColor.translateAlternateColorCodes('&', "&b&l Currently mining bedrock!"));
        hologram.insertTextLine(1,  "");

        new BukkitRunnable(){
            int duration = 15;
            @Override
            public void run() {
                if(duration<=0){
                    armor_stands_deployed.remove(am);
                    am.remove();
                    hologram.removeLine(1);
                    hologram.removeLine(0);
                    block.setType(Material.AIR);
                    hologram.appendTextLine(ChatColor.translateAlternateColorCodes('&', "&b&l Mining task completed!"));
                    hologram.appendTextLine(ChatColor.GRAY + "Pick up your pickaxe!");
                    ItemLine pickaxe = hologram.insertItemLine(1, item);

                    pickaxe.setPickupHandler(new PickupHandler() {

                        @Override
                        public void onPickup(Player player) {

                            // Play an effect.
                            player.playEffect(hologram.getLocation(), Effect.MOBSPAWNER_FLAMES, null);

                            // 30 seconds of speed II.
                            player.getInventory().remove(filler_item);
                            player.getInventory().addItem(item);

                            // Delete the hologram.
                            hologram.delete();

                        }
                    });

                    Firework fw = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
                    FireworkMeta fwm = fw.getFireworkMeta();

                    fwm.setPower(2);
                    fwm.addEffect(FireworkEffect.builder().withColor(Color.LIME).flicker(true).build());

                    fw.setFireworkMeta(fwm);
                    fw.detonate();
                    this.cancel();
                    return;
                }
                location.getWorld().spawnParticle(Particle.ITEM_CRACK, location, 20, break_effect);
                location.getWorld().playSound(location, Sound.BLOCK_STONE_BREAK, 1.0F, 1.0F);
                hologram.removeLine(1);
                hologram.insertTextLine(1,  ChatColor.translateAlternateColorCodes('&', "&l" + timerFormat(duration)));
                duration-=1;
            }
        }.runTaskTimer(mainClass, 0, 20);
    }


    public static void destroyArmorstandsDeployed(){
        for(ArmorStand as : armor_stands_deployed){
            as.remove();
        }
        return;
    }

    private static String timerFormat(int secs){
        String mins_f, sec_f;
        String strFormatted;
        int mins = (int)Math.floor(secs/60);
        int sec = secs%60;
        if(mins<10){
            mins_f = "0"+Integer.toString(mins);
        }else{mins_f = Integer.toString(mins);}
        if(sec<10){
            sec_f = "0"+Integer.toString(sec);
        }else{sec_f = Integer.toString(sec);}
        strFormatted = mins_f + ":" + sec_f;
        return strFormatted;
    }

}
