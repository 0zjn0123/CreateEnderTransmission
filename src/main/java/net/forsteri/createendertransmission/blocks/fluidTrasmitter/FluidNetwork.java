package net.forsteri.createendertransmission.blocks.fluidTrasmitter;

import com.simibubi.create.foundation.fluid.SmartFluidTank;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;
import java.util.List;

public enum FluidNetwork {
    FLUID(10, 10),;

    public final List<List<IFluidHandler>> channels;

    FluidNetwork(int xDim, int yDim){
        this.channels = new ArrayList<>();
        for (int i = 0; i < xDim; i++) {
            channels.add(new ArrayList<>());
            for (int j = 0; j < yDim; j++) {
                channels.get(i).add(
                        new SmartFluidTank(1000, (FluidStack contents)->{})
                );
            }
        }
    }
}
