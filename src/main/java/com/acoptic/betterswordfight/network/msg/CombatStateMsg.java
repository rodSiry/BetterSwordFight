package com.acoptic.betterswordfight.network.msg;

import com.acoptic.betterswordfight.client.event.KeyboardHandler;
import com.acoptic.betterswordfight.data.SFState;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class CombatStateMsg implements IMessage{
	SFState state;
	int count;
	
	public CombatStateMsg(){}
	
	public CombatStateMsg(SFState state, int count) {
		    this.state = state;
		    this.count = count;
		  }
	
	  @Override public void toBytes(ByteBuf buf) {
		  switch(this.state){
		  	case UNEQUIPPED:
				buf.writeInt(0);	
		  		break;
		  	case SHEATED:
		  		buf.writeInt(1);
		  		break;
		  	case STANDBY:
		  		buf.writeInt(2);
		  		break;
		  	case OVERHEAD:
		  		buf.writeInt(3);
		  		break;
		  	case ESTOC:
		  		buf.writeInt(4);
		  		break;
		  	case SLASH:
		  		buf.writeInt(5);
		  		break;
		  	case RSLASH:
		  		buf.writeInt(6);
		  		break;
		  	case PARRY:
		  		buf.writeInt(7);
		  		break;
		  }  
		  buf.writeInt(count);

	}

	  @Override public void fromBytes(ByteBuf buf) {
		  switch(buf.readInt()){
		  	case 0:
		  		state = SFState.UNEQUIPPED;
		  		break;
		  	case 1:
		  		state = SFState.SHEATED;
		  		break;
		  	case 2:
		  		state = SFState.STANDBY;
		  		break;
		  	case 3:
		  		state = SFState.OVERHEAD;
		  		break;
		  	case 4:
		  		state = SFState.ESTOC;
		  		break;
		  	case 5:
		  		state = SFState.SLASH;
		  		break;
		  	case 6:
		  		state = SFState.RSLASH;
		  		break;
		  	case 7:
		  		state = SFState.PARRY;
		  		break;
		  }  
		  count = buf.readInt();
	  }
	  
  public static class CombatStateMsgHandler implements IMessageHandler<CombatStateMsg, IMessage> {
	// Do note that the default constructor is required, but implicitly defined in this case

		@Override public IMessage onMessage(CombatStateMsg message, MessageContext ctx) {
			 KeyboardHandler.playerState = message.state;
			 KeyboardHandler.count = message.count;
			 return null;
		}
	}
}
