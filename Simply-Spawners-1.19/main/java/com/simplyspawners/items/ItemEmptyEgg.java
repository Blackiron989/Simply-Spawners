package com.simplyspawners.items;

import com.simplyspawners.blocks.BlockLoader;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class ItemEmptyEgg extends Item {

	public ItemEmptyEgg(Properties properties) {
		
		super(properties);

	}
	
	@Override
	public InteractionResult useOn(UseOnContext context) {

		Level level = context.getLevel();
		Player player = context.getPlayer();
		BlockPos blockpos = context.getClickedPos();
		BlockState blockstate = level.getBlockState(blockpos);
		
		if(!level.isClientSide()) {
			
			if(blockstate.getBlock() == Blocks.SPAWNER) {
				
				level.removeBlockEntity(blockpos);
				level.setBlockAndUpdate(blockpos, BlockLoader.EMPTY_SPAWNER.get().defaultBlockState());
				
				int i = 15 + level.random.nextInt(15) + level.random.nextInt(15);
				
				while(i > 0) {
					
					int j = ExperienceOrb.getExperienceValue(i);
					i -= j;
					level.addFreshEntity(new ExperienceOrb(level, blockpos.getX(), blockpos.getY(), blockpos.getZ(), j));
					
				}
				
				if(player.isCreative()) {

					return InteractionResult.PASS;
					
				}
				
				level.playSound(null, blockpos, SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.PLAYERS, 1F, 1F);
				context.getItemInHand().shrink(1);
				return InteractionResult.PASS;
				
			}
			
		}
		
		return InteractionResult.PASS;
		
	}

}
