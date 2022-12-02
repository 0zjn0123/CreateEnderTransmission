package net.forsteri.createendertransmission.entry;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.forsteri.createendertransmission.CreateEnderTransmission;
import net.forsteri.createendertransmission.blocks.chunkLoader.LoaderInstance;
import net.forsteri.createendertransmission.blocks.chunkLoader.LoaderRenderer;
import net.forsteri.createendertransmission.blocks.chunkLoader.LoaderTileEntity;

public class TileEntities {

    private static final CreateRegistrate REGISTRATE = CreateEnderTransmission
            .registrate();

    public static final BlockEntityEntry<LoaderTileEntity> CHUNK_LOADER_TILE = REGISTRATE
            .tileEntity("chunk_loader", LoaderTileEntity::new)
            .instance(() -> LoaderInstance::new, false)
            .validBlocks(net.forsteri.createendertransmission.entry.Blocks.CHUNK_LOADER_BLOCK)
            .renderer(() -> LoaderRenderer::new)
            .register();
    public static void register(){}
}
