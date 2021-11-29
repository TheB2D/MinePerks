package com.theb2d.mineperks.config.inventories;

import com.theb2d.mineperks.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.Collections;

public class MainMenu implements InventoryHolder {

    private Inventory inv;

    public MainMenu(){
        inv = Bukkit.createInventory(this, 27, ChatColor.GREEN + "Mining Perks config.yml");
        init();
    }

    public void init(){
        inv.setItem(11, InventoryUtils.create(ChatColor.GOLD + "Perks Power Level", Material.FIRE_CHARGE, Collections.singletonList(ChatColor.GRAY + "Adjust the power of mining perks!"), false));
        inv.setItem(15, InventoryUtils.create(ChatColor.GOLD + "Perks Duration", Material.CLOCK, Collections.singletonList(ChatColor.GRAY + "Adjust the duration of mining perks!"), false));
    }

    public Inventory getInventory() {
        return inv;
    }
}
