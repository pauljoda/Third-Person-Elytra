package com.pauljoda.thirdpersonelytra;

import com.pauljoda.thirdpersonelytra.event.ClientRenderEvent;
import com.pauljoda.thirdpersonelytra.lib.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ThirdPersonElytra {

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(ClientRenderEvent.INSTANCE);
    }
}
