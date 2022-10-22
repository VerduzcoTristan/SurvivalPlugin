package com.devmclovin.survival;

import com.devmclovin.survival.beacon.BeaconRange;
import com.devmclovin.survival.listeners.AgeableInteract;
import com.devmclovin.survival.listeners.NetheriteLuck;
import com.devmclovin.survival.listeners.SkullLuck;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalPlugin extends JavaPlugin {

    //todo change drop rates for wither skulls and drop rates of ancient depbri scrap
    // BEACON EXTENDER
    // Anvil too expensive limit removed
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new AgeableInteract(), this);
        getServer().getPluginManager().registerEvents(new NetheriteLuck(), this);
        getServer().getPluginManager().registerEvents(new SkullLuck(), this);
        getServer().getPluginManager().registerEvents(new BeaconRange(this, beaconList), this);
        //getServer().getPluginManager().registerEvents(new TeleportListener(), this);
        //getServer().getPluginManager().registerEvents(new BetterBeacon(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getScheduler().cancelTasks(this);
    }
}
