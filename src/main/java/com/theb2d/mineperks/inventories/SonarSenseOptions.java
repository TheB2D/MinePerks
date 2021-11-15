package com.theb2d.mineperks.inventories;

import com.theb2d.mineperks.utils.InventoryUtils;
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

import static com.theb2d.mineperks.utils.InventoryUtils.filler;

public class SonarSenseOptions implements InventoryHolder {

    private Inventory inv;
    private static ItemStack targeted_ore;

    public SonarSenseOptions(Player player){
        try{
            targeted_ore = PlayerSonarSenseBindsTo.getPlayerTargetOre(player);
        }catch(NullArgumentException e){
            ;
        }
        inv = Bukkit.createInventory(this, 27 , "Sonar sense options");
        init();
    }

    private void init(){

        int row = 9;
        String selected_ore_lore = ChatColor.BOLD + "You have this ore selected!";
        ItemStack diamond, redstone, emerald, gold, lapis, iron, coal;

        diamond = InventoryUtils.create(ChatColor.GREEN + "Diamond ore", Material.DIAMOND, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), false);
        emerald = InventoryUtils.create(ChatColor.GREEN + "Emerald ore", Material.EMERALD, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), false);
        redstone = InventoryUtils.create(ChatColor.GREEN + "Redstone ore", Material.REDSTONE, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), false);
        gold = InventoryUtils.create(ChatColor.GREEN + "Gold ore", Material.GOLD_INGOT, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), false);
        lapis = InventoryUtils.create(ChatColor.GREEN + "Lapis ore", Material.LAPIS_LAZULI, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), false);
        iron = InventoryUtils.create(ChatColor.GREEN + "Iron ore", Material.IRON_INGOT, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), false);
        coal = InventoryUtils.create(ChatColor.GREEN + "Coal ore", Material.COAL, Collections.singletonList(ChatColor.GRAY + "Click to target this ore block!"), false);

        switch(targeted_ore.getType()){
            case DIAMOND:
                diamond = InventoryUtils.create(ChatColor.GREEN + "Diamond ore", Material.DIAMOND, Collections.singletonList(selected_ore_lore), true);
                break;
            case EMERALD:
                emerald = InventoryUtils.create(ChatColor.GREEN + "Emerald ore", Material.EMERALD, Collections.singletonList(selected_ore_lore), true);
                break;
            case REDSTONE:
                redstone = InventoryUtils.create(ChatColor.GREEN + "Redstone ore", Material.REDSTONE, Collections.singletonList(selected_ore_lore), true);
                break;
            case GOLD_INGOT:
                gold = InventoryUtils.create(ChatColor.GREEN + "Gold ore", Material.GOLD_INGOT, Collections.singletonList(selected_ore_lore), true);
                break;
            case LAPIS_LAZULI:
                lapis = InventoryUtils.create(ChatColor.GREEN + "Lapis ore", Material.LAPIS_LAZULI, Collections.singletonList(selected_ore_lore), true);
                break;
            case IRON_INGOT:
                iron = InventoryUtils.create(ChatColor.GREEN + "Iron ore", Material.IRON_INGOT, Collections.singletonList(selected_ore_lore), true);
                break;
            case COAL:
                coal = InventoryUtils.create(ChatColor.GREEN + "Coal ore", Material.COAL, Collections.singletonList(selected_ore_lore), true);
                break;
        }
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
