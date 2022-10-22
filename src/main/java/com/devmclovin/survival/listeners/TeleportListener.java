package com.devmclovin.survival.listeners;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.awt.*;

public class TeleportListener implements Listener {

    //TODO Implement

    @EventHandler
    public void onSignCreate(BlockPlaceEvent event) {
        Bukkit.broadcastMessage("You placed a " + event.getBlock().getType());
    }

    /**
     * Teleport player when they click on a teleport sign
     * TODO
     */
    @EventHandler
    public void onTeleport(PlayerInteractEvent event) {
        if (event.getHand() != null && event.getHand().equals(EquipmentSlot.HAND) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

            Block block = event.getClickedBlock();
            // Check if the block is a sign
            if (block.getState() instanceof Sign sign) {
                //todo
//                TextComponent text = sign.line(0);
                Bukkit.broadcastMessage("Line 0: " + sign.line(0));
            }
        }
    }
}
