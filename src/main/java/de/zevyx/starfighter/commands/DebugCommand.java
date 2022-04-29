package de.zevyx.starfighter.commands;

import de.zevyx.starfighter.Starfighter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("debug")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;
                if(player.hasPermission("starfighter.debug")) {
                    if(args[0].equalsIgnoreCase("start")) {
                        player.sendMessage("[DEBUG] Set Game as started");
                        Starfighter.getInstance().getGameManager().startGame();
                    }
                }
            }
        }

        return false;
    }
}
