package com.pauljoda.thirdpersonelytra.event;


import com.pauljoda.thirdpersonelytra.ThirdPersonElytra;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientRenderEvent {
    public static ClientRenderEvent INSTANCE = new ClientRenderEvent();
    private int lastMode = (Minecraft.getInstance()).gameSettings.thirdPersonView;
    private boolean flyingLastTick = false;

    @SubscribeEvent
    public void clientRender(TickEvent.RenderTickEvent event) {
        if (ThirdPersonElytra.isToggleEnabled && (Minecraft.getInstance()).world != null) {
            ClientPlayerEntity player = (Minecraft.getInstance()).player;
            if (player.isElytraFlying() && !this.flyingLastTick) {
                this.lastMode = (Minecraft.getInstance()).gameSettings.thirdPersonView;
                (Minecraft.getInstance()).gameSettings.thirdPersonView = 1;
            } else if (!player.isElytraFlying() && this.flyingLastTick) {
                (Minecraft.getInstance()).gameSettings.thirdPersonView = this.lastMode;
            }
            this.flyingLastTick = player.isElytraFlying();
        }
    }
}
