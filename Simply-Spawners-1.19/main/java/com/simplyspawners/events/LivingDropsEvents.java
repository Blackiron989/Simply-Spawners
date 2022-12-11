package com.simplyspawners.events;

import java.util.Random;

import com.simplyspawners.config.Config;
import com.simplyspawners.enchantments.EnchantmentLoader;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingDropsEvents {
	
	@SubscribeEvent
	public static void dropsEvent(LivingDropsEvent event) {
		
		if(event.getSource().getDirectEntity() instanceof ServerPlayer) {
			
			ServerPlayer serverplayer = (ServerPlayer) event.getSource().getDirectEntity();
			
			ItemStack itemstack = serverplayer.getItemInHand(InteractionHand.MAIN_HAND);
			
			if(itemstack.isEnchanted() && event.getEntity() != null) {
				
				if(itemstack.getEnchantmentLevel(EnchantmentLoader.EGGIFICATION.get()) > 0) {

					EntityType<?> type = event.getEntity().getType();
					
					ItemStack spawnegg = null;
					
					Iterable<SpawnEggItem> iterable = SpawnEggItem.eggs();
					
					for(SpawnEggItem egg : iterable) {
						
						ItemStack stack = new ItemStack(egg);
						
						if(egg.spawnsEntity(stack.getTag(), type)) {

							spawnegg = stack;
							break;
							
						}
						
					}
					
					Random random = new Random();
					
					//double chance = 0.1;
					double chance = Math.max(Config.Holder.eggificationChance, 0);
					
					if(itemstack.getEnchantmentLevel(Enchantments.MOB_LOOTING) > 0 && chance > 0) {
						
						switch(itemstack.getEnchantmentLevel(Enchantments.MOB_LOOTING)) {
						
							case 1:
								
								//chance = 0.13;
								chance = Math.min(Config.Holder.eggificationChance + 0.03, 1);
								
							break;
							
							case 2:
								
								//chance = 0.16;
								chance = Math.min(Config.Holder.eggificationChance + 0.06, 1);
								
							break;
							
							default:
							case 3:
								
								//chance = 0.2;
								chance = Math.min(Config.Holder.eggificationChance + 0.1, 1);
								
							break;
						
						}
						
					}
					
					for(String str : Config.Holder.blacklistEggification) {
						
						if(spawnegg.getItem().toString().matches(str)) {
							
							spawnegg = null;
							break;
							
						}
						
					}
					
					if(spawnegg != null && !spawnegg.isEmpty() && random.nextDouble() < chance) {
					
						ItemEntity itementity = new ItemEntity(serverplayer.level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), spawnegg);
						
						event.getDrops().add(itementity);
						
					}
					
				}
				
			}
			
		}
		
	}

}
