package com.theb2d.mineperks;

import com.theb2d.mineperks.Perks.*;
import com.theb2d.mineperks.commands.SonarSenseOptionsTrigger;
import com.theb2d.mineperks.events.Mining;
import com.theb2d.mineperks.inventories.SonarSenseOptions;
import com.theb2d.mineperks.inventory_events.SonarSenseOptionsClick;
import com.theb2d.mineperks.perks_events.DetonationEvents;
import com.theb2d.mineperks.perks_events.ProsperityEvents;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

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
