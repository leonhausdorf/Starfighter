package de.zevyx.starfighter.manager;

import de.zevyx.starfighter.Starfighter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private File file;
    private YamlConfiguration config;

    public ConfigManager() {
        this.file = new File(Starfighter.getInstance().getDataFolder(), "config.yml");
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

    public void insertValue(String path, Object value) {
        config.set(path, value);
        save();
    }

    public Object getValue(String path) {
        return config.get(path);
    }




}
