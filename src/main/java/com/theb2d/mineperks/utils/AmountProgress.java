package com.theb2d.mineperks.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static com.theb2d.mineperks.utils.ActivatePerk.setPerk;

public class AmountProgress {

    public static int max = 100;

    public static HashMap<Player, Integer> playerStatusBinds = new HashMap<Player, Integer>();

    public static void setPlayerBind(Player player, int amount){
        if(playerStatusBinds.containsKey(player)){
            playerStatusBinds.put(player, amount);
        }
        else{
            playerStatusBinds.put(player, 0);
        }
    }

    public static int getPlayerBind(Player player){
        if(playerStatusBinds.containsKey(player)){return playerStatusBinds.get(player);}
        return 0;
    }

    public static boolean addPlayerAmount(Player player, int amount){
        if(getPlayerBind(player)!=0){
            int current_progress = getPlayerBind(player);

            if(current_progress+amount>max){
                setPlayerBind(player, 1);
                return false;
            }else {
                setPlayerBind(player, current_progress + amount);
            }
        }else{
            setPlayerBind(player, 1);
        }
        return true;
    }

    public static void showProgress(Player player){
        String progress_bar = ProgressBar.getProgressBar(AmountProgress.getPlayerBind(player), AmountProgress.max, 50, '|', ChatColor.AQUA, ChatColor.GRAY);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(progress_bar));
    }

    public static void triggerProgress(Player player){
        if(!addPlayerAmount(player, 1)){
            setPerk(player);
            return;
        }else{
            showProgress(player);
        }
    }
}
