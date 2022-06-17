package com.devmclovin.survival.listeners;

import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class AgeableInteract implements Listener {

    /**
     * Replant ageable blocks with a right click
     * EXAMPLES: Carrots, Wheat, Potatoes
     * TODO test cocoa beans
     */
    @EventHandler
    public void onReplant(PlayerInteractEvent event) {
        if (event.getHand() != null && event.getHand().equals(EquipmentSlot.HAND) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Block block = event.getClickedBlock();
            // Check if the block is harvestable
            if (block.getBlockData() instanceof Ageable data) {
                // Check if ready to harvest
                if (data.getAge() == data.getMaximumAge()) {
                    //Drop items in world
                    block.breakNaturally();
                    // Reset block
                    data.setAge(0);
                    block.setBlockData(data);
                    // Prevent player from bone mealing and harvesting in the same click
                    event.setCancelled(true);
                }
            }
        }

    }

}
