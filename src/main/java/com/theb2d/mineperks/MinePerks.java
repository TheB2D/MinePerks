package com.theb2d.mineperks;

import com.theb2d.mineperks.Perks.*;
import com.theb2d.mineperks.commands.Configuration;
import com.theb2d.mineperks.commands.SonarSenseOptionsTrigger;
import com.theb2d.mineperks.config.invetory_events.MenuClickEvent;
import com.theb2d.mineperks.events.Mining;
import com.theb2d.mineperks.inventory_events.SonarSenseOptionsClick;
import com.theb2d.mineperks.perks_events.DetonationEvents;
import com.theb2d.mineperks.perks_events.HerculesEvents;
import com.theb2d.mineperks.perks_events.ProsperityEvents;
import com.theb2d.mineperks.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;

public final class MinePerks extends JavaPlugin {

    public ProsperityEvents prosperityEvents;
    public DetonationEvents detonationEvents;
    public HerculesEvents herculesEvents;

    public Mining miningEvent;
    public Detonation detonationPerk;
    public Prosperity prosperityPerk;
    public MinersAura minersAura;
    public LightningTempo lightningTempo;
    public SonarSense sonarSense;
    public SonarSenseOptionsTrigger sonarSenseOptionsTrigger;
    public SonarSenseOptionsClick sonarSenseOptionsClick; //TODO
    private HerculesMight herculesMight;
    private boolean useHolographicDisplays;
    private Configuration configuration;
    private Config config;
    private MenuClickEvent menuClickEvent;

    public void initConfig(){
        this.saveDefaultConfig();
    }

    @Override
    public void onEnable() {

        config = new Config(this);
        configuration = new Configuration(this);

        lightningTempo = new LightningTempo(this);
        detonationEvents = new DetonationEvents(this);
        detonationPerk = new Detonation(this);
        miningEvent = new Mining(this);
        prosperityEvents = new ProsperityEvents(this);
        prosperityPerk = new Prosperity(this);
        minersAura = new MinersAura(this);
        sonarSense = new SonarSense(this);
        sonarSenseOptionsClick = new SonarSenseOptionsClick(this);
        herculesMight = new HerculesMight(this);
        herculesEvents = new HerculesEvents(this);
        menuClickEvent = new MenuClickEvent(this);

        // commands
        sonarSenseOptionsTrigger = new SonarSenseOptionsTrigger();


        getCommand("sonarsense").setExecutor(sonarSenseOptionsTrigger);
        getCommand("mineperks").setExecutor(configuration);

        useHolographicDisplays = Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays");

        getServer().getPluginManager().registerEvents(miningEvent, this);
        getServer().getPluginManager().registerEvents(detonationEvents, this);
        getServer().getPluginManager().registerEvents(prosperityEvents, this);
        getServer().getPluginManager().registerEvents(sonarSenseOptionsClick, this);
        getServer().getPluginManager().registerEvents(herculesEvents, this);
        getServer().getPluginManager().registerEvents(menuClickEvent, this);
        initConfig();

        getServer().getConsoleSender().sendMessage("[MinePerks] MinePerks has been enabled!" + ChatColor.GREEN);
    }

    @Override
    public void onDisable() {
        HerculesMight.destroyArmorstandsDeployed();
        this.saveConfig();
        getServer().getConsoleSender().sendMessage("[MinePerks] MinePerks has been enabled!" + ChatColor.RED);
    }
}
