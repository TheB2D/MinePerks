package com.theb2d.mineperks;

import com.theb2d.mineperks.Perks.*;
import com.theb2d.mineperks.commands.SonarSenseOptionsTrigger;
import com.theb2d.mineperks.events.Mining;
import com.theb2d.mineperks.inventories.SonarSenseOptions;
import com.theb2d.mineperks.inventory_events.SonarSenseOptionsClick;
import com.theb2d.mineperks.perks_events.DetonationEvents;
import com.theb2d.mineperks.perks_events.ProsperityEvents;
import com.theb2d.mineperks.utils.PlayerSonarSenseBindsTo;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class MinePerks extends JavaPlugin {

    public Mining miningEvent;
    public DetonationEvents detonationEvents;
    public Detonation detonationPerk;
    public Prosperity prosperityPerk;
    public ProsperityEvents prosperityEvents;
    public MinersAura minersAura;
    public LightningTempo lightningTempo;
    public SonarSense sonarSense;
    public SonarSenseOptionsTrigger sonarSenseOptionsTrigger;
    public static SonarSenseOptionsClick sonarSenseOptionsClick; //TODO

    public static File file;
    public static FileConfiguration players;

    @Override
    public void onEnable() {

        lightningTempo = new LightningTempo(this);
        detonationEvents = new DetonationEvents(this);
        detonationPerk = new Detonation(this);
        miningEvent = new Mining(this);
        prosperityEvents = new ProsperityEvents(this);
        prosperityPerk = new Prosperity(this);
        minersAura = new MinersAura(this);
        sonarSense = new SonarSense(this);
        sonarSenseOptionsTrigger = new SonarSenseOptionsTrigger();
        sonarSenseOptionsClick = new SonarSenseOptionsClick(this);

        File file = new File("player_binds.yml");
        FileConfiguration players = YamlConfiguration.loadConfiguration(file);

        getCommand("sonarsense").setExecutor(sonarSenseOptionsTrigger);

        getServer().getPluginManager().registerEvents(miningEvent, this);
        getServer().getPluginManager().registerEvents(detonationEvents, this);
        getServer().getPluginManager().registerEvents(prosperityEvents, this);
        getServer().getPluginManager().registerEvents(sonarSenseOptionsClick, this);
        getServer().getConsoleSender().sendMessage("[MinePerks] MinePerks has been enabled!" + ChatColor.GREEN);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("[MinePerks] MinePerks has been enabled!" + ChatColor.RED);
    }
}
