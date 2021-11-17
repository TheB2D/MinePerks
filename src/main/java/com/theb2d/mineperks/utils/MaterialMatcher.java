package com.theb2d.mineperks.utils;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class MaterialMatcher {

    public static Material matchMaterialOreForm(Material item_form){
        return Material.matchMaterial(matchMaterialOreFormStr(item_form, false));
    }

    public static String matchMaterialOreFormStr(Material item_form, Boolean proper){
        String val = item_form.toString().replace("_", "").replace("INGOT", "").replace("LAZULI", "").toLowerCase();
        if(proper){
            return StringUtils.capitalize(val + " ore");
        }
        return val + " ore";
    }

}
