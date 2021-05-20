package net.mov51;

import net.luckperms.api.LuckPerms;
import net.mov51.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

import static net.mov51.helpers.ChatHelper.sendLog;

public final class SafeSpectate extends JavaPlugin {

    public static String logPrefix = "[SafeSpectate] - ";
    //todo add a config for ChatPrefix value
    public static String chatPrefix = "§6§l[§2Naspen§6§l]§r";

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(getCommand("gmsp")).setExecutor(new Spectate());
        Objects.requireNonNull(getCommand("gms")).setExecutor(new Survive());
        Objects.requireNonNull(getCommand("gmso")).setExecutor(new SurvivalOverride());
        Objects.requireNonNull(getCommand("gmtoggle")).setExecutor(new gmToggle());
        Objects.requireNonNull(getCommand("gmreturn")).setExecutor(new gmReturn());

        RegisteredServiceProvider<LuckPerms> LPprovider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if(LPprovider != null){
            sendLog("LuckPerms loaded!");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
