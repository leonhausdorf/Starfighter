package de.zevyx.starfighter.utils;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class Util {

    public static boolean isGamemodeSurvival(Player p) {
        return p.getGameMode() == GameMode.SURVIVAL;
    }

}
