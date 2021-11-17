package com.theb2d.mineperks.utils;

import com.theb2d.mineperks.MinePerks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class PlayerSonarSenseBindsTo {

    private static File file = new File("player_binds.yml");
    private static FileConfiguration players_binds = YamlConfiguration.loadConfiguration(file);
    private static List<String> player_list = players_binds.getKeys(false).stream().toList();

    public static void addPlayerTargetOre(Player player, Material ore){
        if(!file.exists()){
            try {
                file.createNewFile();
            }catch(IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(e.getMessage());
            }
        }

        players_binds.set(player.getName(), new ItemStack(ore));

        try{
            players_binds.save(file);
        }catch(IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(e.getMessage());
        }
    }

    public static List getPlayerList(){
        return player_list;
    }

    public static ItemStack getPlayerTargetOre(Player player, Boolean ore){
        ItemStack def = new ItemStack(Material.DIAMOND);

        if(ore){
            if(players_binds.getItemStack(player.getName())==null){
                return (new ItemStack(Material.DIAMOND_ORE));
            }
            try{
                ItemStack return_val = new ItemStack(MaterialMatcher.matchMaterialOreForm(players_binds.getItemStack(player.getName()).getType()));
                return return_val;
            }catch(IllegalArgumentException e){
                ;
            }
        }else{
            if(players_binds.getItemStack(player.getName())==null){
                return def;
            }
            try{
                ItemStack return_val = players_binds.getItemStack(player.getName());
                return return_val;
            }catch(IllegalArgumentException e){ }
        }
        return def;
    }
}
