package de.zevyx.starfighter.manager;

import de.zevyx.starfighter.Starfighter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

public class MessageManager {

    private File file;
    private YamlConfiguration config;

    public MessageManager() {
        this.file = new File(Starfighter.getInstance().getDataFolder(), "messages.yml");
        this.config = YamlConfiguration.loadConfiguration(file);

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(String path) {
        return config.contains(path);
    }

    public void initialize() {
        if(exists("prefix")) return;
        insertMessage("prefix", "&8[&bStarfighter&8] &7");
        insertMessage("no-permission", "&cDafür hast du keine Rechte!");
        insertMessage("no-player", "&cDieser Spieler existiert nicht!");

        insertMessage("setup.menu-main.title", "&b&lSetup");
        insertMessage("setup.menu-main.item-spawnpoints", "&bSpawnpunkte");
        insertMessage("setup.menu-main.item-teams", "&bTeams");
        insertMessage("setup.menu-main.item-settings", "&bEinstellungen");

        insertMessage("setup.menu-location.title", "&b&lLocation Setup");
        insertMessage("setup.menu-location.item-waitingspawn", "&bWartelobby");
        insertMessage("setup.menu-location.item-gamespawn", "&bIngame");
    }

    public void insertMessage(String path, String message) {
        config.set(path, message);
        save();
    }

    public String getMessageAsString(String path) {
        return config.getString(path);
    }

    public static String getMessage(String path) {
        return Starfighter.getInstance().getMessageManager().getMessageAsString(path).replaceAll("&", "§");
    }





}
