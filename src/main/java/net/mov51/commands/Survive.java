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
import static net.mov51.helpers.lpMetaHelper.*;

public class Survive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("SafeSpectate.Survive")){
                if(isLocation(p)){
                    //Get location from LuckPerms
                    Location l = getLocation(p);

                    //Teleports player to the acquired location
                    TeleportPlayer(p,l);
                    sendChat("You've been returned to " + formatCoords(l) + "!", p);
                    GameModeHelper.smartSetGameMode(p, GameMode.SURVIVAL);
                    clearLocation(p);
                }else{
                    sendChat("There isn't a location to return you to! Did you set yourself to spectator yet?", p);
                }
            }else{
                //warns player that they don't have the required permission
                sendWarn(p,"noPerm");
            }
        }else{
            sender.sendMessage("You must be a player to use this command!");
        }
        return false;
    }
}
