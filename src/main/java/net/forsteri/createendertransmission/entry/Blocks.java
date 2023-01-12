package net.forsteri.createendertransmission.entry;

import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.forsteri.createendertransmission.CreateEnderTransmission;
import net.forsteri.createendertransmission.blocks.chunkLoader.LoaderBlock;
import net.forsteri.createendertransmission.blocks.energyTransmitter.EnergyTransmitterBlock;
import net.forsteri.createendertransmission.blocks.fluidTrasmitter.FluidTransmitterBlock;
import net.forsteri.createendertransmission.blocks.itemTransmitter.ItemTransmitterBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

public class Blocks {

    private static final CreateRegistrate REGISTRATE = CreateEnderTransmission
            .registrate()
            .creativeModeTab(
                    () -> Tab.TAB);

    public static final BlockEntry<LoaderBlock> CHUNK_LOADER_BLOCK =
            REGISTRATE.block("chunk_loader", LoaderBlock::new)
                    .initialProperties(() -> net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK)
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .properties(p -> p.strength(10.0F, 1200.0F))
                    .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .transform(pickaxeOnly())
            //        .blockstate(BlockStateGen.axisBlockProvider(true))
                    .item()
                    .transform(customItemModel())
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(BlockStressDefaults.setImpact(8))
                    .register();

    public static final BlockEntry<EnergyTransmitterBlock> ENERGY_TRANSMITTER_BLOCK =
            REGISTRATE.block("energy_transmitter", EnergyTransmitterBlock::new)
                    .initialProperties(() -> net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK)
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .properties(p -> p.strength(10.0F, 1200.0F))
                    .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .transform(pickaxeOnly())
                    .blockstate(BlockStateGen.directionalBlockProvider(true))
                    .item()
                    .transform(customItemModel())
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(BlockStressDefaults.setImpact(8))
                    .register();

    public static final BlockEntry<ItemTransmitterBlock> ITEM_TRANSMITTER_BLOCK =
            REGISTRATE.block("item_transmitter", ItemTransmitterBlock::new)
                    .initialProperties(() -> net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK)
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .properties(p -> p.strength(10.0F, 1200.0F))
                    .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .transform(pickaxeOnly())
                    .item()
                    .transform(customItemModel())
                    .addLayer(() -> RenderType::cutoutMipped)
                    .register();

    public static final BlockEntry<FluidTransmitterBlock> FLUID_TRANSMITTER_BLOCK =
            REGISTRATE.block("fluid_transmitter", FluidTransmitterBlock::new)
                    .initialProperties(() -> net.minecraft.world.level.block.Blocks.DIAMOND_BLOCK)
                    .properties(BlockBehaviour.Properties::noOcclusion)
                    .properties(p -> p.strength(10.0F, 1200.0F))
                    .properties(BlockBehaviour.Properties::requiresCorrectToolForDrops)
                    .transform(pickaxeOnly())
                    .item()
                    .transform(customItemModel())
                    .addLayer(() -> RenderType::cutoutMipped)
                    .register();

    public static void register(){}
}
