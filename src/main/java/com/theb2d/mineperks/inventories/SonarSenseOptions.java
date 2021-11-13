package com.theb2d.mineperks.inventories;

import com.theb2d.mineperks.utils.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class SonarSenseOptions implements InventoryHolder {

    private Inventory inv;

    public SonarSenseOptions(){
        inv = Bukkit.createInventory(this, 27 , "Sonar sense options");
        init();
    }

    private void init(){
        int row = 9;
        ItemStack diamonds = InventoryUtils.create(ChatColor.GREEN + "Diamond ore", Material.DIAMOND, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), true);
        ItemStack emeralds = InventoryUtils.create(ChatColor.GREEN + "Emerald ore", Material.EMERALD, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), true);
        ItemStack redstone = InventoryUtils.create(ChatColor.GREEN + "Redstone ore", Material.REDSTONE, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), true);
        ItemStack gold = InventoryUtils.create(ChatColor.GREEN + "Gold ore", Material.GOLD_INGOT, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), true);
        ItemStack lapis = InventoryUtils.create(ChatColor.GREEN + "Lapis ore", Material.LAPIS_LAZULI, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), true);
        ItemStack iron = InventoryUtils.create(ChatColor.GREEN + "Iron ore", Material.IRON_INGOT, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), true);
        ItemStack coal = InventoryUtils.create(ChatColor.GREEN + "Coal ore", Material.COAL, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), true);
        ItemStack filler = InventoryUtils.create(" ", Material.GRAY_STAINED_GLASS_PANE, false);
        for(int i=0; i<11; i++){
            inv.setItem(i, filler);
        }
        for(int i=18; i<27; i++){
            inv.setItem(i, filler);
        }
        inv.setItem(17, filler);

        inv.setItem(1+row, diamonds);
        inv.setItem(2+row, emeralds);
        inv.setItem(3+row, redstone);
        inv.setItem(4+row, gold);
        inv.setItem(5+row, lapis);
        inv.setItem(6+row, iron);
        inv.setItem(7+row, coal);

    }

    @Override
    public Inventory getInventory() {
        return inv;
    }
}
