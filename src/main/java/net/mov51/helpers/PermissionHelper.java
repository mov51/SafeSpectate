package net.mov51.helpers;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.mov51.helpers.ChatHelper.sendWarn;

public class PermissionHelper {
    public static boolean hasPerm(Player p,String Perm){
        if(p.hasPermission(Perm))
            return true;
        else{
            sendWarn(p,"noPerm");
            return false;
        }
    }

    public static boolean hasPerm(CommandSender s,String Perm) {
        if (isPlayer(s)) {
            Player p = (Player) s;
            if (p.hasPermission(Perm))
                return true;
            else {
                sendWarn(p, "noPerm");
                return false;
            }
        }else{
            return false;
        }
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
        return getPlayer(s).getGameMode() == G;
    }

    public static Player getPlayer(CommandSender s){
        if (s instanceof Player)
            return (Player) s;
        else
            return null;

    }
}
