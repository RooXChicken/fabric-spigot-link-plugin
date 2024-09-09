package com.rooxchicken.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rooxchicken.plugin.Plugin;

public class VerifyMod implements CommandExecutor
{
    private Plugin plugin;

    public VerifyMod(Plugin _plugin)
    {
        plugin = _plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        //this command tells the server that the client has the mod, and it is active
        Player player = Bukkit.getPlayer(sender.getName());
        if(!Plugin.hasMod.contains(player))
            Plugin.hasMod.add(player);

        return true;
    }

}
