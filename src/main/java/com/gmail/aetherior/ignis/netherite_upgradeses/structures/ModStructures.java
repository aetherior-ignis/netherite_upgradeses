package com.gmail.aetherior.ignis.netherite_upgradeses.structures;

import com.gmail.aetherior.ignis.netherite_upgradeses.worldgen.structures.NetheriteStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructures {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_TYPE, "netherite_upgradeses");

    public static final RegistryObject<StructureType<NetheriteStructure>> NETHERITE_STRUCTURE =
            STRUCTURE_TYPES.register("netherite_structure", () -> () -> NetheriteStructure.CODEC);
}
