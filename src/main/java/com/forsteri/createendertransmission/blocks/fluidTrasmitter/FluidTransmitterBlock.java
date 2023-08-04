package com.forsteri.createendertransmission.blocks.fluidTrasmitter;

import com.forsteri.createendertransmission.transmitUtil.TransmitterScreen;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.gui.ScreenOpener;
import com.forsteri.createendertransmission.entry.TransmissionBlocks;
import com.forsteri.createendertransmission.entry.TransmissionBlockEntities;
import com.tterrag.registrate.fabric.EnvExecutor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class FluidTransmitterBlock extends Block implements IBE<FluidTransmitterBlockEntity>, IWrenchable {

    public FluidTransmitterBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn,
                                          BlockHitResult hit) {
        EnvExecutor.runWhenOn(EnvType.CLIENT,
                () -> () -> withBlockEntityDo(worldIn, pos, te -> this.displayScreen(te, player)));
        return InteractionResult.SUCCESS;
    }

    @Environment(value = EnvType.CLIENT)
    protected void displayScreen(FluidTransmitterBlockEntity te, Player player) {
        if (player instanceof LocalPlayer)
            ScreenOpener.open(new TransmitterScreen<>(te, TransmissionBlocks.FLUID_TRANSMITTER_BLOCK.asStack()));
    }

    @Override
    public Class<FluidTransmitterBlockEntity> getBlockEntityClass() {
        return FluidTransmitterBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends FluidTransmitterBlockEntity> getBlockEntityType() {
        return TransmissionBlockEntities.FLUID_TRANSMITTER_TILE_ENTITY.get();
    }
}
