package com.theb2d.mineperks.inventories;

import com.theb2d.mineperks.utils.InventoryUtils;
import com.theb2d.mineperks.utils.MaterialMatcher;
import com.theb2d.mineperks.utils.PlayerSonarSenseBindsTo;
import org.apache.commons.lang.NullArgumentException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static com.theb2d.mineperks.utils.InventoryUtils.filler;

public class SonarSenseOptions implements InventoryHolder {

    private Inventory inv;

    public SonarSenseOptions(Player player){
        inv = Bukkit.createInventory(this, 27 , "Sonar sense options");
        init(player);
    }

    private void init(Player player){


        ItemStack targeted_ore = PlayerSonarSenseBindsTo.getPlayerTargetOre(player, false);

        int row = 9;
        String selected_ore_lore = ChatColor.GRAY + "You have this ore selected!";
        List<String> not_selected_lore = Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!");
        ItemStack diamond, redstone, emerald, gold, lapis, iron, coal, selected_ore;


        selected_ore = InventoryUtils.create(ChatColor.GREEN + MaterialMatcher.matchMaterialOreFormStr(targeted_ore.getType(), true), MaterialMatcher.matchMaterialOreForm(targeted_ore.getType()), Collections.singletonList(selected_ore_lore), true);


        diamond = selected_ore.getType()!=Material.DIAMOND_ORE ? InventoryUtils.create(ChatColor.GREEN + "Diamond ore", Material.DIAMOND, not_selected_lore, false) : selected_ore;
        emerald = selected_ore.getType()!=Material.EMERALD_ORE ? InventoryUtils.create(ChatColor.GREEN + "Emerald ore", Material.EMERALD, not_selected_lore, false) : selected_ore;
        redstone = selected_ore.getType()!=Material.REDSTONE_ORE ? InventoryUtils.create(ChatColor.GREEN + "Redstone ore", Material.REDSTONE, not_selected_lore, false) : selected_ore;
        gold = selected_ore.getType()!=Material.GOLD_ORE ? InventoryUtils.create(ChatColor.GREEN + "Gold ore", Material.GOLD_INGOT, not_selected_lore, false) : selected_ore;
        lapis = selected_ore.getType()!=Material.LAPIS_ORE ? InventoryUtils.create(ChatColor.GREEN + "Lapis Lazuli ore", Material.LAPIS_LAZULI, not_selected_lore, false) : selected_ore;
        iron = selected_ore.getType()!=Material.IRON_ORE ? InventoryUtils.create(ChatColor.GREEN + "Iron ore", Material.IRON_INGOT, not_selected_lore, false) : selected_ore;
        coal = selected_ore.getType()!=Material.COAL_ORE ? InventoryUtils.create(ChatColor.GREEN + "Coal ore", Material.COAL, not_selected_lore, false) : selected_ore;



        for(int i=0; i<11; i++){
            inv.setItem(i, filler);
        }
        for(int i=18; i<27; i++){
            inv.setItem(i, filler);
        }
        inv.setItem(17, filler);

        inv.setItem(1+row, diamond);
        inv.setItem(2+row, emerald);
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
