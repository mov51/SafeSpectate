package net.mov51.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.mov51.helpers.GameModeHelper.*;
import static net.mov51.helpers.PermissionHelper.*;

public class Spectate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (isPlayer(sender)) {
            //If any args are present, assume that they are trying to change other players.
            if (args.length == 0) {
                if (hasPerm(sender, "SafeSpectate.Spectate")) {
                    setSpectatorAndSave(sender);
                }
            } else if (args.length == 1) {
                if (hasPerm(sender, "SafeSpectate.Spectate.Others")) {
                    setOtherSpectatorAndSave(sender, args[0]);
                }
            }

        }
        return false;
    }
}