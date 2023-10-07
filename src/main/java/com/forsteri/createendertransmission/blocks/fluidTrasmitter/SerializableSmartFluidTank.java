package com.forsteri.createendertransmission.blocks.fluidTrasmitter;

import com.simibubi.create.foundation.fluid.SmartFluidTank;
import io.github.fabricators_of_create.porting_lib.core.util.INBTSerializable;
import io.github.fabricators_of_create.porting_lib.util.FluidStack;
import net.minecraft.nbt.CompoundTag;

import java.util.function.Consumer;

public class SerializableSmartFluidTank extends SmartFluidTank implements INBTSerializable<CompoundTag> {
    public SerializableSmartFluidTank(int capacity, Consumer<FluidStack> updateCallback) {
        super(capacity, updateCallback);
    }

    @Override
    public CompoundTag serializeNBT() {
        return writeToNBT(new CompoundTag());
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);
        setFluid(fluid);
    }
}
