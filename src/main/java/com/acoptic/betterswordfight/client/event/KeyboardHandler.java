package com.acoptic.betterswordfight.client.event;

import com.acoptic.betterswordfight.Main;
import com.acoptic.betterswordfight.data.SFAction;
import com.acoptic.betterswordfight.data.SFState;
import com.acoptic.betterswordfight.network.BSFPacketHandler;
import com.acoptic.betterswordfight.network.msg.CombatActionMsg;

import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class KeyboardHandler {
	
	public static SFState playerState = SFState.UNEQUIPPED;
	public static int count = 0;
	
	@SubscribeEvent
	public void onMouseEvent(MouseEvent event) {
		int wheel = event.getDwheel();
		int click = event.getButton();
		SFAction action = null;
		switch(click) {
			case 0:
				action = SFAction.L_CLICK;
				break;
			case 1:
				action = SFAction.R_CLICK;
				break;
		}
		if(wheel > 0) {
			action = SFAction.SCROLL_U;
		}
		if(wheel < 0) {
			action = SFAction.SCROLL_D;
		}
		if (action != null && (playerState != SFState.UNEQUIPPED)) {
			if(playerState == SFState.SHEATED && count == 0) {
				if(action == SFAction.L_CLICK) {
					BSFPacketHandler.INSTANCE.sendToServer(new CombatActionMsg(action));
					event.setCanceled(true);
				}
				if(action == SFAction.R_CLICK) {
					BSFPacketHandler.INSTANCE.sendToServer(new CombatActionMsg(action));
					event.setCanceled(true);
				}
			}
			else {
				BSFPacketHandler.INSTANCE.sendToServer(new CombatActionMsg(action));
				event.setCanceled(true);
			}
		}
	}
}

