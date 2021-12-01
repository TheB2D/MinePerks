package com.theb2d.mineperks.commands;

import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.config.inventories.MainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;

public class Configuration implements CommandExecutor {

    MinePerks mainClass;

    public Configuration(MinePerks main){
        this.mainClass = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(command.getName().equalsIgnoreCase("mineperks")){
            MainMenu mainMenu = new MainMenu();
            player.openInventory(mainMenu.getInventory());
            return true;
        }
        return false;
    }
}
