package net.mov51.commands;

import net.mov51.helpers.ChatHelper;
import net.mov51.helpers.GameModeHelper;
import net.mov51.helpers.lpMetaHelper;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.mov51.helpers.ChatHelper.sendChat;
import static net.mov51.helpers.lpMetaHelper.saveLocation;
import static net.mov51.helpers.lpMetaHelper.getLocation;

public class Spectate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("SafeSpectate.Spectate")){
                //Save location to LuckPerms
                Location l = p.getLocation();
                saveLocation(p, l);
                //output location
//                ChatHelper.sendChat("you are at" + getLocation(p),p);
                //Set game mode to spectator
                GameModeHelper.smartSetGameMode(p,GameMode.SPECTATOR);
                sendChat("You will be returned to " + Math.round(l.getX()) + "," + Math.round(l.getX()) + "," + Math.round(l.getZ()) + " when you exit spectator mode!", p);
            }
        }else{
            sender.sendMessage("You must be a player to use this command!");
        }
        return false;
    }
}
