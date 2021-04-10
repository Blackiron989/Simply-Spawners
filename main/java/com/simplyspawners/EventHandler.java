package com.simplyspawners;

import java.util.Random;

import com.simplyspawners.enchantments.EnchantmentLoader;
import com.simplyspawners.items.ItemLoader;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class EventHandler {
	
	@SubscribeEvent
	public static void lootTableLoad(LootTableLoadEvent event) {
		
		if(event.getName().equals(new ResourceLocation("minecraft", "chests/bastion_bridge"))) {
			
			event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(Main.MODID, "inject/bastion_bridge"))).build());
			
		}
		
		if(event.getName().equals(new ResourceLocation("minecraft", "chests/bastion_hoglin"))) {
			
			event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(Main.MODID, "inject/bastion_hoglin"))).build());
			
		}

		if(event.getName().equals(new ResourceLocation("minecraft", "chests/bastion_other"))) {
			
			event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(Main.MODID, "inject/bastion_other"))).build());
			
		}
		
		if(event.getName().equals(new ResourceLocation("minecraft", "chests/bastion_treasure"))) {
			
			event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(Main.MODID, "inject/bastion_treasure"))).build());
			
		}
		
		if(event.getName().equals(new ResourceLocation("minecraft", "chests/nether_bridge"))) {
			
			event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(Main.MODID, "inject/nether_bridge"))).build());
			
		}
		
		if(event.getName().equals(new ResourceLocation("minecraft", "chests/ruined_portal"))) {
			
			event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(Main.MODID, "inject/ruined_portal"))).build());
			
		}
		
	}
	
	@SubscribeEvent
	public static void breakEvent(BreakEvent event) {
		
		if(!event.getWorld().isRemote()) {
		
			if(event.getPlayer() != null) {
				
				PlayerEntity playerentity = event.getPlayer();
				
				ItemStack itemstack = playerentity.getHeldItemMainhand();
				
				if(itemstack.isEnchanted() && event.getState().getBlock() == Blocks.SPAWNER) {
	
					Random random = new Random();
					
					switch(EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, itemstack)) {
					
						case 1:
	
							ItemEntity f1 = new ItemEntity((World) event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemLoader.SPAWNER_FRAGMENT.get(), 2 + (int)Math.round(random.nextDouble())));
							
							if(!event.getWorld().isRemote()) {
								
								event.getWorld().addEntity(f1);
							
							}
							
						break;
							
						case 2:
	
							ItemEntity f2 = new ItemEntity((World) event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemLoader.SPAWNER_FRAGMENT.get(), 3 + (int)Math.round(random.nextDouble())));
							
							if(!event.getWorld().isRemote()) {
								
								event.getWorld().addEntity(f2);
							
							}
							
						break;
							
						case 3:
	
							ItemEntity f3 = new ItemEntity((World) event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack(ItemLoader.SPAWNER_FRAGMENT.get(), 4));
							
							if(!event.getWorld().isRemote()) {
								
								event.getWorld().addEntity(f3);
							
							}
							
						break;
							
						default:break;
					
					}
					
				}
				
			}
		
		}
		
	}
	
	@SubscribeEvent
	public static void dropsEvent(LivingDropsEvent event) {
		
		if(event.getSource().getTrueSource() instanceof ServerPlayerEntity) {
			
			ServerPlayerEntity serverplayer = (ServerPlayerEntity) event.getSource().getTrueSource();
			
			ItemStack itemstack = serverplayer.getHeldItemMainhand();
			
			if(itemstack.isEnchanted() && event.getEntity() != null) {
				
				if(EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.EGGIFICATION.get(), itemstack) > 0) {

					EntityType<?> type = event.getEntity().getType();
					
					ItemStack spawnegg = null;
					
					Iterable<SpawnEggItem> iterable = SpawnEggItem.getEggs();
					
					for(SpawnEggItem egg : iterable) {
						
						ItemStack stack = new ItemStack(egg);
						
						if(egg.hasType(stack.getTag(), type)) {

							spawnegg = stack;
							break;
							
						}
						
					}
					
					Random random = new Random();
					
					double chance = 0.1;
					
					if(EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, itemstack) > 0) {
						
						switch(EnchantmentHelper.getEnchantmentLevel(Enchantments.LOOTING, itemstack)) {
						
							case 1:
								
								chance = 0.13;
								
							break;
							
							case 2:
								
								chance = 0.16;
								
							break;
							
							default:
							case 3:
								
								chance = 0.2;
								
							break;
						
						}
						
					}
					
					if(spawnegg != null && !spawnegg.isEmpty() && random.nextDouble() < chance) {
					
						ItemEntity itementity = new ItemEntity(serverplayer.world, event.getEntity().getPosX(), event.getEntity().getPosY(), event.getEntity().getPosZ(), spawnegg);
						
						event.getDrops().add(itementity);
						
					}
					
				}
				
			}
			
		}
		
	}

}
