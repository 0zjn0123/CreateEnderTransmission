package net.forsteri.createendertransmission.blocks.fluidTrasmitter;

import com.mojang.datafixers.util.Pair;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.fluid.SmartFluidTank;
import net.forsteri.createendertransmission.transmitUtil.ITransmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FluidTransmitterTileEntity extends KineticBlockEntity implements ITransmitter {
    public LazyOptional<IFluidHandler> capability;

    public FluidTransmitterTileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
        capability = LazyOptional.of(() -> new FluidTransmitterInventoryHandler(this::getInv));
    }

    public IFluidHandler getInv(){
        for (Pair<String, IFluidHandler> pair : FluidNetwork.FLUID.channels
                .get(this.getTileData().getInt("channel"))){
            if(pair.getFirst().equals(this.getTileData().getString("password"))){
                return pair.getSecond();
            }

        }
        Pair<String, IFluidHandler> pair = new Pair<>(this.getTileData().getString("password"), new SmartFluidTank(1000, (FluidStack contents)->{}));
        FluidNetwork.FLUID.channels
                .get(this.getTileData().getInt("channel")).add(pair);
        return pair.getSecond();
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (isFluidHandlerCap(cap)) return capability.cast();
        return super.getCapability(cap, side);
    }
}
