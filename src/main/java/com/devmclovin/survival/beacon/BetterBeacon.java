package com.devmclovin.survival.beacon;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BetterBeacon implements Listener {

    //TODO Implement

    private List<Block> blockList;
    private HashMap<String, Integer> chunkList;

    public BetterBeacon() {
        this.blockList = new ArrayList<>();
        this.chunkList = new HashMap<>();
    }

    /**
     * @param id - ID of the chunk WORLD:X:Z
     * @return count - number if beacons in chunk
     */
    private int beaconsInChunk(String id) {

        String[] split = id.split(":");
        World world = Bukkit.getWorld(split[0]);
        Chunk chunk = world.getChunkAt(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        int count = 0;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = world.getMinHeight(); y < world.getMaxHeight(); y++) {
                    Block block = chunk.getBlock(x, y, z);
                    if (block.getType() == Material.BEACON) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    //Beacon adding methods todo world is null on server start

    @EventHandler
    public void onBeaconLoad(ChunkLoadEvent event) {
        Chunk chunk = event.getChunk();
        //todo see if multiple beacons
        if (chunk.contains(Material.BEACON.createBlockData())) {
            String id = chunk.getWorld() + ":" + chunk.getX() + ":" + chunk.getZ();

            if (!chunkList.keySet().contains(id)) {
                int count = beaconsInChunk(id);
                chunkList.put(id, count);
                Bukkit.broadcastMessage("We r loaded my mane");
            }
            System.out.println("Count: " + chunkList.values().stream().mapToInt(Integer::intValue).sum());
        }
    }

    @EventHandler
    public void onBeaconPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();

        if (block.getType() == Material.BEACON) {
            Chunk chunk = block.getChunk();
            String id = chunk.getWorld() + ":" + chunk.getX() + ":" + chunk.getZ() + ":";

            if (!chunkList.keySet().contains(id)) {
                chunkList.put(id, 1);
                Bukkit.broadcastMessage("We r placed my mane");
            } else {
                int count = chunkList.get(id);
                count++;
                chunkList.put(id, count);
            }
            System.out.println("Count: " + chunkList.values().stream().mapToInt(Integer::intValue).sum());
        }
    }

    //Beacon removal method
//todo fix
    @EventHandler
    public void onBeaconBreak(BlockBreakEvent event) {
        Block block = event.getBlock();

        if (block.getType() == Material.BEACON) {
            Chunk chunk = block.getChunk();
            String id = chunk.getWorld() + ":" + chunk.getX() + ":" + chunk.getZ();

            if (chunkList.keySet().contains(id)) {
                int count = chunkList.get(id);

                if (count == 1) {
                    chunkList.remove(id);
                } else {
                    count--;
                    chunkList.put(id, count);
                }
            }
            System.out.println("Count: " + chunkList.values().stream().mapToInt(Integer::intValue).sum());
        }
    }

}
