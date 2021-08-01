package net.mov51.helpers;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.mov51.helpers.ChatHelper.sendWarn;

public class PermissionHelper {

    public static boolean hasPerm(Player p,String Permission){
        if(p.hasPermission(Permission))
            return true;
        else{
            sendWarn(p,"noPerm");
            return false;
        }
    }

    public static boolean hasPerm(CommandSender sender,String Permission){
        if(sender instanceof Player){
            return hasPerm((Player) sender, Permission);
        }
        return false;
    }

    public static boolean hasPermMessage(Player p,String Permission,String message) {
        if (p.hasPermission(Permission)){
            return true;
        }
        else {
            sendWarn(p, message);
            return false;
        }
    }
    public static boolean hasPermMessage(CommandSender sender,String Permission,String message){
        if(sender instanceof Player){
            return hasPermMessage((Player) sender, Permission,message);
        }
        return false;
    }

    public static boolean isPlayer(CommandSender sender){
        if(sender instanceof Player)
            return true;
        else{
            sender.sendMessage("You must be a player to use this command!");
            return false;
        }
    }

    public static boolean isGameMode(Player p, GameMode G){
        return p.getGameMode() == G;
    }

    public static boolean isGameMode(CommandSender s, GameMode G){
        return ((Player)s).getGameMode() == G;
    }
}
