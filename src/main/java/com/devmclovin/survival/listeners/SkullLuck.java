package com.devmclovin.survival.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class SkullLuck implements Listener {
    //todo maybe modify collection drops
    //Double wither skull drops with netherite tools
    @EventHandler
    public void onSkullDrop(EntityDeathEvent event) {
        Entity target = event.getEntity();

        if (target.getType() == EntityType.WITHER_SKELETON) {
            if (target.isDead()) {
                if (event.getDrops().stream().anyMatch(item -> item.getType() == Material.WITHER_SKELETON_SKULL)) {
                    if (event.getEntity().getKiller() instanceof Player) {
                        Player player = (Player) event.getEntity().getKiller();
                        ItemStack weapon = player.getInventory().getItemInMainHand();
                        if (weapon.getType().toString().contains("NETHERITE")) {
                            event.getDrops().removeIf(item -> Material.WITHER_SKELETON_SKULL == item.getType());
                            Location loc = target.getLocation();
                            loc.getWorld().dropItemNaturally(loc, new ItemStack(Material.WITHER_SKELETON_SKULL, 2));
                        }
                    }
                }
            }
        }
    }
}

