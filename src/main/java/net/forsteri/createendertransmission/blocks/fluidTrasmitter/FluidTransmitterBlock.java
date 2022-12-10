package net.forsteri.createendertransmission.blocks.fluidTrasmitter;

import com.simibubi.create.foundation.block.ITE;
import com.simibubi.create.foundation.gui.ScreenOpener;
import net.forsteri.createendertransmission.entry.Blocks;
import net.forsteri.createendertransmission.entry.TileEntities;
import net.forsteri.createendertransmission.transmitUtil.TransmitterScreen;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class FluidTransmitterBlock extends Block implements ITE<FluidTransmitterTileEntity> {
    public FluidTransmitterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<FluidTransmitterTileEntity> getTileEntityClass() {
        return FluidTransmitterTileEntity.class;
    }

    @Override
    public BlockEntityType<? extends FluidTransmitterTileEntity> getTileEntityType() {
        return TileEntities.FLUID_TRANSMITTER_TILE_ENTITY.get();
    }

    @SuppressWarnings("deprecation")
    @Override
    public @NotNull InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn,
                                          BlockHitResult hit) {
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> withTileEntityDo(worldIn, pos, te -> this.displayScreen(te, player)));
        return InteractionResult.SUCCESS;
    }

    @OnlyIn(value = Dist.CLIENT)
    protected void displayScreen(FluidTransmitterTileEntity te, Player player) {
        if (player instanceof LocalPlayer)
            ScreenOpener.open(new TransmitterScreen(te, Blocks.FLUID_TRANSMITTER_BLOCK.asStack()));
    }
}
