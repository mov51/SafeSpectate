package net.mov51.helpers;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.mov51.SafeSpectate.*;

public class ChatHelper {
    public static void sendChat(Player player, String message){
        player.sendMessage(chatPrefix + message);
    }

    public static void sendColoredChat(Player player, String message, String color){
        player.sendMessage(chatPrefix + ChatColor.of(color)+message);
    }

    public static void sendChat(CommandSender sender, String message){
        if(sender instanceof Player){
            sendChat((Player) sender, message);
        }else{
            sender.sendMessage(chatPrefix + message);
        }
    }

    public static void sendLog(String message){
        System.out.println(logPrefix + message);
    }

    public static void sendWarn(Player p, String message){
        switch (message){
            case "noPerm":
                sendColoredChat(p, "Sorry! You don't have permission to do that! If you need help please contact staff!",chatWarnColor);
                break;
            case "noOverride":
                sendColoredChat(p, "Sorry! Only staff members can avoid the return teleport!",chatWarnColor);
                break;
            default:
                sendColoredChat(p, message,chatWarnColor);
                break;
        }
    }
}
