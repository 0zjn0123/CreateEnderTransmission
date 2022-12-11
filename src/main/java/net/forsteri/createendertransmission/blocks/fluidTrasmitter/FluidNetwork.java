package net.forsteri.createendertransmission.blocks.fluidTrasmitter;

import com.mojang.datafixers.util.Pair;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.util.ArrayList;
import java.util.List;

public enum FluidNetwork {
    FLUID;

    public final List<List<Pair<String, IFluidHandler>>> channels;

    FluidNetwork(){
        this.channels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            channels.add(new ArrayList<>());
//            for (int j = 0; j < yDim; j++) {
//                channels.get(i).add(
//                        new SmartFluidTank(1000, (FluidStack contents)->{})
//                );
//            }
        }
    }
}
