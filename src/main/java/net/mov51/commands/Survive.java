package net.mov51.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.mov51.helpers.ChatHelper.sendChat;
import static net.mov51.helpers.PermissionHelper.*;
import static net.mov51.helpers.PermissionHelper.getPlayer;
import static net.mov51.helpers.ToggleHelper.GameModeToggle;

public class Survive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(isPlayer(sender)){
            if(hasPerm(sender,"SafeSpectate.Survive")){
                if(!isGameMode(sender,GameMode.SURVIVAL)){
                    GameModeToggle(sender);
                }else{
                    sendChat("You're already in Survival mode!", getPlayer(sender));
                }
            }
        }
        return false;
    }
}
