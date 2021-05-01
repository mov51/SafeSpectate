package net.mov51.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.mov51.helpers.PermissionHelper.hasPerm;
import static net.mov51.helpers.ToggleHelper.GameModeToggle;

public class gmtoggle implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(hasPerm(sender,"SafeSpectate.Toggle")){
            GameModeToggle(sender);
        }
        return false;
    }
}
