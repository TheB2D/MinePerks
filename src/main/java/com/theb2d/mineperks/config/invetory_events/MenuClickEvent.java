package com.theb2d.mineperks.config.invetory_events;

import com.theb2d.mineperks.config.inventories.MainMenu;
import com.theb2d.mineperks.utils.InventoryUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.Collections;
import static com.theb2d.mineperks.utils.InventoryUtils.create;

public class MenuClickEvent implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        MainMenu mainMenu = new MainMenu();

        Player player = (Player) e.getWhoClicked();
        Inventory currentInv = e.getInventory();

        if(e.getClickedInventory().getHolder() instanceof MainMenu){
            e.setCancelled(true);
            if(e.getCurrentItem() == null){
                return;
            }
            if(e.getCurrentItem().getType() == Material.FIRE_CHARGE){
                // display power menu

                currentInv.clear();
                currentInv.setItem(10, create(ChatColor.YELLOW + "Detonation", Material.TNT, false));
                currentInv.setItem(11, create(ChatColor.YELLOW + "Lightning Tempo", Material.FEATHER, false));
                currentInv.setItem(26, create(ChatColor.GREEN + "Go Back", Material.BARRIER, false));
                player.updateInventory();
            }

            if(e.getCurrentItem().getType() == Material.BARRIER){
                // display main menu

                currentInv.clear();
                currentInv.setItem(11, create(ChatColor.GOLD + "Perks Power Level", Material.FIRE_CHARGE, Collections.singletonList(ChatColor.GRAY + "Adjust the power of mining perks!"), false));
                currentInv.setItem(15, create(ChatColor.GOLD + "Perks Duration", Material.CLOCK, Collections.singletonList(ChatColor.GRAY + "Adjust the duration of mining perks!"), false));
                player.updateInventory();
            }
        }
        return;

    }
}
