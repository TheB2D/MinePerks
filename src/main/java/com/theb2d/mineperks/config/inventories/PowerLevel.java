package com.theb2d.mineperks.config.inventories;

import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.commands.Configuration;
import com.theb2d.mineperks.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.ArrayList;
import java.util.Collections;

import static com.theb2d.mineperks.utils.InventoryUtils.create;

public class PowerLevel implements InventoryHolder {
    private Inventory inv;
    MinePerks mainClass;
    FileConfiguration fconfg;

    public PowerLevel(MinePerks main){
        this.mainClass = main;
        this.fconfg = mainClass.getConfig();
        inv = Bukkit.createInventory(this, 27, ChatColor.BOLD + "Mining Perks config.yml");
        init();
    }

    public void init(){
        inv.setItem(10, create(ChatColor.GREEN + "Detonation", Material.TNT, ChatColor.GRAY + "Current: " + fconfg.get("perks.detonation.power"), false));
        inv.setItem(11, create(ChatColor.GREEN + "Lightning Tempo", Material.FEATHER,ChatColor.GRAY + "Current: " + fconfg.get("perks.lightning_tempo.power"), false));
        inv.setItem(12, create(ChatColor.GREEN + "Miner's Aura", Material.BLAZE_POWDER,ChatColor.GRAY + "Current: " + fconfg.get("perks.miners_aura.power"), false));
        inv.setItem(13, create(ChatColor.GREEN + "Prosperity", Material.GOLD_INGOT,ChatColor.GRAY + "Immutable", false));
        inv.setItem(14, create(ChatColor.GREEN + "Sonar Sense", Material.NOTE_BLOCK,ChatColor.GRAY + "Current: " + fconfg.get("perks.miners_aura.power"), false));
        inv.setItem(15, create(ChatColor.GREEN + "Hercules' Might", Material.BEDROCK,ChatColor.GRAY + "Immutable", false));



        inv.setItem(26, create(ChatColor.GREEN + "Go Back", Material.BARRIER,  false));
    }

    public Inventory getInventory() {
        return inv;
    }
}
