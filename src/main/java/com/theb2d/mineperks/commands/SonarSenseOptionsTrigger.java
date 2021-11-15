package com.theb2d.mineperks.commands;

import com.theb2d.mineperks.inventories.SonarSenseOptions;
import com.theb2d.mineperks.utils.InventoryUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class SonarSenseOptionsTrigger implements CommandExecutor {

    public static SonarSenseOptions sonarSenseOptions;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        switch(command.getName().toLowerCase()){
            case "sonarsense":
                sonarSenseOptions = new SonarSenseOptions(player);
                player.openInventory(sonarSenseOptions.getInventory());
                return true;
        }
        return false;
    }
}
