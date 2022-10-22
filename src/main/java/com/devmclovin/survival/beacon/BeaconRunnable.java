package com.devmclovin.survival.beacon;

import com.devmclovin.survival.SurvivalPlugin;
import org.bukkit.ChunkSnapshot;
import org.bukkit.scheduler.BukkitRunnable;

public class BeaconRunnable extends BukkitRunnable {

    private final ChunkSnapshot chunk;
    private final BeaconRange cl;
    private final SurvivalPlugin pl;

    public BeaconRunnable(ChunkSnapshot chunk, BeaconRange cl, SurvivalPlugin plugin) {
    this.chunk = chunk;
    this.cl = cl;
    this.pl = plugin;
    }

    //only check highest block to look for active beacons
    @Override
    public void run() {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int y = chunk.getHighestBlockYAt(x, z);

                if (y == -1)
                    continue;

                String type = chunk.getBlockType(x, y, z).toString();
                if (type.equals("BEACON")){
                    sendToMainThread(chunk.getWorldName(), x, y, z);
                }
            }
        }
    }

    private void sendToMainThread(String world, int x, int y, int z){
        new BukkitRunnable() {
            @Override
            public void run() {
                cl.updateBeaconRange(world, x, y, z);
            }
        }.runTask(pl);
    }
}

