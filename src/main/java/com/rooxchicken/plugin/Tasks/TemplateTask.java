package com.rooxchicken.plugin.Tasks;

import com.rooxchicken.plugin.Plugin;

public class TemplateTask extends Task
{
    private Plugin plugin;

    public TemplateTask(Plugin _plugin)
    {
        super(_plugin);
        plugin = _plugin;

        tickThreshold = 4;
    }

    @Override
    public void run()
    {
        
    }
}
