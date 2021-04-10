package com.simplyspawners.items;

import com.simplyspawners.Group;
import com.simplyspawners.Main;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemLoader {
	
	/** BLOCKS **/
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
	
	public static final RegistryObject<Item> EMPTY_EGG = ITEMS.register("emptyegg",() -> new ItemEmptyEgg(new Properties().group(Group.SIMPLY_SPAWNERS)));
	public static final RegistryObject<Item> SPAWNER_FRAGMENT = ITEMS.register("spawnerfragment",() -> new Item(new Properties().group(Group.SIMPLY_SPAWNERS)));

}
