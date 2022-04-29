package de.zevyx.starfighter.listener;

import de.zevyx.starfighter.Starfighter;
import de.zevyx.starfighter.utils.GameState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConnectListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if(p.hasPlayedBefore()) {

        }

        if(Starfighter.getInstance().getGameManager().getGameState() == GameState.SETUP) {
            if(Starfighter.getInstance().getLocationManager().exists("lobby")) {
                p.teleport(Starfighter.getInstance().getLocationManager().getLocation("lobby"));
            } else {
                p.sendMessage("§cEs wurde noch kein Wartelobby Spawnpoint gesetzt. Bitte führe /setup aus.");
            }
        }

        p.getInventory().clear();

    }

}
