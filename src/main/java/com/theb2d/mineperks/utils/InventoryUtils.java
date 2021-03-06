package com.theb2d.mineperks.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class InventoryUtils {

    public static ItemStack filler = InventoryUtils.create(" ", Material.GRAY_STAINED_GLASS_PANE, false);

    public static ItemStack create(String name, Material mat, List<String> lore, boolean isShiny){
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        if(isShiny){
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack create(String name, Material mat, boolean isShiny, int data){
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setCustomModelData(data);
        if(isShiny){
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack create(String name, Material mat, String lore, boolean isShiny, int data){
        List<String> lore_list = Arrays.asList(lore.split("`"));
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setCustomModelData(data);
        meta.setLore(lore_list);
        if(isShiny){
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack create(String name, Material mat, boolean isShiny){
        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if(isShiny){
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack create(String name, Material mat, String lore, boolean isShiny){
        List<String> lore_list = Arrays.asList(lore.split("`"));

        ItemStack item = new ItemStack(mat, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore_list);
        if(isShiny){
            meta.addEnchant(Enchantment.LUCK, 1, false);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        item.setItemMeta(meta);
        return item;
    }

    public static void fillFiller(Inventory other_inv){
        int size;
        size = other_inv.getSize();
        for (int i = 0; i < size; i++) {
            if(other_inv.getItem(i)==null){
                other_inv.setItem(i, filler);
            }
        }
    }
}
