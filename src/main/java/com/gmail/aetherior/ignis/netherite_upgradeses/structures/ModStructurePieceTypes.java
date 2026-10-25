package com.gmail.aetherior.ignis.netherite_upgradeses.structures;

import com.gmail.aetherior.ignis.netherite_upgradeses.worldgen.structures.NetheriteStructurePiece;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructurePieceTypes {
    public static final DeferredRegister<StructurePieceType> PIECE_TYPES =
            DeferredRegister.create(Registries.STRUCTURE_PIECE, "netherite_upgradeses");

    public static final RegistryObject<StructurePieceType> NETHERITE_PIECE =
            PIECE_TYPES.register("netherite_piece", () -> NetheriteStructurePiece::new);

    public static StructurePieceType createNetheritePiece() {
        return NetheriteStructurePiece::new;
    }
}
