package de.zevyx.starfighter.listener;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class GadgetListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if(event.getEntity().getShooter() instanceof Player) {
            if(event.getEntityType().equals(EntityType.ARROW)) {
                if(event.getHitBlock() == null) {
                    Location loc = event.getHitEntity().getLocation();
                } else if (event.getHitEntity() == null) {
                    Location loc = event.getHitBlock().getLocation();
                }

            }
        }
    }

}
