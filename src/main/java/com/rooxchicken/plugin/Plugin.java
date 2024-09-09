package com.rooxchicken.plugin;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import com.rooxchicken.plugin.Commands.GetItems;
import com.rooxchicken.plugin.Commands.WandCommand;
import com.rooxchicken.plugin.Tasks.ManaTask;
import com.rooxchicken.plugin.Tasks.Task;
import com.rooxchicken.plugin.Tasks.Task_WandOfSparking;

public class Plugin extends JavaPlugin implements Listener
{
    public static NamespacedKey manaKey;

    public static ArrayList<Player> hasMod;
    public static ArrayList<Task> tasks;
    
    public ItemStack wand;

    @Override
    public void onEnable()
    {
        tasks = new ArrayList<Task>();
        tasks.add(new ManaTask(this));

        manaKey = new NamespacedKey(this, "mana");

        getServer().getPluginManager().registerEvents(this, this);

        this.getCommand("getitems").setExecutor(new GetItems(this));
        this.getCommand("wand").setExecutor(new WandCommand(this));

        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
        {
            public void run()
            {
                ArrayList<Task> _tasks = new ArrayList<Task>();
                for(Task t : tasks)
                    _tasks.add(t);
                
                ArrayList<Task> toRemove = new ArrayList<Task>();

                for(Task t : _tasks)
                {
                    t.tick();

                    if(t.cancel)
                        toRemove.add(t);
                }

                for(Task t : toRemove)
                {
                    t.onCancel();
                    tasks.remove(t);
                }
            }
        }, 0, 1);

        wand = new ItemStack(Material.STICK);
        ItemMeta meta = wand.getItemMeta();

        meta.setDisplayName("Wand of Sparking");
        meta.setMaxStackSize(1);

        wand.setItemMeta(meta);

        getLogger().info("Plugin Base (1987) (made by roo)");
    }

    @EventHandler
    private void onJoin(PlayerLoginEvent event)
    {
        //send wake up request
        sendData(event.getPlayer(), "0");
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent event)
    {
        //remove the player from the list
        if(hasMod.contains(event.getPlayer()))
            hasMod.remove(event.getPlayer());
    }

    public int getMana(Player player)
    {
        PersistentDataContainer data = player.getPersistentDataContainer();
        if(!data.has(manaKey))
            data.set(manaKey, PersistentDataType.INTEGER, 400);

        return data.get(manaKey, PersistentDataType.INTEGER);
    }
    
    public void useMana(Player player, int mana)
    {
        PersistentDataContainer data = player.getPersistentDataContainer();
        data.set(manaKey, PersistentDataType.INTEGER, getMana(player) - mana);
    }

    public void sendData(Player player, String data)
    {
        //send our key, then the data
        player.sendMessage("tut012_" + data);
    }
}
