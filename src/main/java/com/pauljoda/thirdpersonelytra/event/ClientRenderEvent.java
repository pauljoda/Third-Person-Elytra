package com.pauljoda.thirdpersonelytra.event;


import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ClientRenderEvent {
    public static ClientRenderEvent INSTANCE = new ClientRenderEvent();
    private int lastMode = (Minecraft.getMinecraft()).gameSettings.thirdPersonView;
    private boolean flyingLastTick = false;

    @SubscribeEvent
    public void clientRender(TickEvent.RenderTickEvent event) {
        if ((Minecraft.getMinecraft()).world != null) {
            EntityPlayerSP player = (Minecraft.getMinecraft()).player;
            if (player.isElytraFlying() && !this.flyingLastTick) {
                this.lastMode = (Minecraft.getMinecraft()).gameSettings.thirdPersonView;
                (Minecraft.getMinecraft()).gameSettings.thirdPersonView = 1;
            } else if (!player.isElytraFlying() && this.flyingLastTick) {
                (Minecraft.getMinecraft()).gameSettings.thirdPersonView = this.lastMode;
            }
            this.flyingLastTick = player.isElytraFlying();
        }
    }
}
