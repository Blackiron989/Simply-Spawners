package com.simplyspawners.events;

import java.util.Random;

import com.simplyspawners.items.ItemLoader;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class BreakEvents {
	
	@SubscribeEvent
	public static void breakEvent(BlockEvent.BreakEvent event) {
		
		LevelAccessor level = event.getPlayer().getLevel();
		
		if(!level.isClientSide()) {
		
			if(event.getPlayer() != null) {
				
				Player player = event.getPlayer();
				
				ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
				
				if(itemstack.isEnchanted() && event.getState().getBlock() == Blocks.SPAWNER) {
	
					Random random = new Random();
					BlockPos blockpos = event.getPos();
					
					switch(itemstack.getEnchantmentLevel(Enchantments.BLOCK_FORTUNE)) {
					
						case 1:
	
							ItemEntity f1 = new ItemEntity((Level) level, blockpos.getX(), blockpos.getY(), blockpos.getZ(), new ItemStack(ItemLoader.SPAWNER_FRAGMENT.get(), 2 + (int)Math.round(random.nextDouble())));
							
							level.addFreshEntity(f1);
							
						break;
							
						case 2:
	
							ItemEntity f2 = new ItemEntity((Level) level, blockpos.getX(), blockpos.getY(), blockpos.getZ(), new ItemStack(ItemLoader.SPAWNER_FRAGMENT.get(), 3 + (int)Math.round(random.nextDouble())));
							
							level.addFreshEntity(f2);
							
						break;
							
						case 3:
	
							ItemEntity f3 = new ItemEntity((Level) level, blockpos.getX(), blockpos.getY(), blockpos.getZ(), new ItemStack(ItemLoader.SPAWNER_FRAGMENT.get(), 4));
							
							level.addFreshEntity(f3);
							
						break;
							
						default:break;
					
					}
					
				}
				
			}
		
		}
		
	}

}
