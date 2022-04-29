package de.zevyx.starfighter.listener;

import de.zevyx.starfighter.Starfighter;
import de.zevyx.starfighter.utils.GameState;
import de.zevyx.starfighter.utils.Util;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.KeybindComponent;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class MovementListener implements Listener {

    @EventHandler
    public void onFlightEvent(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if(!Util.isGamemodeSurvival(p)) return;
        if(Starfighter.getInstance().getGadgetManager().getEmpPlayers().contains(p)) return;
        if(Starfighter.getInstance().getGameManager().getGameState() != GameState.PLAYING) return;
        e.setCancelled(true);
        p.setGliding(true);
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("§7Du kannst dich mit der Taste §b").append(new KeybindComponent("key.swapOffhand")).append("§7 boosten.").create());
        Starfighter.getInstance().getFlightManager().addFlyingPlayer(p);
        p.setExp(1);
    }


    @EventHandler
    public void onToggleGlide(EntityToggleGlideEvent e) {
        if(e.getEntityType() == EntityType.PLAYER && Starfighter.getInstance().getFlightManager().isFlying((Player) e.getEntity())) {
            Player player = (Player) e.getEntity();
            Location loc = player.getLocation();
            Bukkit.getScheduler().runTaskLater(Starfighter.getInstance(), () -> {
                player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc, 3, 0.2, 0.2, 0.2, 0.1);
            },2);

            e.setCancelled(true);
        }
    }



}
