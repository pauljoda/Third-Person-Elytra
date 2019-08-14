package com.pauljoda.thirdpersonelytra.manager;

import com.pauljoda.thirdpersonelytra.ThirdPersonElytra;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigManager {

    /*******************************************************************************************************************
     * Variables                                                                                                       *
     *******************************************************************************************************************/

    // Value for path to variable in config
    public static final String ELYTRA_ENABLED_PATH = "elytraenabled";

    // Forge config
    public static final Client CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;
    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    // Public access to config
    public static ModConfig configInstance;

    /**
     * Class to define values
     */
    public static class Client {
        public static ForgeConfigSpec.BooleanValue isToggleEnabled;

        public Client(ForgeConfigSpec.Builder builder) {
            isToggleEnabled = builder
                    .comment("Enable toggle by default?")
                    .define(ELYTRA_ENABLED_PATH, true);
        }
    }

    /*******************************************************************************************************************
     * Methods                                                                                                         *
     *******************************************************************************************************************/

    /**
     * Set a value to the config and write changes
     * @param path Path to value
     * @param newValue New value
     */

    public static void setValueAndSave(String path, Object newValue) {
        configInstance.getConfigData().set(path, newValue);
        configInstance.save();
    }

    /**
     * Update local values from changed config
     * @param config Updated config
     */
    public static void updateValues(final ModConfig config) {
        configInstance = config;
        ThirdPersonElytra.isToggleEnabled = Client.isToggleEnabled.get();
    }
}
