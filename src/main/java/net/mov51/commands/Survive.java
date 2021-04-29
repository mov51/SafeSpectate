package net.mov51.commands;

import net.mov51.helpers.GameModeHelper;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static net.mov51.helpers.ChatHelper.sendChat;
import static net.mov51.helpers.ChatHelper.sendWarn;
import static net.mov51.helpers.LocationHelper.TeleportPlayer;
import static net.mov51.helpers.LocationHelper.formatCoords;
import static net.mov51.helpers.lpMetaHelper.*;
import static org.bukkit.Bukkit.getServer;

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
                    Location l = p.getBedSpawnLocation();
                    if(l != null){
                        TeleportPlayer(p,l);
                    }else{
                        Location l2 = getServer().getWorlds().get(0).getSpawnLocation();
                        TeleportPlayer(p,l2);
                    }
                    GameModeHelper.smartSetGameMode(p, GameMode.SURVIVAL);
                    sendChat("No Location to return to. You've been sent to your spawn instead <3", p);
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
