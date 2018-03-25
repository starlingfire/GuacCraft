package com.starlingfire.guac.proxy;

import com.starlingfire.guac.module.ModuleLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ModuleLoader.preInit(event);
    }

    public void init(FMLInitializationEvent event) {
        ModuleLoader.init(event);
    }

    public void postInit(FMLPostInitializationEvent event) {
        ModuleLoader.postInit(event);
    }

    public void finalInit(FMLPostInitializationEvent event) {
        ModuleLoader.finalInit(event);
    }

    public void serverStarting(FMLServerStartingEvent event) {
        ModuleLoader.serverStarting(event);
    }

    public void doEmote(String playerName, String emoteName) {
        // proxy override
    }

    public void addResourceOverride(String space, String dir, String file, String ext) {
        // proxy override
    }

}
