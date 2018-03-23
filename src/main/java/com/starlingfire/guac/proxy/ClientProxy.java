package com.starlingfire.guac.proxy;

import com.starlingfire.guac.module.ModuleLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

    /* Not doing whatever this is

        static ResourceProxy resourceProxy;

        static {
            List<IResourcePack> packs = ReflectionHelper.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), LibObfuscation.DEFAULT_RESOURCE_PACKS);
            resourceProxy = new ResourceProxy();
            packs.add(resourceProxy);

            EmoteSystem.addResourcePack(packs);
        }

    */

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        ModuleLoader.preInitClient(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ModuleLoader.initClient(event);

        /*  Not doing capes or custom events (gui stuff)

            MinecraftForge.EVENT_BUS.register(DevCapeHandler.class);
            MinecraftForge.EVENT_BUS.register(ConfigEvents.class);
        */
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ModuleLoader.postInitClient(event);
    }

    /* Not implementing emotes

        @Override
        public void doEmote(String playerName, String emoteName) {
            World world = Minecraft.getMinecraft().world;
            EntityPlayer player = world.getPlayerEntityByName(playerName);
            if(player != null && player instanceof AbstractClientPlayer)
                EmoteHandler.putEmote((AbstractClientPlayer) player, emoteName);
        }
    */

    /* Not doing whatever this is

        @Override
        public void addResourceOverride(String space, String dir, String file, String ext) {
            resourceProxy.addResource(space, dir, file, ext);
        }
    */
}
