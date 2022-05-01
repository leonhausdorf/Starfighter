package de.zevyx.starfighter;

import de.zevyx.starfighter.commands.DebugCommand;
import de.zevyx.starfighter.commands.SetupCommand;
import de.zevyx.starfighter.listener.*;
import de.zevyx.starfighter.manager.*;
import de.zevyx.starfighter.utils.GameState;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Starfighter extends JavaPlugin {

    private static Starfighter instance;

    private ConfigManager configManager;
    private FlightManager flightManager;
    private BoostManager boostManager;
    private GameManager gameManager;
    private LocationManager locationManager;
    private InventoryManager inventoryManager;
    private GadgetManager gadgetManager;
    private EconomyManager economyManager;
    private MessageManager messageManager;

    @Override
    public void onEnable() {
        instance = this;

        if(!getDataFolder().exists())
            getDataFolder().mkdirs();

        configManager = new ConfigManager();
        flightManager = new FlightManager();
        boostManager = new BoostManager();
        gameManager = new GameManager();
        locationManager = new LocationManager();
        inventoryManager = new InventoryManager();
        gadgetManager = new GadgetManager();
        economyManager = new EconomyManager();
        messageManager = new MessageManager();

        Bukkit.getPluginManager().registerEvents(new ConnectListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new MovementListener(), this);
        Bukkit.getPluginManager().registerEvents(new EnvironmentListener(), this);

        getCommand("debug").setExecutor(new DebugCommand());
        getCommand("setup").setExecutor(new SetupCommand());

        getEconomyManager().initialize();

        getGameManager().setGameState(GameState.SETUP);
    }


    @Override
    public void onDisable() {


    }

    public static Starfighter getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public FlightManager getFlightManager() {
        return flightManager;
    }

    public BoostManager getBoostManager() {
        return boostManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public GadgetManager getGadgetManager() {
        return gadgetManager;
    }

    public EconomyManager getEconomyManager() {
        return economyManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}
