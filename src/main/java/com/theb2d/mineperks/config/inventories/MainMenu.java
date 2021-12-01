package com.theb2d.mineperks.config.inventories;

import com.theb2d.mineperks.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.Collections;

import static com.theb2d.mineperks.utils.InventoryUtils.create;

public class MainMenu implements InventoryHolder {

    private Inventory inv;

    public MainMenu(){
        inv = Bukkit.createInventory(this, 27, ChatColor.BOLD + "Mining Perks config.yml");
        init();
    }

    public void init(){
        inv.setItem(11, create(ChatColor.GOLD + "Perks Power Level", Material.FIRE_CHARGE, Collections.singletonList(ChatColor.GRAY + "Adjust the power of mining perks!"), false));
        inv.setItem(15, create(ChatColor.GOLD + "Perks Duration", Material.CLOCK, Collections.singletonList(ChatColor.GRAY + "Adjust the duration of mining perks!"), false));
        InventoryUtils.fillFiller(inv);
    }

    public Inventory getInventory() {
        return inv;
    }
}
