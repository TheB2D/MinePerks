package com.theb2d.mineperks.config;

import com.theb2d.mineperks.MinePerks;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.logging.Level;

public class Config {

    public static MinePerks mainClass;
    private static FileConfiguration fileConfiguration;
    private static File config_file = new File("config.yml");

    public Config(MinePerks main){
        this.mainClass = main;
    }

    public static boolean setObjParameter(String path, Object value){
        fileConfiguration = mainClass.getConfig();
        fileConfiguration.set(path, value);
        try{
            fileConfiguration.save(config_file);
        }catch (java.io.IOException e){
            mainClass.getLogger().log(Level.SEVERE, "[MinePerks] Something happened while saving config.yml " + e.getMessage() );
            return false;
            // operation failed
        }
        return true;
    }

    public static Object getObjParameter(String path){
        return fileConfiguration.get(path);
    }

}
