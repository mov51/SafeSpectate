package net.mov51.commands;

import net.mov51.helpers.GameModeHelper;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.mov51.helpers.ChatHelper.sendChat;
import static net.mov51.helpers.ChatHelper.sendWarn;
import static net.mov51.helpers.LocationHelper.TeleportPlayer;
import static net.mov51.helpers.LocationHelper.formatCoords;
import static net.mov51.helpers.lpMetaHelper.getLocation;

public class SurvivalOverride implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("SafeSpectate.SpectateOverride")){
                Location l = getLocation(p);
                sendChat("You've overridden your return to "+ formatCoords(l) +".", p);
                GameModeHelper.smartSetGameMode(p, GameMode.SURVIVAL);
            }else{
                sendWarn(p,"noOverride");
            }
        }else{
            sender.sendMessage("You must be a player to use this command!");
        }
        return false;
    }

}
