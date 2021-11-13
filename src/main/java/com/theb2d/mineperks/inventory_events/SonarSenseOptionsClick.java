package com.theb2d.mineperks.inventory_events;

import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.commands.SonarSenseOptionsTrigger;
import com.theb2d.mineperks.inventories.SonarSenseOptions;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public class SonarSenseOptionsClick implements Listener {

    private static MinePerks mainClass;

    public SonarSenseOptionsClick(MinePerks main){
        this.mainClass = main; //main instance
    }


    @EventHandler
    public void onClick(InventoryClickEvent e){

        Player player = (Player) e.getWhoClicked();
        if(e.getClickedInventory() == null){return;}
        if(e.getClickedInventory().getHolder() instanceof SonarSenseOptions){
            Consumer<String> sendMsg = ore -> {player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l♪♪♪ &8► &7Your sonar sense has been set to track " + ore + " ores")); return;};
            switch(e.getCurrentItem().getType()){
                case DIAMOND:
                    sendMsg.accept("diamond");
                    break;
                case EMERALD:
                    sendMsg.accept("emerald");
                    break;
                case LAPIS_LAZULI:
                    sendMsg.accept("lapis lazuli");
                    break;
                case GOLD_INGOT:
                    sendMsg.accept("gold");
                    break;
                case REDSTONE:
                    sendMsg.accept("redstone");
                    break;
                case IRON_INGOT:
                    sendMsg.accept("iron");
                    break;
                case COAL:
                    sendMsg.accept("coal");
                    break;
                default:
                    e.setCancelled(true);
                    return;
            }
            e.setCancelled(true);
            player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_PLACE, 1.0F, 1.0F);
            player.closeInventory();
            return;
        }
    }
}
