package net.mov51.commands;

import net.mov51.helpers.GameModeHelper;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.mov51.helpers.ChatHelper.sendChat;
import static net.mov51.helpers.GameModeHelper.setOtherSurvivalAndReturn;
import static net.mov51.helpers.GameModeHelper.setSurvivalAndReturn;
import static net.mov51.helpers.PermissionHelper.*;

public class Survive implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        GameMode G = GameMode.SURVIVAL;
        if (isPlayer(sender)) {
            //If any args are present, assume that they are trying to change other players.
            if (args.length == 0) {
                if (hasPerm(sender, "SafeSpectate.Survive")) {
                    if (!GameModeHelper.isGameMode(sender, G)) {
                        setSurvivalAndReturn(sender);
                    } else {
                        sendChat(sender, "You're already in Survival mode!");
                    }
                }
            } else if (args.length == 1) {
                if (hasPerm(sender, "SafeSpectate.Survive.Others")) {
                    setOtherSurvivalAndReturn(sender, args[0]);
                }
            }
        }
        return false;
    }
}
