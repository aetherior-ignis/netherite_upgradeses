package com.gmail.aetherior.ignis.netherite_upgradeses.items;

import com.gmail.aetherior.ignis.netherite_upgradeses.blocks.ModBlocks;
import com.gmail.aetherior.ignis.netherite_upgradeses.items.custom.modSwords.BlazeSword;
import com.gmail.aetherior.ignis.netherite_upgradeses.items.custom.modSwords.EnderaniteSword;
import com.gmail.aetherior.ignis.netherite_upgradeses.netherite_upgradeses;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, netherite_upgradeses.MODID);

    public static final RegistryObject<Item> ENDERANITE_ORE = ITEMS.register("enderanite_ore", ()
            -> new BlockItem(ModBlocks.ENDERANITE_ORE.get(), new Item.Properties().stacksTo(32).rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> RAW_ENDERANITE_BLOCK = ITEMS.register("raw_enderanite_block", ()
            -> new BlockItem(ModBlocks.RAW_ENDERANITE_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ENDERANITE_BLOCK = ITEMS.register("enderanite_block", ()
            -> new BlockItem(ModBlocks.ENDERANITE_BLOCK.get(), new Item.Properties().fireResistant().stacksTo(8)));

    public static final RegistryObject<Item> RAW_ENDERANITE_NUGGET = ITEMS.register("raw_enderanite_nugget", ()
            -> new Item(new Item.Properties().stacksTo(32).fireResistant()));
    public static final RegistryObject<Item> RAW_ENDERANITE = ITEMS.register("raw_enderanite", ()
            -> new Item(new Item.Properties().stacksTo(16).fireResistant()));
    public static final RegistryObject<Item> ENDERANITE_NUGGET = ITEMS.register("enderanite_nugget", ()
            -> new Item(new Item.Properties().stacksTo(32).fireResistant()));
    public static final RegistryObject<Item> ENDERANITE_INGOT = ITEMS.register("enderanite_ingot", ()
            -> new Item(new Item.Properties().stacksTo(16).fireResistant()));
    public static final RegistryObject<Item> ENDERANITE_SMITHING_TEMPLATE = ITEMS.register("enderanite_smithing_template", ()
            -> new Item(new Item.Properties().stacksTo(4).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> ENDERANITE_SWORD = ITEMS.register("enderanite_sword", ()
            -> new EnderaniteSword(Tiers.NETHERITE, 9, -2.5F, new Item.Properties().durability(1024).fireResistant()));


    public static final RegistryObject<Item> BLAZING_ORE = ITEMS.register("blazing_ore", ()
            -> new BlockItem(ModBlocks.BLAZING_ORE.get(), new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(32)));
    public static final RegistryObject<Item> RAW_BLAZING_BLOCK = ITEMS.register("raw_blazing_block", ()
            -> new BlockItem(ModBlocks.RAW_BLAZING_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> BLAZING_BLOCK = ITEMS.register("blazing_block", ()
            -> new BlockItem(ModBlocks.BLAZING_BLOCK.get(), new Item.Properties().fireResistant().stacksTo(8)));

    public static final RegistryObject<Item> RAW_BLAZING_NUGGET = ITEMS.register("raw_blazing_nugget", ()
            -> new Item(new Item.Properties().stacksTo(32).fireResistant()));
    public static final RegistryObject<Item> RAW_BLAZING = ITEMS.register("raw_blazing", ()
            -> new Item(new Item.Properties().stacksTo(16).fireResistant()));
    public static final RegistryObject<Item> BLAZING_NUGGET = ITEMS.register("blazing_nugget", ()
            -> new Item(new Item.Properties().stacksTo(32).fireResistant()));
    public static final RegistryObject<Item> BLAZING_INGOT = ITEMS.register("blazing_ingot", ()
            -> new Item(new Item.Properties().stacksTo(16).fireResistant()));
    public static final RegistryObject<Item> BLAZING_SMITHING_TEMPLATE = ITEMS.register("blazing_smithing_template", ()
            -> new Item(new Item.Properties().stacksTo(4).fireResistant().rarity(Rarity.EPIC)));
    public static final RegistryObject<Item> BLAZING_SWORD = ITEMS.register("blazing_sword", ()
            -> new BlazeSword(Tiers.NETHERITE, 6, -2.5F, new Item.Properties().durability(1024).fireResistant()));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
