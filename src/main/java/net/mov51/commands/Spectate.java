package net.mov51.commands;

import net.mov51.helpers.ChatHelper;
import net.mov51.helpers.GameModeHelper;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spectate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("SafeSpectate.Spectate")){
                //output location
                //todo store location
                // - Currently just storing it
                ChatHelper.sendChat("you are at" + p.getLocation(),p);
                //Set game mode to spectator
                GameModeHelper.smartSetGameMode(p,GameMode.SPECTATOR);
                //todo Warn user that they will return to current location
            }
        }else{
            sender.sendMessage("You must be a player to use this command!");
        }
        return false;
    }
}
