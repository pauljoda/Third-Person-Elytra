package com.pauljoda.thirdpersonelytra.event;

import com.pauljoda.thirdpersonelytra.ThirdPersonElytra;
import com.pauljoda.thirdpersonelytra.lib.Reference;
import com.pauljoda.thirdpersonelytra.manager.ConfigManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.KeybindComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fmlclient.registry.ClientRegistry;


public class KeyPressedEvent {
    public static final KeyPressedEvent INSTANCE = new KeyPressedEvent();

    /**
     * The binding for the toggle for flight
     */
    private final KeyMapping toggleThirdPersonElytra =
            new KeyMapping("text.keybindtext", 80, Reference.MOD_NAME);

    /**
     * Registers the bindings for this mod
     */
    public void registerBindings() {
        ClientRegistry.registerKeyBinding(toggleThirdPersonElytra);
    }

    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event) {
        if(toggleThirdPersonElytra.isDown()
                && event.getAction() == 1) {
                ThirdPersonElytra.isToggleEnabled = !ThirdPersonElytra.isToggleEnabled;
                ConfigManager.setValueAndSave(ConfigManager.ELYTRA_ENABLED_PATH, ThirdPersonElytra.isToggleEnabled);
                Minecraft.getInstance().player.displayClientMessage((new TextComponent(I18n.get(
                        ThirdPersonElytra.isToggleEnabled ? "text.toggle.enabled" : "text.toggle.disabled"))), true);
        }
    }
}
