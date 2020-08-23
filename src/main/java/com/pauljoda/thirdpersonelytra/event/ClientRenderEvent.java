package com.pauljoda.thirdpersonelytra.event;


import com.pauljoda.thirdpersonelytra.ThirdPersonElytra;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.PointOfView;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientRenderEvent {
    public static ClientRenderEvent INSTANCE = new ClientRenderEvent();
    private PointOfView lastMode = (Minecraft.getInstance()).gameSettings.func_243230_g();
    private boolean flyingLastTick = false;

    @SubscribeEvent
    public void clientRender(TickEvent.RenderTickEvent event) {
        if (ThirdPersonElytra.isToggleEnabled && (Minecraft.getInstance()).world != null) {
            ClientPlayerEntity player = (Minecraft.getInstance()).player;
            if (player.isElytraFlying() && !this.flyingLastTick) {
                this.lastMode = (Minecraft.getInstance()).gameSettings.func_243230_g();
                (Minecraft.getInstance()).gameSettings.func_243229_a(PointOfView.THIRD_PERSON_BACK);
            } else if (!player.isElytraFlying() && this.flyingLastTick) {
                (Minecraft.getInstance()).gameSettings.func_243229_a(this.lastMode);
            }
            this.flyingLastTick = player.isElytraFlying();
        }
    }
}
