package com.acoptic.betterswordfight.network.msg;

import com.acoptic.betterswordfight.data.EntityData;
import com.acoptic.betterswordfight.data.EntityDatabase;
import com.acoptic.betterswordfight.data.SFAction;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CombatActionMsg implements IMessage{
	SFAction action;
	
	public CombatActionMsg(){}
	
	public CombatActionMsg(SFAction action) {
		    this.action = action;
		  }
	
	  @Override public void toBytes(ByteBuf buf) {
		  switch(this.action){
		  	case L_CLICK:
				buf.writeInt(0);	
		  		break;
		  	case R_CLICK:
		  		buf.writeInt(1);
		  		break;
		  	case SCROLL_D:
		  		buf.writeInt(2);
		  		break;
		  	case SCROLL_U:
		  		buf.writeInt(3);
		  		break;
		  }  

		  }

	  @Override public void fromBytes(ByteBuf buf) {
		  switch(buf.readInt()){
		  	case 0:
		  		action = SFAction.L_CLICK;
		  		break;
		  	case 1:
		  		action = SFAction.R_CLICK;
		  		break;
		  	case 2:
		  		action = SFAction.SCROLL_D;
		  		break;
		  	case 3:
		  		action = SFAction.SCROLL_U;
		  		break;
		  }  
	  }
	  
  public static class CombatActionMsgHandler implements IMessageHandler<CombatActionMsg, IMessage> {
	// Do note that the default constructor is required, but implicitly defined in this case

		@Override public IMessage onMessage(CombatActionMsg message, MessageContext ctx) {
			 
			 EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
			 EntityData<?> data = EntityDatabase.instance.get(serverPlayer);
			 data.processAction(message.action);
			 return null;
		}
	}
}
