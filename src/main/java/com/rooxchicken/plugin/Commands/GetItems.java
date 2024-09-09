package com.rooxchicken.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.rooxchicken.plugin.Plugin;

public class GetItems implements CommandExecutor
{
    private Plugin plugin;

    public GetItems(Plugin _plugin)
    {
        plugin = _plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender.isOp())
        {
            Player player = Bukkit.getPlayer(sender.getName());
            player.getInventory().addItem(plugin.wand);
        }

        return true;
    }

}
