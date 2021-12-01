package com.theb2d.mineperks.config.inventories;

import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import static com.theb2d.mineperks.utils.InventoryUtils.create;

public class PowerLevel implements InventoryHolder {

    // p stands for power
    public static ItemStack detonationP_edit, tempoP_edit, auraP_edit, detonationD_edit, tempoD_edit, auraD_edit, prosperityD_edit, sonarD_edit, herculesD_edit;

    ItemStack immutable_power = create(ChatColor.GRAY + "Immutable value", Material.IRON_BARS, false);

    private Inventory inv;
    MinePerks mainClass;
    static FileConfiguration fconfg;

    public PowerLevel(MinePerks main){
        this.mainClass = main;
        this.fconfg = mainClass.getConfig();

        // this part seems long TODO: optimize (DRY)
        detonationP_edit = create(ChatColor.LIGHT_PURPLE + "Click to edit power", Material.LIGHT_BLUE_STAINED_GLASS_PANE, "Current Power: " + fconfg.get("perks.detonation.power"), false, 1);
        tempoP_edit = create(ChatColor.LIGHT_PURPLE + "Click to edit power", Material.LIGHT_BLUE_STAINED_GLASS_PANE,"Current Power: " + fconfg.get("perks.lightning_tempo.power"), false, 2);
        auraP_edit = create(ChatColor.LIGHT_PURPLE + "Click to edit power", Material.LIGHT_BLUE_STAINED_GLASS_PANE, "Current Power: " + fconfg.get("perks.miners_aura.power"),false, 3);


        detonationD_edit = create(ChatColor.YELLOW + "Click to edit duration", Material.LIME_STAINED_GLASS_PANE, "Current Seconds: " + fconfg.get("perks.detonation.duration"), false, 1);
        tempoD_edit = create(ChatColor.YELLOW + "Click to edit duration", Material.LIME_STAINED_GLASS_PANE, "Current Seconds: " + fconfg.get("perks.lightning_tempo.duration"), false, 2);
        auraD_edit = create(ChatColor.YELLOW + "Click to edit duration", Material.LIME_STAINED_GLASS_PANE, "Current Seconds: " + fconfg.get("perks.miners_aura.duration"), false, 3);
        prosperityD_edit = create(ChatColor.YELLOW + "Click to edit duration", Material.LIME_STAINED_GLASS_PANE, "Current Seconds: " + fconfg.get("perks.prosperity.duration"), false, 4);
        sonarD_edit = create(ChatColor.YELLOW + "Click to edit duration", Material.LIME_STAINED_GLASS_PANE, "Current Seconds: " + fconfg.get("perks.sonar_sense.duration"), false, 5);
        herculesD_edit = create(ChatColor.YELLOW + "Click to edit duration", Material.LIME_STAINED_GLASS_PANE, "Current Seconds: " + fconfg.get("perks.hercules_might.duration"), false, 6);


        inv = Bukkit.createInventory(this, 54, ChatColor.BOLD + "Mining Perks config.yml");
        init();
    }

    public void init(){

        inv.setItem(10, create(ChatColor.GREEN + "Detonation", Material.TNT, true));
        inv.setItem(19, detonationP_edit);
        inv.setItem(28, detonationD_edit);

        inv.setItem(11, create(ChatColor.GREEN + "Lightning Tempo", Material.FEATHER, true));
        inv.setItem(20, tempoP_edit);
        inv.setItem(29, tempoD_edit);

        inv.setItem(12, create(ChatColor.GREEN + "Miner's Aura", Material.BLAZE_POWDER,false));
        inv.setItem(21, auraP_edit);
        inv.setItem(30, auraD_edit);

        inv.setItem(13, create(ChatColor.GREEN + "Prosperity", Material.GOLD_INGOT, false));
        inv.setItem(22, immutable_power);
        inv.setItem(31, prosperityD_edit);

        inv.setItem(14, create(ChatColor.GREEN + "Sonar Sense", Material.NOTE_BLOCK, false));
        inv.setItem(23, immutable_power);
        inv.setItem(32, sonarD_edit);

        inv.setItem(15, create(ChatColor.GREEN + "Hercules' Might", Material.BEDROCK, false));
        inv.setItem(24, immutable_power);
        inv.setItem(33, herculesD_edit);

        inv.setItem(4, create(ChatColor.RED + "" + ChatColor.BOLD + "Edit Power Levels", Material.BOOK, "Click an icon to edit its power levels!`Plugin must be reloaded or restarted`to save changes made.", true));
        inv.setItem(53, create(ChatColor.GREEN + "Go Back", Material.BARRIER,  false));

        InventoryUtils.fillFiller(inv);
    }

    public Inventory getInventory() {
        return inv;
    }
}
