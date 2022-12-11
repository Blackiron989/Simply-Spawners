package com.simplyspawners.enchantments;

import com.simplyspawners.Main;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentLoader {
	
	/** ENCHANTMENTS **/
	public static final DeferredRegister<Enchantment> ENCHANTMENT = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Main.MODID);
	
	public static final RegistryObject<Enchantment> EGGIFICATION = ENCHANTMENT.register("eggification", () -> new EnchantmentEggification(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));
	
}
