package net.forsteri.createendertransmission.blocks.fluidTrasmitter;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import net.forsteri.createendertransmission.transmitUtil.ITransmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FluidTransmitterTileEntity extends KineticTileEntity implements ITransmitter {
    public LazyOptional<IFluidHandler> capability;

    public FluidTransmitterTileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
        capability = LazyOptional.of(() -> new FluidTransmitterInventoryHandler(this::getInv));
    }

    public IFluidHandler getInv(){
        return FluidNetwork.FLUID.channels
                .get(this.getTileData().getInt("channel"))
                .get(this.getTileData().getInt("password"));
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (isFluidHandlerCap(cap)) return capability.cast();
        return super.getCapability(cap, side);
    }
}
