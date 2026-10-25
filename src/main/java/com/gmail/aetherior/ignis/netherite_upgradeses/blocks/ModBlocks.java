package com.gmail.aetherior.ignis.netherite_upgradeses.blocks;

import com.gmail.aetherior.ignis.netherite_upgradeses.netherite_upgradeses;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, netherite_upgradeses.MODID);

    public static final RegistryObject<Block> ENDERANITE_ORE = BLOCKS.register("enderanite_ore", ()
            -> new Block(BlockBehaviour.Properties.of().destroyTime(10f).explosionResistance(1000f)));
    public static final RegistryObject<Block> RAW_ENDERANITE_BLOCK = BLOCKS.register("raw_enderanite_block", ()
            -> new Block(BlockBehaviour.Properties.of().destroyTime(15f).explosionResistance(2000f)));
    public static final RegistryObject<Block> ENDERANITE_BLOCK = BLOCKS.register("enderanite_block", ()
            -> new Block(BlockBehaviour.Properties.of().destroyTime(30f).explosionResistance(10000f)));

    public static final RegistryObject<Block> BLAZING_ORE = BLOCKS.register("blazing_ore", ()
            -> new Block(BlockBehaviour.Properties.of().destroyTime(10f).explosionResistance(1000f)));
    public static final RegistryObject<Block> RAW_BLAZING_BLOCK = BLOCKS.register("raw_blazing_block", ()
            -> new Block(BlockBehaviour.Properties.of().destroyTime(15f).explosionResistance(2000f)));
    public static final RegistryObject<Block> BLAZING_BLOCK = BLOCKS.register("blazing_block", ()
            -> new Block(BlockBehaviour.Properties.of().destroyTime(30f).explosionResistance(10000f)));

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
