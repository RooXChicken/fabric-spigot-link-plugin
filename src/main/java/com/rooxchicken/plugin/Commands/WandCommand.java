package com.rooxchicken.plugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import com.rooxchicken.plugin.Plugin;
import com.rooxchicken.plugin.Tasks.Task_WandOfSparking;

public class WandCommand implements CommandExecutor
{
    private Plugin plugin;

    public WandCommand(Plugin _plugin)
    {
        plugin = _plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = Bukkit.getPlayer(sender.getName());
        //int ability = Integer.parseInt(args[0]);

        return true;
    }

}
