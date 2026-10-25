package com.gmail.aetherior.ignis.netherite_upgradeses.creativeModTabs;

import com.gmail.aetherior.ignis.netherite_upgradeses.items.ModItems;
import com.gmail.aetherior.ignis.netherite_upgradeses.netherite_upgradeses;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, netherite_upgradeses.MODID);

    public static final RegistryObject<CreativeModeTab> FIRST_TAB = CREATIVE_MODE_TABS.register("netherite_upgradeses_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.RAW_ENDERANITE.get()))
            .title(Component.translatable("creativetab.netherite_upgradeses_tab"))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(ModItems.ENDERANITE_ORE.get());
                pOutput.accept(ModItems.RAW_ENDERANITE_NUGGET.get());
                pOutput.accept(ModItems.RAW_ENDERANITE.get());
                pOutput.accept(ModItems.RAW_ENDERANITE_BLOCK.get());
                pOutput.accept(ModItems.ENDERANITE_NUGGET.get());
                pOutput.accept(ModItems.ENDERANITE_INGOT.get());
                pOutput.accept(ModItems.ENDERANITE_BLOCK.get());
                pOutput.accept(ModItems.ENDERANITE_SMITHING_TEMPLATE.get());
                pOutput.accept(ModItems.ENDERANITE_SWORD.get());

                pOutput.accept(ModItems.BLAZING_ORE.get());
                pOutput.accept(ModItems.RAW_BLAZING_NUGGET.get());
                pOutput.accept(ModItems.RAW_BLAZING.get());
                pOutput.accept(ModItems.RAW_BLAZING_BLOCK.get());
                pOutput.accept(ModItems.BLAZING_NUGGET.get());
                pOutput.accept(ModItems.BLAZING_INGOT.get());
                pOutput.accept(ModItems.BLAZING_BLOCK.get());
                pOutput.accept(ModItems.BLAZING_SMITHING_TEMPLATE.get());
                pOutput.accept(ModItems.BLAZING_SWORD.get());

            })
            .build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}