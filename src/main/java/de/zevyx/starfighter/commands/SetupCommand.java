package de.zevyx.starfighter.commands;

import de.zevyx.starfighter.Starfighter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("setup")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission("starfighter.setup")) {
                    Starfighter.getInstance().getInventoryManager().openSetupInventory(player);
                }
            }
        }

        return false;
    }
}
