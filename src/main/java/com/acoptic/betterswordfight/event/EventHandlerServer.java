package com.acoptic.betterswordfight.event;


import com.acoptic.betterswordfight.data.EntityData;
import com.acoptic.betterswordfight.data.EntityDatabase;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;

public class EventHandlerServer {
	
	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {
		if(event.getEntity() instanceof EntityLivingBase && !event.getEntity().world.isRemote) {
			EntityLivingBase entity = (EntityLivingBase)event.getEntity();
			ItemStack item = entity.getHeldItemMainhand();
			if(item.getItem() instanceof ItemSword || entity instanceof EntityPlayerMP)
				EntityDatabase.instance.add(entity, new EntityData<EntityLivingBase>(entity));
		}

	}
	
	@SubscribeEvent
	public void onEntityPickupEvent(EntityItemPickupEvent event) {
	}
	
	@SubscribeEvent
	public void onServerTicks(ServerTickEvent event) {
		EntityDatabase.instance.update();
	}
}
