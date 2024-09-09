package com.rooxchicken.plugin.Tasks;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.rooxchicken.plugin.Plugin;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ManaTask extends Task
{
    //FOR DEVELOPERS
    //this is just a task system i made. think of this as a scheduled task in spigot

    private Plugin plugin;

    public ManaTask(Plugin _plugin)
    {
        super(_plugin);
        plugin = _plugin;

        tickThreshold = 1;
    }

    @Override
    public void run()
    {
        for(Player player : Plugin.hasMod)
        {
            PersistentDataContainer data = player.getPersistentDataContainer();
            if(!data.has(Plugin.manaKey))
                data.set(Plugin.manaKey, PersistentDataType.INTEGER, 400);

            int mana = data.get(Plugin.manaKey, PersistentDataType.INTEGER);

            if(++mana > 400)
                mana = 400;

            data.set(Plugin.manaKey, PersistentDataType.INTEGER, mana);
            
            //1 marks the type of data (mana information)
            //400 is the max mana (can be a variable)
            plugin.sendData(player, "1_400_" + mana);
            
            //player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(ChatColor.BLUE + "Mana: " + (mana/20) + "/20"));
        }
    }
}