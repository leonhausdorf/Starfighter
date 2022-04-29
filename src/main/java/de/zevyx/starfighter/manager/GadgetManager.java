package de.zevyx.starfighter.manager;

import de.zevyx.starfighter.Starfighter;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class GadgetManager {

    private ArrayList<Player> empPlayers = new ArrayList<>();

    public GadgetManager() {

    }

    public void impactEMP(Location location) {

        Firework fw = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();

        fwm.setPower(2);
        fwm.addEffect(FireworkEffect.builder().withColor(Color.BLUE).withColor(Color.AQUA).withFade(Color.GRAY).with(FireworkEffect.Type.BALL_LARGE).withFade(Color.WHITE).build());
        fw.setFireworkMeta(fwm);

        fw.detonate();

        Firework fw2 = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
        fw2.setFireworkMeta(fwm);
        fw2.detonate();

        for(Player all : Bukkit.getOnlinePlayers()) {
            if(location.distance(all.getLocation()) < 12) {
                empPlayers.add(all);
            }
        }

        for(Player all : empPlayers) {
            all.setAllowFlight(false);
            all.setGliding(false);
            all.playSound(all.getLocation(), Sound.ENTITY_ENDERMAN_DEATH, 0.5F, 1);

            new BukkitRunnable() {

                Integer count = 0;

                @Override
                public void run() {

                    if(count == 0) {
                        all.sendTitle("§cSystemausfall", "§cNeustart wird eingeleitet", 0, 40, 0);
                        all.playSound(all.getLocation(), Sound.UI_BUTTON_CLICK, 0.5F, 1);
                    } else if(count == 1) {
                        all.sendTitle("§cSystemausfall", "§cNeustart in 3..", 0, 40, 0);
                        all.playSound(all.getLocation(), Sound.UI_BUTTON_CLICK, 0.5F, 1);
                    } else if(count == 2) {
                        all.sendTitle("§cSystemausfall", "§cNeustart in 2..", 0, 40, 0);
                        all.playSound(all.getLocation(), Sound.UI_BUTTON_CLICK, 0.5F, 1);
                    } else if(count == 3) {
                        all.sendTitle("§cSystemausfall", "§cNeustart in 1..", 0, 40, 0);
                        all.playSound(all.getLocation(), Sound.UI_BUTTON_CLICK, 0.5F, 1);
                    } else if(count == 4) {
                        all.sendTitle("§aSystem online", "§aFlugfähigkeit wiederhergestellt", 0, 40, 0);
                        all.playSound(all.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.5F, 1);
                        all.setAllowFlight(true);
                        Starfighter.getInstance().getBoostManager().startCooldown(all);
                        empPlayers.remove(all);
                        this.cancel();
                    }

                    count++;
                }

            }.runTaskTimer(Starfighter.getInstance(), 0, 20L);

        }
    }

    public ArrayList<Player> getEmpPlayers() {
        return empPlayers;
    }
}
