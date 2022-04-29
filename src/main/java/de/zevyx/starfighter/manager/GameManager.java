package de.zevyx.starfighter.manager;

import de.zevyx.starfighter.Starfighter;
import de.zevyx.starfighter.utils.GameState;
import de.zevyx.starfighter.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class GameManager {

    private static GameState gameState = GameState.SETUP;

    public GameManager() {

    }

    public void startGame() {
        Starfighter.getInstance().getGameManager().setGameState(GameState.PLAYING);
        for(Player all : Bukkit.getOnlinePlayers()) {

            Starfighter.getInstance().getEconomyManager().initializePlayer(all);

            all.getInventory().setItem(2, new ItemBuilder(Material.STICK).setName("§bLaser Blaster").build());
            all.getInventory().setItem(4, new ItemBuilder(Material.BARRIER).setName("§cKein Gadget").build());
            all.getInventory().setItem(6, new ItemBuilder(Material.CHEST).setName("§bShop").build());
            all.getInventory().setItem(8, new ItemBuilder(Material.END_CRYSTAL).setName("§bEMP Granate").setLore(Arrays.asList("§bMacht einen Gegner für kurze Zeit Flugunfähig.", " ", "§7§iRechtsklick um zu werfen")).build());
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState state) {
        gameState = state;
    }

}
