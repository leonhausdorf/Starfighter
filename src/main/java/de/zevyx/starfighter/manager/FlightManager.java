package de.zevyx.starfighter.manager;

import de.zevyx.starfighter.Starfighter;
import de.zevyx.starfighter.utils.GameState;
import de.zevyx.starfighter.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class FlightManager {

    private final ArrayList<Player> flyingPlayers = new ArrayList<>();

    public FlightManager() {
        Bukkit.getScheduler().runTaskTimer(Starfighter.getInstance(), () -> {
            Bukkit.getWorld("world").getPlayers().forEach(player -> {
                if(!Util.isGamemodeSurvival(player)) return;
                if(Starfighter.getInstance().getGameManager().getGameState() == GameState.PLAYING && !Starfighter.getInstance().getGadgetManager().getEmpPlayers().contains(player)) player.setAllowFlight(true);
                if(Starfighter.getInstance().getFlightManager().isFlying(player) && !player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isAir()) {
                    player.setFlying(false);
                    player.setGliding(false);
                    removeFlyingPlayer(player);
                    player.setExp(0);
                }
            });
        }, 0, 3);
    }

    public void addFlyingPlayer(Player player) {
        flyingPlayers.add(player);
    }

    public void removeFlyingPlayer(Player player) {
        flyingPlayers.remove(player);
    }

    public ArrayList<Player> getFlyingPlayers() {
        return flyingPlayers;
    }

    public boolean isFlying(Player player) {
        return flyingPlayers.contains(player);
    }

}
