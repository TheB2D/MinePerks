package com.theb2d.mineperks.utils;

import com.theb2d.mineperks.Perks.*;
import org.bukkit.Sound;

import org.bukkit.entity.Player;

import java.util.Random;

public class ActivatePerk {

    public static void setPerk(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
        Random rand_int = new Random();
        int chance = rand_int.nextInt(5);
        switch(chance){
            case 1:
                if(!Detonation.getPlayersAffected().contains(player)) {
                    Detonation.apply(player);
                }else{setPerk(player);}
                break;
            case 2:
                if(!LightningTempo.getPlayersAffected().contains(player)) {
                    LightningTempo.apply(player);
                }else{setPerk(player);}
                break;
            case 3:
                if(!MinersAura.getPlayersAffected().contains(player)) {
                    MinersAura.apply(player);
                }else{setPerk(player);}
                break;
            case 4:
                if(!Prosperity.getPlayersAffected().contains(player)) {
                    Prosperity.apply(player);
                }else{setPerk(player);}
                break;
            case 5:
                if(!SonarSense.getPlayersAffected().contains(player)){
                    SonarSense.apply(player);
                }else{setPerk(player);}
                break;
        }
    }
}
