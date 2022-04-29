package de.zevyx.starfighter.listener;

import de.zevyx.starfighter.Starfighter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e.getEntityType() == EntityType.PLAYER
                && (e.getCause() == EntityDamageEvent.DamageCause.FALL
                || e.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL)
                && Starfighter.getInstance().getFlightManager().isFlying((Player) e.getEntity())) e.setCancelled(true);
        
    }


}
