package com.simplyspawners.enchantments;

import com.simplyspawners.Main;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentLoader {
	
	/** ENCHANTMENTS **/
	public static final DeferredRegister<Enchantment> ENCHANTMENT = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Main.MODID);
	
	public static final RegistryObject<Enchantment> EGGIFICATION = ENCHANTMENT.register("eggification", () -> new EnchantmentEggification(Rarity.RARE, EnchantmentType.WEAPON, EquipmentSlotType.MAINHAND));

}
