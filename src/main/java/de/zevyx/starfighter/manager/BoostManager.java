package de.zevyx.starfighter.manager;

import de.zevyx.starfighter.Starfighter;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.KeybindComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BoostManager {

    private final ArrayList<Player> boosted = new ArrayList<>();

    public BoostManager() {

    }

    public void startCooldown(Player player) {
        boosted.add(player);
        player.setLevel(5);
        player.setExp(0);
        Bukkit.getScheduler().runTaskLaterAsynchronously(Starfighter.getInstance(), () -> {
            player.setLevel(4);
            player.setExp(0.2F);
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 0.2F, 1);
        }, 20);
        Bukkit.getScheduler().runTaskLaterAsynchronously(Starfighter.getInstance(), () -> {
            player.setLevel(3);
            player.setExp(0.4F);
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 0.2F, 1);
        }, 20 * 2);
        Bukkit.getScheduler().runTaskLaterAsynchronously(Starfighter.getInstance(), () -> {
            player.setLevel(2);
            player.setExp(0.6F);
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 0.2F, 1);
        }, 20 * 3);
        Bukkit.getScheduler().runTaskLaterAsynchronously(Starfighter.getInstance(), () -> {
            player.setLevel(1);
            player.setExp(0.8F);
            player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 0.2F, 1);
        }, 20 * 4);
        Bukkit.getScheduler().runTaskLaterAsynchronously(Starfighter.getInstance(), () -> {
            if(boosted.contains(player))
                boosted.remove(player);
            player.setExp(1);
            player.setLevel(0);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.7F, 2);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder("§7Dein Boost ist wieder einsatzbereit").create());
        }, 20 * 5);
    }

    public boolean isOnCooldown(Player player) {
        return boosted.contains(player);
    }


}
