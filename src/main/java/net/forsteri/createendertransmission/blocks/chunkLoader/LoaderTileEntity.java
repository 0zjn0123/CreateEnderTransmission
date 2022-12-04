package net.forsteri.createendertransmission.blocks.chunkLoader;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import net.forsteri.createendertransmission.CreateEnderTransmission;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.world.ForgeChunkManager;


public class LoaderTileEntity extends KineticTileEntity {
    public LoaderTileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public void tick() {
        super.tick();
        assert level != null;
        if (level.isClientSide) {
            return;
        }
        ForgeChunkManager.forceChunk(
                (ServerLevel) level,
                CreateEnderTransmission.MOD_ID,
                getBlockPos(),
                new ChunkPos(getBlockPos()).x,
                new ChunkPos(getBlockPos()).z,
                this.getSpeed() != 0,
                true
        );
    }

}
