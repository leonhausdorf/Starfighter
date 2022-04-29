package de.zevyx.starfighter.manager;

import de.zevyx.starfighter.Starfighter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class EconomyManager {

    private File file;
    private YamlConfiguration config;

    public EconomyManager() {
        this.file = new File(Starfighter.getInstance().getDataFolder(), "economy.yml");
    }

    public void initialize() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            file.delete();
            initialize();
        }
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializePlayer(Player player) {
        config.set(player.getName() + ".balance", 0);
        save();
    }

}
