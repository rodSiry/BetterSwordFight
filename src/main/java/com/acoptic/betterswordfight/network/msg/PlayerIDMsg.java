package com.acoptic.betterswordfight.network.msg;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PlayerIDMsg implements IMessage{
	private Integer entityID;
	
	public PlayerIDMsg(Integer entityID) {
		    this.entityID = entityID;
		  }
	
	  @Override public void toBytes(ByteBuf buf) {
		    buf.writeInt(entityID);
		  }

	  @Override public void fromBytes(ByteBuf buf) {
	    entityID = buf.readInt();
	  }
}
