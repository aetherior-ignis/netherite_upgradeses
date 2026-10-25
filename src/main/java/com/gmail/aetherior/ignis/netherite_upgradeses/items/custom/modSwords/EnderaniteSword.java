package com.gmail.aetherior.ignis.netherite_upgradeses.items.custom.modSwords;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
public class EnderaniteSword extends SwordItem {

    public EnderaniteSword(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        return false;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);
         if(!pLevel.isClientSide){
             ThrownEnderpearl enderpearl = new ThrownEnderpearl(pLevel, pPlayer);

             Vec3 lookAngle = pPlayer.getLookAngle();
             enderpearl.shoot(lookAngle.x, lookAngle.y, lookAngle.z, 2.0F, 0.0F);
             pLevel.addFreshEntity(enderpearl);

             itemStack.hurtAndBreak(1, pPlayer, (p) -> p.broadcastBreakEvent(pUsedHand));
             pPlayer.getCooldowns().addCooldown(this, 100);
         }

        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
