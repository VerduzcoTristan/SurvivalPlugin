package com.devmclovin.survival.beacon;

import com.destroystokyo.paper.event.block.BeaconEffectEvent;
import com.devmclovin.survival.SurvivalPlugin;
import org.bukkit.block.Beacon;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class BeaconRange implements Listener {
    private final List<Beacon> beaconList;
    private final SurvivalPlugin plugin;

    public BeaconRange(SurvivalPlugin plugin) {
        this.beaconList = new ArrayList<>();
        this.plugin = plugin;
    }

    public void updateBeaconRange(Block block) {

        ConfigurationSection config = plugin.getConfig().getConfigurationSection("beacon-range");

        Beacon beacon = (Beacon) block.getState();

        String range = String.valueOf(beacon.getTier());

        beacon.setEffectRange(config.getInt("t" + range));

        beacon.update();
        beaconList.add(beacon);
    }

    @EventHandler
    public void onBeaconEffect(BeaconEffectEvent event) {
        Block beacon =  event.getBlock();
        if (!beaconList.contains(beacon)) {
            updateBeaconRange(beacon);
        }
    }
}
