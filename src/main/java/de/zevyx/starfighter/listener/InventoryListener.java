package de.zevyx.starfighter.listener;

import de.zevyx.starfighter.Starfighter;
import de.zevyx.starfighter.utils.ItemBuilder;
import de.zevyx.starfighter.utils.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventorySwap(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();
        e.setCancelled(true);
        if(!Starfighter.getInstance().getFlightManager().isFlying(p)) return;
        if(Starfighter.getInstance().getBoostManager().isOnCooldown(p)) return;
        Starfighter.getInstance().getBoostManager().startCooldown(p);
        p.setVelocity(p.getLocation().getDirection().multiply(2));
        p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 0.7F, 1);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(Util.isGamemodeSurvival(p)) e.setCancelled(true);
        try {

            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lSchließen")) Starfighter.getInstance().getInventoryManager().closeInventory(p);
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bSpawnpoints")) Starfighter.getInstance().getInventoryManager().openLocationInventory(p);
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bWartelobby")) {
                Starfighter.getInstance().getLocationManager().setLocation("lobby", p.getLocation());
                Starfighter.getInstance().getInventoryManager().closeInventory(p);
                p.sendMessage("§7Du hast deine Position als §bWartelobby-Spawnpoint §7gesetzt!");
            }

            if(e.getView().getTitle().equalsIgnoreCase("§6§lLocation Setup")) {
                if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lZurück")) Starfighter.getInstance().getInventoryManager().openSetupInventory(p);
            }

        } catch (Exception ex) {

        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        try {
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§bShop")) {
                    Starfighter.getInstance().getInventoryManager().openShopInventory(player);
                }

                if(player.getInventory().getItemInMainHand().getItemMeta().getLore().contains("§7§iRechtsklick um zu werfen")) {

                    ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, 0.5, 0), EntityType.ARMOR_STAND);
                    as.setArms(true);
                    as.setGravity(false);
                    as.setVisible(false);
                    as.setSmall(true);
                    as.setMarker(true);
                    as.setItemInHand(new ItemBuilder(Material.END_CRYSTAL).setName("EMPGRANADE").build());
                    as.setRightArmPose(new EulerAngle(Math.toRadians(90), Math.toRadians(0), Math.toRadians(0)));

                    player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);

                    Location destination = player.getLocation().add(player.getLocation().getDirection().multiply(20));
                    Vector v = destination.subtract(player.getLocation()).toVector();

                    new BukkitRunnable() {

                        @Override
                        public void run() {

                            as.teleport(as.getLocation().add(v.normalize()));

                            if(!as.getTargetBlockExact(1).isPassable()) {
                                Starfighter.getInstance().getGadgetManager().impactEMP(as.getLocation());
                                as.remove();
                                cancel();
                            }

                            for(Entity entity : as.getLocation().getChunk().getEntities()) {
                                if(!as.isDead()) {
                                    if(as.getLocation().distanceSquared(entity.getLocation()) <= 1) {
                                        if(entity != player && entity != as) {
                                            if(entity instanceof LivingEntity) {
                                                Starfighter.getInstance().getGadgetManager().impactEMP(as.getLocation());
                                                as.remove();
                                            }
                                        }
                                    }
                                }
                            }

                        }

                    }.runTaskTimer(Starfighter.getInstance(), 0L, 1L);


                }
            }
        } catch (Exception ex) {

        }
    }

}
