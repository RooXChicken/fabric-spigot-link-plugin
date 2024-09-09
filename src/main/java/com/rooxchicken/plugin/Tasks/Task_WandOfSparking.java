package com.rooxchicken.plugin.Tasks;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import com.rooxchicken.plugin.Plugin;

public class Task_WandOfSparking extends Task
{
    private Plugin plugin;
    private Player player;

    private Location sparkPos;
    
    private int t = 0;

    public Task_WandOfSparking(Plugin _plugin, Player _player)
    {
        super(_plugin);
        plugin = _plugin;
        player = _player;

        sparkPos = player.getEyeLocation().clone();

        tickThreshold = 1;
    }

    @Override
    public void run()
    {
        sparkPos.add(sparkPos.getDirection());
        sparkPos.getWorld().spawnParticle(Particle.FLAME, sparkPos, 1, 0, 0, 0, 0.1);

        if(++t > 100)
            cancel = true;
    }
}
