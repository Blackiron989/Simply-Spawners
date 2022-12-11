package com.simplyspawners.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;

public class BlockEmptySpawner extends Block {

	public BlockEmptySpawner(Properties properties) {
		
		super(properties);

	}
	
	@Override
	public InteractionResult use(BlockState blockstate, Level level, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult raytraceresult) {
		
		if(!level.isClientSide()) {
			
			ItemStack itemstack = player.getItemInHand(hand);
			
			if(itemstack.getItem() instanceof SpawnEggItem) {
				
				BlockEntity blockentity = new SpawnerBlockEntity(blockpos, blockstate);
				
				BaseSpawner basespawner = ((SpawnerBlockEntity) blockentity).getSpawner();
				
				EntityType<?> entitytype = ((SpawnEggItem) itemstack.getItem()).getType(itemstack.getTag());
				
				basespawner.setEntityId(entitytype);

				BlockState spawnerstate = Blocks.SPAWNER.defaultBlockState();
				
				level.setBlockEntity(blockentity);
				level.setBlockAndUpdate(blockpos, spawnerstate);
				//level.notifyBlockUpdate(blockpos, blockstate, spawnerstate, 3);

	            return InteractionResult.PASS;
				
			}
			
		}
		
		return InteractionResult.PASS;
		
	}
	
	@Override
	public RenderShape getRenderShape(BlockState blockstate) {
		
		return RenderShape.MODEL;
	
	}

}
