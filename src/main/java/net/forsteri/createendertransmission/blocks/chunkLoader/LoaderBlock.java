package net.forsteri.createendertransmission.blocks.chunkLoader;

import com.simibubi.create.content.contraptions.base.KineticBlock;
import com.simibubi.create.foundation.block.ITE;
import net.forsteri.createendertransmission.CreateEnderTransmission;
import net.forsteri.createendertransmission.entry.TileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.world.ForgeChunkManager;

public class LoaderBlock extends KineticBlock implements ITE<LoaderTileEntity> {
    public LoaderBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return Direction.Axis.Y;
    }

    @Override
    public Class<LoaderTileEntity> getTileEntityClass() {
        return LoaderTileEntity.class;
    }

    @Override
    public BlockEntityType<? extends LoaderTileEntity> getTileEntityType() {
        return TileEntities.CHUNK_LOADER_TILE.get();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == Direction.DOWN;
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving){
        ForgeChunkManager.forceChunk(
                (ServerLevel) worldIn,
                CreateEnderTransmission.MOD_ID,
                pos,
                new ChunkPos(pos).x,
                new ChunkPos(pos).z,
                false,
                true
        );
        super.onRemove(state, worldIn, pos, newState, isMoving);
    }
}
