package com.simplyspawners.enchantments;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EnchantmentEggification extends Enchantment {

	protected EnchantmentEggification(Enchantment.Rarity rarity, EnchantmentCategory category, EquipmentSlot... slot) {
		
		super(rarity, category, slot);

	}
	
	/*@Override
	public int getMaxLevel() {
		
		return 1;
		
	}*/
	
	@Override
	public boolean isTreasureOnly() {
		
		return true;
	
	}

	@Override
	public boolean isAllowedOnBooks() {
		
		return false;
		
	}

}
