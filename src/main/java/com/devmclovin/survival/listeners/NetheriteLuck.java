package com.devmclovin.survival.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;

public class NetheriteLuck implements Listener {
    // Improve furnace smelt rates of ancient debris
    @EventHandler
    public void onLuck(FurnaceSmeltEvent event) {

        Block furnace = event.getBlock();
        ItemStack result = event.getResult();

        if (result.getType() == Material.NETHERITE_SCRAP) {

            Block under = furnace.getWorld().getBlockAt(furnace.getLocation().add(0, -1, 0));

            //50% chance for extra scrap
            if (under.getType() == Material.DIAMOND_BLOCK) {
                if (Math.random() >= 0.5D){
                    return;
                }
            //100% chance for extra scrap
            } else if (under.getType() == Material.NETHERITE_BLOCK) {
            } else {
                return;
            }
            ItemStack item = new ItemStack(Material.NETHERITE_SCRAP, 2);
            event.setResult(item);
        }
    }
}
