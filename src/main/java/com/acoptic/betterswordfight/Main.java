package com.acoptic.betterswordfight;


import org.apache.logging.log4j.Logger;

import com.acoptic.betterswordfight.client.event.KeyboardHandler;
import com.acoptic.betterswordfight.client.event.RenderHandler;
import com.acoptic.betterswordfight.event.EventHandlerServer;
import com.acoptic.betterswordfight.network.BSFPacketHandler;
import com.acoptic.betterswordfight.network.msg.CombatActionMsg;
import com.acoptic.betterswordfight.network.msg.CombatActionMsg.CombatActionMsgHandler;
import com.acoptic.betterswordfight.network.msg.CombatStateMsg;
import com.acoptic.betterswordfight.network.msg.CombatStateMsg.CombatStateMsgHandler;
import com.acoptic.betterswordfight.proxy.CommonProxy;
import com.acoptic.betterswordfight.util.Reference;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid=Reference.MOD_ID, name=Reference.NAME, version=Reference.VERSION)
public class Main {
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide=Reference.CLIENT_PROXY_CLASS, serverSide=Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static Logger logger;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		MinecraftForge.EVENT_BUS.register(new EventHandlerServer());
		MinecraftForge.EVENT_BUS.register(new KeyboardHandler());
		MinecraftForge.EVENT_BUS.register(new RenderHandler());
		BSFPacketHandler.INSTANCE.registerMessage(CombatActionMsgHandler.class, CombatActionMsg.class, 0, Side.SERVER);
		BSFPacketHandler.INSTANCE.registerMessage(CombatStateMsgHandler.class, CombatStateMsg.class,   1, Side.CLIENT);
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event) {
		
	}
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {

	}
}
