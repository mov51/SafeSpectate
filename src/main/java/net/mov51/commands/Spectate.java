package net.mov51.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.mov51.helpers.ChatHelper.sendChat;
import static net.mov51.helpers.PermissionHelper.*;
import static net.mov51.helpers.ToggleHelper.GameModeToggle;
import static net.mov51.helpers.ToggleHelper.toggleOtherGameMode;

public class Spectate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        GameMode G = GameMode.SPECTATOR;
        if (isPlayer(sender)) {
            //If any args are present, assume that they are trying to change other players.
            if (args.length == 0) {
                if (hasPerm(sender, "SafeSpectate.Spectate")) {
                    if (!isGameMode(sender, G)) {
                        GameModeToggle(sender);
                    } else {
                        sendChat("You're already in Spectator mode!", sender);
                    }
                }
            } else if (args.length == 1) {
                if (hasPerm(sender, "SafeSpectate.Spectate.Others")) {
                    toggleOtherGameMode(sender, args[0], G);
                }
            }

        }
        return false;
    }
}