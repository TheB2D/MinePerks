package com.theb2d.mineperks.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;

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
}
