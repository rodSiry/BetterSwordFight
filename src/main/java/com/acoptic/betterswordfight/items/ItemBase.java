package com.acoptic.betterswordfight.items;

import com.acoptic.betterswordfight.Main;
import com.acoptic.betterswordfight.init.ModItems;
import com.acoptic.betterswordfight.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel{
	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		
		ModItems.ITEMS.add(this);
	}
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
