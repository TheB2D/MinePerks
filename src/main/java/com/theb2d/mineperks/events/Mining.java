package com.theb2d.mineperks.events;

import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.utils.ActivatePerk;
import com.theb2d.mineperks.utils.AmountProgress;
import com.theb2d.mineperks.utils.ProgressBar;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class Mining implements Listener {

    private static MinePerks mainClass;

    public Mining(MinePerks main){
        this.mainClass = main; //main instance
    }

    @EventHandler
    public static void onBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();


        Random rand_int = new Random();

        boolean chance = rand_int.nextInt(25)==6;  // maybe used

        if(true){ // TODO: change true to chance if you want to make getting points harder
            AmountProgress.triggerProgress(player);
        }
    }
}