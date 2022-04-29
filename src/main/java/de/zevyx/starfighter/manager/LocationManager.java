package de.zevyx.starfighter.manager;

import de.zevyx.starfighter.Starfighter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationManager {

    private File file;
    private YamlConfiguration config;

    public LocationManager() {
        file = new File(Starfighter.getInstance().getDataFolder(), "locations.yml");
        config = YamlConfiguration.loadConfiguration(file);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLocation(String name, String world, double x, double y, double z, float yaw, float pitch) {
        config.set("locations." + name + ".world", world);
        config.set("locations." + name + ".x", x);
        config.set("locations." + name + ".y", y);
        config.set("locations." + name + ".z", z);
        config.set("locations." + name + ".yaw", yaw);
        config.set("locations." + name + ".pitch", pitch);
    }

    public void setLocation(String name, Location location) {
        config.set("locations." + name + ".world", location.getWorld().getName());
        config.set("locations." + name + ".x", location.getX());
        config.set("locations." + name + ".y", location.getY());
        config.set("locations." + name + ".z", location.getZ());
        config.set("locations." + name + ".yaw", location.getYaw());
        config.set("locations." + name + ".pitch", location.getPitch());
        save();

    }

    public Location getLocation(String name) {
        return new Location(
                Bukkit.getWorld(config.getString("locations." + name + ".world")),
                config.getDouble("locations." + name + ".x"),
                config.getDouble("locations." + name + ".y"),
                config.getDouble("locations." + name + ".z"),
                (float) config.getDouble("locations." + name + ".yaw"),
                (float) config.getDouble("locations." + name + ".pitch")
        );
    }

    public String getWorld(String name) {
        return config.getString("locations." + name + ".world");
    }

    public double getX(String name) {
        return config.getDouble("locations." + name + ".x");
    }

    public double getY(String name) {
        return config.getDouble("locations." + name + ".y");
    }

    public double getZ(String name) {
        return config.getDouble("locations." + name + ".z");
    }

    public float getYaw(String name) {
        return (float) config.getDouble("locations." + name + ".yaw");
    }

    public float getPitch(String name) {
        return (float) config.getDouble("locations." + name + ".pitch");
    }

    public boolean exists(String name) {
        return config.contains("locations." + name);
    }

    public void remove(String name) {
        config.set("locations." + name, null);
    }
}
