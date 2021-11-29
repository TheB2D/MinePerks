package com.theb2d.mineperks.commands;

import com.theb2d.mineperks.config.inventories.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Configuration implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("mineperks")){
            Player player = (Player) sender;
            MainMenu mainMenu = new MainMenu();
            player.openInventory(mainMenu.getInventory());
            return true;
        }
        return false;
    }
}
