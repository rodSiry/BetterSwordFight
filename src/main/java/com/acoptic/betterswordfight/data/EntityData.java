package com.acoptic.betterswordfight.data;

import com.acoptic.betterswordfight.Main;
import com.acoptic.betterswordfight.network.BSFPacketHandler;
import com.acoptic.betterswordfight.network.msg.CombatStateMsg;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class EntityData<E extends EntityLivingBase> {
	
	protected int entityID;
	protected final E entity;
	private final int attackDelay = 20;
	private final int parryDelay  = 20;
	private final int sheathDelay = 20;
	private int counter = 0;
	private boolean hasStarted = false;
	
	private SFState state = SFState.UNEQUIPPED;
	
	public EntityData(E entity) {
		this.entity = entity;
		if (this.entity != null) {
			this.entityID = entity.getEntityId();
		}
	}
	
	public E getEntity() {
		return this.entity;
	}
	
	public void update() {
		if (entity instanceof EntityPlayerMP){	

			if(hasStarted) {
				counter--;
				if (counter==0) {
					switch(state) {
						case STANDBY:
							break;
						case UNEQUIPPED:
							break;
						case SHEATED:
							state = SFState.STANDBY;
							break;
						case OVERHEAD:
							state = SFState.STANDBY;
							break;
						case ESTOC:
							state = SFState.STANDBY;
							break;
						case SLASH:
							state = SFState.STANDBY;
							break;
						case RSLASH:
							state = SFState.STANDBY;
							break;
						case PARRY:
							state = SFState.STANDBY;
							break;
					}
					hasStarted = false;
				}
				else {
					BSFPacketHandler.INSTANCE.sendTo(new CombatStateMsg(state, counter), (EntityPlayerMP)entity);
					Main.logger.info("Now in " + state + " mode, with count " + counter);
				}
			}
			ItemStack item = entity.getHeldItemMainhand();	
			if(state==SFState.UNEQUIPPED) {
				if(item.getItem() instanceof ItemSword) {
					state = SFState.SHEATED;
					BSFPacketHandler.INSTANCE.sendTo(new CombatStateMsg(state, counter), (EntityPlayerMP)entity);
					Main.logger.info("Now in " + state + " mode");
				}
			}
			if(!(item.getItem() instanceof ItemSword) && state != SFState.UNEQUIPPED) {
				state = SFState.UNEQUIPPED;
				BSFPacketHandler.INSTANCE.sendTo(new CombatStateMsg(state, counter), (EntityPlayerMP)entity);
				Main.logger.info("Now in " + state + " mode");
			}
			
		}
			
	}
	
	public void processAction(SFAction action) {
		if(state==SFState.SHEATED && action==SFAction.L_CLICK && !hasStarted){
			hasStarted = true;
			counter = sheathDelay;
		}
		if(state==SFState.STANDBY && action==SFAction.R_CLICK && !hasStarted){
			hasStarted = true;
			counter = parryDelay;
			state = SFState.PARRY;
		}
		if(state==SFState.STANDBY && action==SFAction.L_CLICK && !hasStarted){
			hasStarted = true;
			counter = attackDelay;
			state = SFState.SLASH;
		}
		if(state==SFState.STANDBY && action==SFAction.SCROLL_D && !hasStarted){
			hasStarted = true;
			counter = attackDelay;
			state = SFState.OVERHEAD;
		}
		if(state==SFState.STANDBY && action==SFAction.SCROLL_U && !hasStarted){
			hasStarted = true;
			counter = attackDelay;
			state = SFState.ESTOC;
		}
	}

}
