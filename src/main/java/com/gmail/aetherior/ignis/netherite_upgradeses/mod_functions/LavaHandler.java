package com.gmail.aetherior.ignis.netherite_upgradeses.mod_functions;

import com.gmail.aetherior.ignis.netherite_upgradeses.items.ModItems;
import com.gmail.aetherior.ignis.netherite_upgradeses.netherite_upgradeses;
import com.gmail.aetherior.ignis.netherite_upgradeses.utils.DelayedTaskManager;
import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = netherite_upgradeses.MODID)
public class LavaHandler {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void onItemInLava(ItemTossEvent event){
        if(event.getPlayer().level().isClientSide || !(event.getEntity().level() instanceof ServerLevel)){
            LOGGER.info("Client side or not ServerLevel!");
            return;
        }
        ItemEntity itemEntity = event.getEntity();
        ItemStack originalStack = itemEntity.getItem();
        if(originalStack.is(Items.ANCIENT_DEBRIS)){
            scheduleLavaCheck(itemEntity, event.getPlayer());
        }
    }

    private static void replaceItemInLava(ServerLevel level, BlockPos pos, int itemCount){
        if(level.isClientSide || !(level.isLoaded(pos))) {
            LOGGER.info("Client side in replaceItemInLava or level is not loaded!");
            return;
        }
        ItemStack newItemStack = new ItemStack(ModItems.BLAZING_NUGGET::get, itemCount);
        ItemEntity newItemEntity = new ItemEntity(level, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, newItemStack);
        level.addFreshEntity(newItemEntity);
    }

    private static void scheduleLavaCheck(ItemEntity itemEntity, Player player){
        if(!(itemEntity.level() instanceof ServerLevel serverLevel)) {
            LOGGER.info("Level is nor ServerLevel!");
            return;
        }
        Runnable task = () -> {
            if(itemEntity.isInLava() && !itemEntity.isRemoved()){
                BlockPos blockPos = itemEntity.blockPosition();
                itemEntity.remove(Entity.RemovalReason.DISCARDED);
                replaceItemInLava(serverLevel, blockPos, itemEntity.getItem().getCount());
            }
        };

        DelayedTaskManager.scheduleTask((ServerPlayer) player, 50, task);
    }
}
