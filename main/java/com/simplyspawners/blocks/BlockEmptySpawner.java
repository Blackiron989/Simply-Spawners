package com.simplyspawners.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.spawner.AbstractSpawner;

public class BlockEmptySpawner extends Block {

	public BlockEmptySpawner(Properties properties) {
		
		super(properties);

	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState blockstate, World world, BlockPos blockpos, PlayerEntity playerentity, Hand hand, BlockRayTraceResult raytraceresult) {
		
		if(!world.isRemote) {
			
			ItemStack itemstack = playerentity.getHeldItem(hand);
			
			if(itemstack.getItem() instanceof SpawnEggItem) {
				
				TileEntity tileentity = new MobSpawnerTileEntity();
				
				AbstractSpawner abstractspawner = ((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic();
				
				EntityType<?> entitytype = ((SpawnEggItem) itemstack.getItem()).getType(itemstack.getTag());
				
				abstractspawner.setEntityType(entitytype);

				BlockState spawnerstate = Blocks.SPAWNER.getDefaultState();
				
				world.setTileEntity(blockpos, tileentity);
				world.setBlockState(blockpos, spawnerstate);
				world.notifyBlockUpdate(blockpos, blockstate, spawnerstate, 3);

	            return ActionResultType.PASS;
				
			}
			
		}
		
		return ActionResultType.PASS;
		
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState blockstate) {
		
		return BlockRenderType.MODEL;
		
	}

}
