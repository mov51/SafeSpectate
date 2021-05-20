package net.mov51.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.mov51.helpers.ChatHelper.sendChat;
import static net.mov51.helpers.LocationHelper.formatCoords;
import static net.mov51.helpers.lpMetaHelper.getLocation;

public class gmReturn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = ((Player) sender).getPlayer();
            assert p != null;
            if(p.getGameMode() == GameMode.SPECTATOR){
                String l = formatCoords(getLocation(p));
                sendChat("You will be teleported to " + l + " when you return to survival mode.",p);
            }
        }

        return false;
    }
}
