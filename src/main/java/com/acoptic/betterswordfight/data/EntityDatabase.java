package com.acoptic.betterswordfight.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import com.acoptic.betterswordfight.Main;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityDatabase {
	public static EntityDatabase instance = new EntityDatabase();
	private World world;

	protected final HashMap<Integer, EntityData<?>> entryMap = new HashMap<>();

	private EntityData<?> get(Integer identifier)
	{
		return entryMap.get(identifier);
	}

	public EntityData<?> get(Entity entity)
	{
		return this.get(entity.getEntityId());
	}

	private void add(int identifier, EntityData<?> data)
	{
		this.entryMap.put(identifier, data);
	}

	public void add(Entity entity, EntityData<?> data) {
		this.world = entity.getEntityWorld();
		this.add(entity.getEntityId(), data);
		Main.logger.info(entryMap.size() + "entities registered!");
	}
	
	public void update()
	{
		Iterator<Entry<Integer, EntityData<?>>> it = entryMap.entrySet().iterator();
		while (it.hasNext())
		{
			Entry<Integer, EntityData<?>> entry = it.next();

			EntityData<?> entityData = entry.getValue();
			Entity entityInData = entityData.getEntity();
			Entity entity = world.getEntityByID(entry.getKey());

			if (entityInData == null || entity != entityInData)
			{
				it.remove();
				Main.logger.info(entryMap.size() + "entities registered! (removal)");
			}
			else
			{
				entityData.update();
			}
		}
	}
	
	public void setWorld(World world)
	{
		this.world = world;
	}

}
