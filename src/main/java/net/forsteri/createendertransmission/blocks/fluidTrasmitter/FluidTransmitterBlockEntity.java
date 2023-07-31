package net.forsteri.createendertransmission.blocks.fluidTrasmitter;

import net.forsteri.createendertransmission.blocks.AbstractMatterTransmitterBlockEntity;
import net.forsteri.createendertransmission.blocks.MatterTransmitterNetwork;
import net.forsteri.createendertransmission.transmitUtil.ITransmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FluidTransmitterBlockEntity extends AbstractMatterTransmitterBlockEntity implements ITransmitter {
    public FluidTransmitterBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    protected MatterTransmitterNetwork getNetwork() {
        return MatterTransmitterNetwork.FLUID;
    }

    @Override
    protected Function<Supplier<INBTSerializable<CompoundTag>>, ?> getCapability() {
        return FluidTransmitterInventoryHandler::new;
    }

    @Override
    protected Predicate<Capability<?>> getMatterCapPredicate() {
        return this::isFluidHandlerCap;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (isFluidHandlerCap(cap)) return capability.cast();
        return super.getCapability(cap, side);
    }
}
