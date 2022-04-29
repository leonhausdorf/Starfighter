package de.zevyx.starfighter.manager;

import de.zevyx.starfighter.utils.InventoryAnimator;
import de.zevyx.starfighter.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class InventoryManager {

    public void closeInventory(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_CLOSE, 1, 1);
        player.closeInventory();
    }

    public void openSetupInventory(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
        new InventoryAnimator(Bukkit.createInventory(player, 27, "§6§lSetup"), player, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build())
                .addItem(11, new ItemBuilder(Material.ENDER_PEARL).setName("§bSpawnpoints").build())
                .addItem(13, new ItemBuilder(Material.RED_BED).setName("§bTeams").build())
                .addItem(15, new ItemBuilder(Material.COMMAND_BLOCK).setName("§bEinstellungen").build())
                .addItem(26, new ItemBuilder(Material.BARRIER).setName("§c§lSchließen").build())
                .animate();
    }

    public void openLocationInventory(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
        new InventoryAnimator(Bukkit.createInventory(player, 27, "§6§lLocation Setup"), player, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build())
                .addItem(11, new ItemBuilder(Material.ENDER_PEARL).setName("§bWartelobby").setLore(Arrays.asList("§7Setzt deine aktuelle Position als Spawnpoint für die Wartelobby")).build())
                .addItem(15, new ItemBuilder(Material.ENDER_PEARL).setName("§bMap Spawn").setLore(Arrays.asList("§7Setzt deine aktuelle Position als Spawnpoint für die Map")).build())
                .addItem(18, new ItemBuilder(Material.ARROW).setName("§c§lZurück").build())
                .addItem(26, new ItemBuilder(Material.BARRIER).setName("§c§lSchließen").build())
                .animate();
    }

    public void openShopInventory(Player player) {
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
        new InventoryAnimator(Bukkit.createInventory(player, 27, "§6§lShop"), player, new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).build())
                .addItem(11, new ItemBuilder(Material.END_CRYSTAL).setName("§bEMP Granate").build())
                .addItem(13, new ItemBuilder(Material.NETHERITE_CHESTPLATE).setName("§bRüstung").build())
                .addItem(15, new ItemBuilder(Material.FIREWORK_ROCKET).setName("§bSuperboost").build())
                .addItem(26, new ItemBuilder(Material.BARRIER).setName("§c§lSchließen").build())
                .animate();
    }

}
