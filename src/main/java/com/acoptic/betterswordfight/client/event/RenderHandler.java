package com.acoptic.betterswordfight.client.event;

import java.util.List;

import com.acoptic.betterswordfight.client.renderer.RenderPlayerCustom;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHandler {
	protected List<LayerRenderer<EntityLivingBase>> layerRenderers;
	@SubscribeEvent
	public void onPlayerRender(RenderPlayerEvent.Pre event) {
		RenderPlayerCustom renderer = new RenderPlayerCustom(event.getRenderer().getRenderManager());
		event.setCanceled(true);
		renderer.doRender((AbstractClientPlayer)event.getEntity(), event.getX(), event.getY(), event.getZ(), event.getEntity().rotationYaw, event.getPartialRenderTick());

	}

}
