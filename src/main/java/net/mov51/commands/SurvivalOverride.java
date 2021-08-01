package net.mov51.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.mov51.helpers.GameModeHelper.setSurvivalAndOverride;
import static net.mov51.helpers.PermissionHelper.*;

public class SurvivalOverride implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(isPlayer(sender)){
            if(hasPermMessage(sender,"SafeSpectate.SpectateOverride","noOverride")){
                setSurvivalAndOverride(sender);
            }
        }
        return false;
    }


}
