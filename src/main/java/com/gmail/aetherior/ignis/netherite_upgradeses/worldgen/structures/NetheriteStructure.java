package com.gmail.aetherior.ignis.netherite_upgradeses.worldgen.structures;

import com.gmail.aetherior.ignis.netherite_upgradeses.structures.ModStructures;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import java.util.Optional;

public class NetheriteStructure extends Structure {
    public static final Codec<NetheriteStructure> CODEC = simpleCodec(NetheriteStructure::new);

    public NetheriteStructure(StructureSettings settings) {
        super(settings);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        ChunkPos chunkPos = context.chunkPos();
        int x = chunkPos.getBlockX(9);
        int z = chunkPos.getBlockZ(9);

        int searchStartY = 120;
        int foundY = -1;

        var column = context.chunkGenerator().getBaseColumn(x, z, context.heightAccessor(), context.randomState());

        for (int checkY = searchStartY; checkY > 0; checkY--) {
            var state = column.getBlock(checkY);

            if (!state.isAir() && state.getFluidState().isEmpty()) {
                if (column.getBlock(checkY + 1).isAir() && column.getBlock(checkY + 2).isAir()
                        && column.getBlock(checkY + 3).isAir() && column.getBlock(checkY + 4).isAir()) {
                    foundY = checkY;
                    break;
                }
            }
        }

        if (foundY == -1) {
            foundY = 30;
        }

        BlockPos pos = new BlockPos(x, foundY, z);
        final int finalY = foundY;

        return Optional.of(new GenerationStub(
                pos,
                builder -> generatePieces(builder, context, finalY)
        ));
    }

    private void generatePieces(StructurePiecesBuilder builder, GenerationContext context, int finalY) {
        ResourceLocation structureLocation = new ResourceLocation("netherite_upgradeses", "netherite_structure");
        Rotation rotation = Rotation.getRandom(context.random());

        BlockPos startPos = new BlockPos(
                context.chunkPos().getMinBlockX(),
                finalY,
                context.chunkPos().getMinBlockZ()
        );

        NetheriteStructurePiece startPiece = new NetheriteStructurePiece(
                context.structureTemplateManager(),
                structureLocation,
                startPos,
                rotation
        );

        builder.addPiece(startPiece);
    }

    @Override
    public StructureType<?> type() {
        return ModStructures.NETHERITE_STRUCTURE.get();
    }
}