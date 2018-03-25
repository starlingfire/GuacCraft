package com.starlingfire.guac;

import com.starlingfire.guac.proxy.CommonProxy;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static net.minecraftforge.fml.common.Mod.*;

@Mod(modid = GuacCraft.MODID, name = GuacCraft.NAME, version = GuacCraft.VERSION, dependencies = "required-after:autoreglib")
public class GuacCraft
{
    public static final String MODID = "guac";
    public static final String NAME = "GuacCraft";
    public static final String VERSION = "0.1.0";
    public static final String PREFIX_MOD = MODID + ":";
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final String CLIENT_PROXY_CLASS = "com.starlingfire.guac.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "com.starlingfire.guac.proxy.CommonProxy";

    //private static Logger logger;
    public static final Logger logger = LogManager.getLogger(MODID);


    @Instance(MODID)
    public static GuacCraft instance;

    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = COMMON_PROXY_CLASS)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
        // logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        System.out.println("AutoRegLib is installed: " + Loader.isModLoaded("autoreglib"));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
        proxy.finalInit(event);

    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event){
        proxy.serverStarting(event);
    }
}
