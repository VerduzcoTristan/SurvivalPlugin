package com.devmclovin.survival.beacon;

import com.destroystokyo.paper.event.block.BeaconEffectEvent;
import com.devmclovin.survival.SurvivalPlugin;
import org.bukkit.block.Beacon;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class BeaconRange implements Listener {
    private final SurvivalPlugin plugin;
    private final List<Beacon> beaconList;

    //todo check for beacon activate event
    public BeaconRange(SurvivalPlugin plugin) {
        this.plugin = plugin;
        this.beaconList = new ArrayList<>();
    }

    public void updateBeaconRange(String world, int x, int y, int z) {
        Beacon beacon = (Beacon) plugin.getServer().getWorld(world).getBlockAt(x, y, z);
        int tier = beacon.getTier();
        double range = beacon.getEffectRange();
        System.out.println("tier: " + tier);
        System.out.println("range: " + range);
        //beacon.setEffectRange();
    }

    @EventHandler
    public void onBeaconEffect(BeaconEffectEvent event){

    }

    /*
    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        ChunkSnapshot snapshot = event.getChunk().getChunkSnapshot(true, false, false);

        new BeaconRunnable(snapshot, this, plugin).runTaskAsynchronously(plugin);
    }*/
}
