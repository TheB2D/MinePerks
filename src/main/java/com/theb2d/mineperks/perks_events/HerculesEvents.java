package com.theb2d.mineperks.perks_events;

import com.theb2d.mineperks.MinePerks;
import com.theb2d.mineperks.Perks.HerculesMight;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class HerculesEvents implements Listener {

    private static MinePerks mainClass;

    public HerculesEvents(MinePerks main){
        this.mainClass = main; //main instance
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        if(e.getAction()== Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock().getType()== Material.BEDROCK){
                Player player = e.getPlayer();
                HerculesMight.loopAnimation(player, e.getClickedBlock().getLocation());
            }
        }
    }
}
