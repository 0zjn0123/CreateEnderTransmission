package net.forsteri.createendertransmission.entry;

import com.simibubi.create.content.contraptions.base.KineticTileEntityRenderer;
import com.simibubi.create.content.contraptions.relays.encased.ShaftInstance;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.forsteri.createendertransmission.CreateEnderTransmission;
import net.forsteri.createendertransmission.blocks.chunkLoader.LoaderInstance;
import net.forsteri.createendertransmission.blocks.chunkLoader.LoaderTileEntity;
import net.forsteri.createendertransmission.blocks.energyTransmitter.EnergyTransmitterTileEntity;
import net.forsteri.createendertransmission.blocks.itemTransmitter.ItemTransmitterTileEntity;

public class TileEntities {

    private static final CreateRegistrate REGISTRATE = CreateEnderTransmission
            .registrate();

    public static final BlockEntityEntry<LoaderTileEntity> CHUNK_LOADER_TILE = REGISTRATE
            .tileEntity("chunk_loader", LoaderTileEntity::new)
            .instance(() -> LoaderInstance::new, false)
            .validBlocks(Blocks.CHUNK_LOADER_BLOCK)
            .renderer(() -> KineticTileEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<EnergyTransmitterTileEntity> ENERGY_TRANSMITTER_TILE = REGISTRATE
            .tileEntity("energy_transmitter", EnergyTransmitterTileEntity::new)
            .instance(() -> ShaftInstance::new, false)
            .validBlocks(Blocks.ENERGY_TRANSMITTER_BLOCK)
            .renderer(() -> KineticTileEntityRenderer::new)
            .register();

    public static final BlockEntityEntry<ItemTransmitterTileEntity> ITEM_TRANSMITTER_TILE_ENTITY = REGISTRATE
            .tileEntity("item_transmitter", ItemTransmitterTileEntity::new)
            .validBlocks(Blocks.ITEM_TRANSMITTER_BLOCK)
            .register();

    public static void register(){}
}
