package com.theb2d.mineperks.config.invetory_events;

import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.config.inventories.MainMenu;
import com.theb2d.mineperks.config.inventories.PowerLevel;
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

    MinePerks mainClass;

    public MenuClickEvent(MinePerks main){
        this.mainClass = main;
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e){
        if (e.getCurrentItem() == null) {
            return;
        }
        MainMenu mainMenu = new MainMenu();

        Player player = (Player) e.getWhoClicked();
        Inventory currentInv = e.getInventory();

        if(e.getClickedInventory().getHolder() instanceof MainMenu) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.FIRE_CHARGE) {
                player.openInventory((new PowerLevel(mainClass)).getInventory());
            }
        }

        if(e.getClickedInventory().getHolder() instanceof PowerLevel){
            if(e.getCurrentItem().getType() == Material.BARRIER){
                player.openInventory(mainMenu.getInventory());
            }
        }

        return;

    }
}
