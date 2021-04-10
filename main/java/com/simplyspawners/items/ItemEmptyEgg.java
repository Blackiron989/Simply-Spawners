package com.simplyspawners.items;

import com.simplyspawners.blocks.BlockLoader;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemEmptyEgg extends Item {

	public ItemEmptyEgg(Properties properties) {
		
		super(properties);

	}
	
	@Override
	public ActionResultType onItemUse(ItemUseContext context) {

		World world = context.getWorld();
		PlayerEntity playerentity = context.getPlayer();
		BlockPos blockpos = context.getPos();
		BlockState blockstate = world.getBlockState(blockpos);
		
		if(!world.isRemote) {
			
			if(blockstate.getBlock() == Blocks.SPAWNER) {
				
				world.removeTileEntity(blockpos);
				world.setBlockState(blockpos, BlockLoader.EMPTY_SPAWNER.get().getDefaultState());
				world.notifyBlockUpdate(blockpos, blockstate, BlockLoader.EMPTY_SPAWNER.get().getDefaultState(), 3);
				
				int i = 15 + world.rand.nextInt(15) + world.rand.nextInt(15);
				
				while(i > 0) {
					
					int j = ExperienceOrbEntity.getXPSplit(i);
					i -= j;
					world.addEntity(new ExperienceOrbEntity(world, blockpos.getX(), blockpos.getY(), blockpos.getZ(), j));
					
				}
				
				if(playerentity.isCreative()) {

					return ActionResultType.PASS;
					
				}
				
				world.playSound(null, blockpos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.PLAYERS, 1F, 1F);
				context.getItem().shrink(1);
				return ActionResultType.PASS;
				
			}
			
		}
		
		return ActionResultType.PASS;
		
	}

}
