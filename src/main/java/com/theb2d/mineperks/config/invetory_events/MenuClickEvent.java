package com.theb2d.mineperks.config.invetory_events;

import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.commands.Configuration;
import com.theb2d.mineperks.config.inventories.MainMenu;
import com.theb2d.mineperks.config.inventories.PowerLevel;
import com.theb2d.mineperks.utils.InventoryUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.io.File;
import java.util.Collections;
import java.util.function.Consumer;

import static com.theb2d.mineperks.utils.InventoryUtils.create;

public class MenuClickEvent implements Listener {

    MinePerks mainClass;

    public MenuClickEvent(MinePerks main){
        this.mainClass = main;
    }


    private void editConfig(Player player, FileConfiguration config, String path, Boolean isInt){
        Prompt ask = new StringPrompt() {
            @Override
            public String getPromptText(ConversationContext context) {
                player.sendTitle("Editing", "config.yml...", 1, 600, 1);
                return ChatColor.translateAlternateColorCodes('&', "§l§e(!)§r§7 Please enter value or enter §c\"cancel\"&7 to cancel:");
            }

            @Override
            public Prompt acceptInput(ConversationContext context, String input) {
                config.set(path, Integer.parseInt(input));
                player.resetTitle();
                player.openInventory(new PowerLevel(mainClass).getInventory());
                return Prompt.END_OF_CONVERSATION;
            }

        };

        ConversationFactory factory = new ConversationFactory(mainClass); // We need our plugin reference here
        factory.withLocalEcho(false);
        factory.withFirstPrompt(ask);

        Conversation conversation = factory.buildConversation(player);
        conversation.begin();
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e){

        FileConfiguration config = mainClass.getConfig();

        if (e.getCurrentItem() == null) {
            return;
        }
        MainMenu mainMenu = new MainMenu();

        Player player = (Player) e.getWhoClicked();
        Inventory currentInv = e.getInventory();

        if(e.getClickedInventory().getHolder() instanceof MainMenu) {
            e.setCancelled(true);
            if (e.getCurrentItem().getType() == Material.FIRE_CHARGE) {
                player.openInventory(new PowerLevel(mainClass).getInventory());
            }
        }

        if(e.getClickedInventory().getHolder() instanceof PowerLevel){
            Consumer<String> editPower = (path) -> {player.closeInventory(); editConfig(player, config, "perks." + path + ".power", true);};

            e.setCancelled(true);
            switch(e.getCurrentItem().getType()){
                case BARRIER: // go back
                    player.openInventory(mainMenu.getInventory());
                    break;
                case TNT: // detonation
                    editPower.accept("detonation");
                    break;
                case FEATHER: // lightning_tempo
                    editPower.accept("lightning_tempo");
                    break;
                case BLAZE_POWDER: // miners_aura
                    editPower.accept("miners_aura");
            }
        }

        return;

    }
}
