package com.gmail.aetherior.ignis.netherite_upgradeses.worldgen.structures;

import com.gmail.aetherior.ignis.netherite_upgradeses.structures.ModStructurePieceTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class NetheriteStructurePiece extends TemplateStructurePiece {
    private static final Logger LOGGER = LogManager.getLogger();

    public NetheriteStructurePiece(StructureTemplateManager manager, ResourceLocation location, BlockPos pos, Rotation rotation) {
        super(ModStructurePieceTypes.NETHERITE_PIECE.get(), 0, manager, location, location.toString(),
                makeSettings(rotation), pos);
    }

    public NetheriteStructurePiece(StructurePieceSerializationContext context, CompoundTag tag) {
        super(ModStructurePieceTypes.NETHERITE_PIECE.get(), tag,
                context.structureTemplateManager(),
                (location) -> makeSettings(Rotation.NONE));

    }

    private static StructurePlaceSettings makeSettings(Rotation rotation) {
        return new StructurePlaceSettings()
                .setRotation(rotation)
                .setMirror(Mirror.NONE)
                .addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
    }

    @Override
    protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor level,
                                    RandomSource random, BoundingBox sbox) {
        LOGGER.info("handleDataMarker called: {} at {}", function, pos);
    }

    @Override
    public void postProcess(WorldGenLevel level, StructureManager manager, ChunkGenerator generator,
                            RandomSource random, BoundingBox box, ChunkPos chunkPos, BlockPos blockPos) {
        super.postProcess(level, manager, generator, random, box, chunkPos, blockPos);

        if (!(level instanceof ServerLevelAccessor)) {
            return;
        }

        replaceMarkers(level, box, blockPos, random);
        LOGGER.info("postProcess called at: {}", blockPos);
    }

    private void replaceMarkers(WorldGenLevel level, BoundingBox box, BlockPos pos, RandomSource random){
        BlockPos.betweenClosed(box.minX(), box.minY(), box.minZ(),
                box.maxX(), box.maxY(), box.maxZ()).forEach(blockPos -> {
                    try {
                        BlockState state = level.getBlockState(blockPos);

                        if (state.is(Blocks.RED_WOOL)) {
                            level.setBlock(blockPos, Blocks.CHEST.defaultBlockState(), 3);
                            setupLootChest((ServerLevelAccessor) level, blockPos, random);
                        } else if (state.is(Blocks.BLACK_WOOL)) {
                            level.setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
                            level.setBlock(blockPos.below(), Blocks.AIR.defaultBlockState(), 3);
                            level.setBlock(blockPos.above(), Blocks.AIR.defaultBlockState(), 3);
                            spawnWitherBoss((ServerLevelAccessor) level, blockPos, random);
                        }
                    } catch (Exception e){
                        LOGGER.error("Error: ", e);
                    }

        });
    }

    private void setupLootChest(ServerLevelAccessor level, BlockPos chestPos, RandomSource random) {
        if (level.getBlockState(chestPos).is(Blocks.CHEST)) {
            if (level.getBlockEntity(chestPos) instanceof ChestBlockEntity chest) {
                ResourceLocation lootTable = new ResourceLocation("netherite_upgradeses", "chests/netherite_loot");
                chest.setLootTable(lootTable, random.nextLong());
            }
        }
    }

    private void spawnWitherBoss(ServerLevelAccessor level, BlockPos pos, RandomSource random) {
        WitherSkeleton boss = EntityType.WITHER_SKELETON.create(level.getLevel());
        if (boss != null) {
            boss.moveTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, random.nextFloat() * 360.0F, 0.0F);

            Objects.requireNonNull(boss.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(200.0);
            boss.setHealth(200.0f);
            Objects.requireNonNull(boss.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(15.0);
            Objects.requireNonNull(boss.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(1.5);


            boss.setPersistenceRequired();
            boss.addTag("netherite_boss");

            level.addFreshEntity(boss);


            LOGGER.info("Босс заспавнен на позиции: {}", pos);
        }
    }
}
