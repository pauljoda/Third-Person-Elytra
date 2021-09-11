package com.pauljoda.thirdpersonelytra.event;


import com.pauljoda.thirdpersonelytra.ThirdPersonElytra;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientRenderEvent {
    public static ClientRenderEvent INSTANCE = new ClientRenderEvent();
    private CameraType lastMode = (Minecraft.getInstance()).options.getCameraType();
    private boolean flyingLastTick = false;

    @SubscribeEvent
    public void clientRender(TickEvent.RenderTickEvent event) {
        if (ThirdPersonElytra.isToggleEnabled && (Minecraft.getInstance()).level != null) {
            LocalPlayer player = (Minecraft.getInstance()).player;
            if (player.isFallFlying() && !this.flyingLastTick) {
                this.lastMode = (Minecraft.getInstance()).options.getCameraType();
                (Minecraft.getInstance()).options.setCameraType(CameraType.THIRD_PERSON_BACK);
            } else if (!player.isFallFlying() && this.flyingLastTick) {
                (Minecraft.getInstance()).options.setCameraType(this.lastMode);
            }
            this.flyingLastTick = player.isFallFlying();
        }
    }
}
