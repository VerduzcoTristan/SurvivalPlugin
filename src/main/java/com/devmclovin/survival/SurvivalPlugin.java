package com.devmclovin.survival;

import com.devmclovin.survival.listeners.AgeableInteract;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new AgeableInteract(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
