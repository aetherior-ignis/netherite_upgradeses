package com.gmail.aetherior.ignis.netherite_upgradeses;

import com.gmail.aetherior.ignis.netherite_upgradeses.blocks.ModBlocks;
import com.gmail.aetherior.ignis.netherite_upgradeses.creativeModTabs.ModCreativeModTabs;
import com.gmail.aetherior.ignis.netherite_upgradeses.items.ModItems;
import com.gmail.aetherior.ignis.netherite_upgradeses.structures.ModStructurePieceTypes;
import com.gmail.aetherior.ignis.netherite_upgradeses.structures.ModStructures;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

@Mod(netherite_upgradeses.MODID)
public class netherite_upgradeses
{
    public static final String MODID = "netherite_upgradeses";
    private static final Logger LOGGER = LogUtils.getLogger();


    public netherite_upgradeses(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);
        ModStructures.STRUCTURE_TYPES.register(modEventBus);
        ModStructurePieceTypes.PIECE_TYPES.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        LOGGER.info("Structures registered: {}", ModStructures.NETHERITE_STRUCTURE.getId());
        LOGGER.info("Structure pieces registered: {}", ModStructurePieceTypes.NETHERITE_PIECE.getId());
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.RAW_ENDERANITE_NUGGET);
            event.accept(ModItems.RAW_ENDERANITE);
            event.accept(ModItems.ENDERANITE_NUGGET);
            event.accept(ModItems.ENDERANITE_INGOT);
            event.accept(ModItems.ENDERANITE_SMITHING_TEMPLATE);

            event.accept(ModItems.RAW_BLAZING_NUGGET);
            event.accept(ModItems.RAW_BLAZING);
            event.accept(ModItems.BLAZING_NUGGET);
            event.accept(ModItems.BLAZING_INGOT);
            event.accept(ModItems.BLAZING_SMITHING_TEMPLATE);
        }
        else if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModItems.ENDERANITE_ORE);

            event.accept(ModItems.BLAZING_ORE);
        }
        else if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModItems.RAW_ENDERANITE_BLOCK);
            event.accept(ModItems.ENDERANITE_ORE);
            event.accept(ModItems.ENDERANITE_BLOCK);

            event.accept(ModItems.RAW_BLAZING_BLOCK);
            event.accept(ModItems.BLAZING_ORE);
            event.accept(ModItems.BLAZING_BLOCK);
        }
        else if (event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(ModItems.ENDERANITE_SWORD);
            event.accept(ModItems.BLAZING_SWORD);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
