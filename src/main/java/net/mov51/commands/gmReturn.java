package net.mov51.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.mov51.helpers.ChatHelper.sendChat;
import static net.mov51.helpers.GameModeHelper.isGameMode;
import static net.mov51.helpers.LocationHelper.formatCords;
import static net.mov51.helpers.PermissionHelper.isPlayer;
import static net.mov51.helpers.lpMetaHelper.getLocation;

public class gmReturn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(isPlayer(sender)){
            Player p = ((Player) sender).getPlayer();
            assert p != null;
            if(isGameMode(p,GameMode.SPECTATOR)){
                String l = formatCords(getLocation(p));
                sendChat(p, "You will be teleported to " + l + " when you return to survival mode.");
            }else{
                sendChat(p, "You aren't in spectator mode!");
            }
        }

        return false;
    }
}
