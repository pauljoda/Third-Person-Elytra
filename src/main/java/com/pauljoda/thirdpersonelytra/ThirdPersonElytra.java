package com.pauljoda.thirdpersonelytra;

import com.pauljoda.thirdpersonelytra.event.ClientRenderEvent;
import com.pauljoda.thirdpersonelytra.event.KeyPressedEvent;
import com.pauljoda.thirdpersonelytra.lib.Reference;
import com.pauljoda.thirdpersonelytra.manager.ConfigManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;

@Mod(value = Reference.MOD_ID)
public class ThirdPersonElytra {
    // Variable for is enabled
    public static boolean isToggleEnabled;

    public ThirdPersonElytra() {
        // Only need to register on client, server should never have installed but just to be safe
        if(FMLEnvironment.dist == Dist.CLIENT) {
            // Registers the event to swap camera
            MinecraftForge.EVENT_BUS.register(ClientRenderEvent.INSTANCE);

            // Registers the toggle keys
            MinecraftForge.EVENT_BUS.register(KeyPressedEvent.INSTANCE);
            KeyPressedEvent.INSTANCE.registerBindings();

            // Inline method, just want to update local values on changes
            FMLJavaModLoadingContext.get().getModEventBus().addListener((ModConfig.ModConfigEvent event) -> {
                final ModConfig config = event.getConfig();
                // Rebake the configs when they change
                if (config.getSpec() == ConfigManager.CLIENT_SPEC) {
                    ConfigManager.updateValues(config);
                }
            });
            ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigManager.CLIENT_SPEC);
        } else { // Warn servers about running client side mod
            LogManager.getLogger(Reference.MOD_ID).error("Warning: You are running Third Person Elytra on a server." +
                    " This should be an issue, but is a client side only mod");
        }
    }
}
