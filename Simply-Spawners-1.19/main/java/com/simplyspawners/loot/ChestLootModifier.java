package com.simplyspawners.loot;

import java.util.Random;
import java.util.function.Supplier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.simplyspawners.Main;
import com.simplyspawners.config.Config;
import com.simplyspawners.enchantments.EnchantmentLoader;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ChestLootModifier {
	
	/** Registry **/
	
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Main.MODID);

	@SuppressWarnings("unused")
	private static final RegistryObject<Codec<BastionBridgeModifier>> BASTION_BRIDGE = GLOBAL_LOOT_MODIFIER.register("bastion_bridge", BastionBridgeModifier.CODEC);
	@SuppressWarnings("unused")
	private static final RegistryObject<Codec<BastionHoglinStableModifier>> BASTION_HOGLIN_STABLE = GLOBAL_LOOT_MODIFIER.register("bastion_hoglin_stable", BastionHoglinStableModifier.CODEC);
	@SuppressWarnings("unused")
	private static final RegistryObject<Codec<BastionOtherModifier>> BASTION_OTHER = GLOBAL_LOOT_MODIFIER.register("bastion_other", BastionOtherModifier.CODEC);
	@SuppressWarnings("unused")
	private static final RegistryObject<Codec<BastionTreasureModifier>> BASTION_TREASURE = GLOBAL_LOOT_MODIFIER.register("bastion_treasure", BastionTreasureModifier.CODEC);
	@SuppressWarnings("unused")
	private static final RegistryObject<Codec<NetherBridgeModifier>> NETHER_BRIDGE = GLOBAL_LOOT_MODIFIER.register("nether_bridge", NetherBridgeModifier.CODEC);
	@SuppressWarnings("unused")
	private static final RegistryObject<Codec<RuinedPortalModifier>> RUINDED_PORTAL = GLOBAL_LOOT_MODIFIER.register("ruined_portal", RuinedPortalModifier.CODEC);
	
	@SubscribeEvent
    public static void runData(GatherDataEvent event)
    {
		
		event.getGenerator().addProvider(event.includeServer(), new DataProvider(event.getGenerator(), Main.MODID));
        
    }
	
	private static class DataProvider extends GlobalLootModifierProvider {

		public DataProvider(DataGenerator gen, String modid) {
			
			super(gen, modid);

		}

		@Override
		protected void start() {
			
			add("bastion_bridge", new BastionBridgeModifier(
					
                    new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/bastion_bridge")).build() }, 1)
					
            );
			add("bastion_hoglin_stable", new BastionHoglinStableModifier(
					
                    new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/bastion_hoglin_stable")).build() }, 1)
					
            );
			add("bastion_other", new BastionOtherModifier(
					
                    new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/bastion_other")).build() }, 1)
					
            );
			add("bastion_treasure", new BastionTreasureModifier(
					
			        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/bastion_treasure")).build() }, 1)
					
			);
			add("nether_bridge", new NetherBridgeModifier(
					
			        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/nether_bridge")).build() }, 1)
					
			);
			add("ruined_portal", new RuinedPortalModifier(
					
			        new LootItemCondition[] { LootTableIdCondition.builder(new ResourceLocation("chests/ruined_portal")).build() }, 1)
					
			);

		}
		
	}
	
	private static class BastionBridgeModifier extends LootModifier {
		
		public static final Supplier<Codec<BastionBridgeModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
				.and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("multiplication_factor", 2).forGetter(m -> m.multiplicationFactor))
				.apply(inst, BastionBridgeModifier::new)));
		
		private final int multiplicationFactor;
		
		public BastionBridgeModifier(final LootItemCondition[] conditionsIn, final int multiplicationFactor) {
			
			super(conditionsIn);
			this.multiplicationFactor = multiplicationFactor;
			
		}
		
		@Override
		protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
			
			if(!Config.Holder.lootSpawn) {

				Random random = new Random();
				if(random.nextInt(10) > 8) {
					
					ItemStack itemstack = new ItemStack(Items.ENCHANTED_BOOK, 1);
					EnchantedBookItem.addEnchantment(itemstack, new EnchantmentInstance(EnchantmentLoader.EGGIFICATION.get(), 1));
					generatedLoot.add(itemstack);
					
				}
			
			}
			return generatedLoot;
			
		}
		
		@Override
		public Codec<? extends IGlobalLootModifier> codec() {
			
			return CODEC.get();
			
		}
		
	}
	
	private static class BastionHoglinStableModifier extends LootModifier {
		
		public static final Supplier<Codec<BastionHoglinStableModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
				.and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("multiplication_factor", 2).forGetter(m -> m.multiplicationFactor))
				.apply(inst, BastionHoglinStableModifier::new)));
		
		private final int multiplicationFactor;
		
		public BastionHoglinStableModifier(final LootItemCondition[] conditionsIn, final int multiplicationFactor) {
			
			super(conditionsIn);
			this.multiplicationFactor = multiplicationFactor;
			
		}
		
		@Override
		protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
			
			if(!Config.Holder.lootSpawn) {

				Random random = new Random();
				if(random.nextInt(10) > 8) {
					
					ItemStack itemstack = new ItemStack(Items.ENCHANTED_BOOK, 1);
					EnchantedBookItem.addEnchantment(itemstack, new EnchantmentInstance(EnchantmentLoader.EGGIFICATION.get(), 1));
					generatedLoot.add(itemstack);
					
				}
			
			}
			return generatedLoot;
			
		}
		
		@Override
		public Codec<? extends IGlobalLootModifier> codec() {
			
			return CODEC.get();
			
		}
		
	}
	
	private static class BastionOtherModifier extends LootModifier {
		
		public static final Supplier<Codec<BastionOtherModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
				.and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("multiplication_factor", 2).forGetter(m -> m.multiplicationFactor))
				.apply(inst, BastionOtherModifier::new)));
		
		private final int multiplicationFactor;
		
		public BastionOtherModifier(final LootItemCondition[] conditionsIn, final int multiplicationFactor) {
			
			super(conditionsIn);
			this.multiplicationFactor = multiplicationFactor;
			
		}
		
		@Override
		protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
			
			if(!Config.Holder.lootSpawn) {

				Random random = new Random();
				if(random.nextInt(10) > 8) {
					
					ItemStack itemstack = new ItemStack(Items.ENCHANTED_BOOK, 1);
					EnchantedBookItem.addEnchantment(itemstack, new EnchantmentInstance(EnchantmentLoader.EGGIFICATION.get(), 1));
					generatedLoot.add(itemstack);
					
				}
			
			}
			return generatedLoot;
			
		}
		
		@Override
		public Codec<? extends IGlobalLootModifier> codec() {
			
			return CODEC.get();
			
		}
		
	}
	
	private static class BastionTreasureModifier extends LootModifier {
		
		public static final Supplier<Codec<BastionTreasureModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
				.and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("multiplication_factor", 2).forGetter(m -> m.multiplicationFactor))
				.apply(inst, BastionTreasureModifier::new)));
		
		private final int multiplicationFactor;
		
		public BastionTreasureModifier(final LootItemCondition[] conditionsIn, final int multiplicationFactor) {
			
			super(conditionsIn);
			this.multiplicationFactor = multiplicationFactor;
			
		}
		
		@Override
		protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
			
			if(!Config.Holder.lootSpawn) {

				Random random = new Random();
				if(random.nextInt(10) > 8) {
					
					ItemStack itemstack = new ItemStack(Items.ENCHANTED_BOOK, 1);
					EnchantedBookItem.addEnchantment(itemstack, new EnchantmentInstance(EnchantmentLoader.EGGIFICATION.get(), 1));
					generatedLoot.add(itemstack);
					
				}
			
			}
			return generatedLoot;
			
		}
		
		@Override
		public Codec<? extends IGlobalLootModifier> codec() {
			
			return CODEC.get();
			
		}
		
	}
	
	private static class NetherBridgeModifier extends LootModifier {
		
		public static final Supplier<Codec<NetherBridgeModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
				.and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("multiplication_factor", 2).forGetter(m -> m.multiplicationFactor))
				.apply(inst, NetherBridgeModifier::new)));
		
		private final int multiplicationFactor;
		
		public NetherBridgeModifier(final LootItemCondition[] conditionsIn, final int multiplicationFactor) {
			
			super(conditionsIn);
			this.multiplicationFactor = multiplicationFactor;
			
		}
		
		@Override
		protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
			
			if(!Config.Holder.lootSpawn) {

				Random random = new Random();
				if(random.nextInt(10) > 8) {
					
					ItemStack itemstack = new ItemStack(Items.ENCHANTED_BOOK, 1);
					EnchantedBookItem.addEnchantment(itemstack, new EnchantmentInstance(EnchantmentLoader.EGGIFICATION.get(), 1));
					generatedLoot.add(itemstack);
					
				}
			
			}
			return generatedLoot;
			
		}
		
		@Override
		public Codec<? extends IGlobalLootModifier> codec() {
			
			return CODEC.get();
			
		}
		
	}
	
	private static class RuinedPortalModifier extends LootModifier {
		
		public static final Supplier<Codec<RuinedPortalModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst)
				.and(ExtraCodecs.POSITIVE_INT.optionalFieldOf("multiplication_factor", 2).forGetter(m -> m.multiplicationFactor))
				.apply(inst, RuinedPortalModifier::new)));
		
		private final int multiplicationFactor;
		
		public RuinedPortalModifier(final LootItemCondition[] conditionsIn, final int multiplicationFactor) {
			
			super(conditionsIn);
			this.multiplicationFactor = multiplicationFactor;
			
		}
		
		@Override
		protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
			
			if(!Config.Holder.lootSpawn) {

				Random random = new Random();
				if(random.nextInt(10) > 8) {
					
					ItemStack itemstack = new ItemStack(Items.ENCHANTED_BOOK, 1);
					EnchantedBookItem.addEnchantment(itemstack, new EnchantmentInstance(EnchantmentLoader.EGGIFICATION.get(), 1));
					generatedLoot.add(itemstack);
					
				}
			
			}
			return generatedLoot;
			
		}
		
		@Override
		public Codec<? extends IGlobalLootModifier> codec() {
			
			return CODEC.get();
			
		}
		
	}

}
