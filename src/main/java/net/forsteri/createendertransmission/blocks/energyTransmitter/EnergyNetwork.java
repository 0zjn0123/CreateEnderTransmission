package net.forsteri.createendertransmission.blocks.energyTransmitter;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;

import java.util.ArrayList;
import java.util.List;

// Just if you are confused by the name, it's just some "networks" for the transmitter
public enum EnergyNetwork {
    ENERGY(10, 10),
//    FLUID,
//    ITEM
    ;

    public final List<List<List<KineticTileEntity>>> channels;

    EnergyNetwork(int xDim, int yDim){
        this.channels = new ArrayList<>();
        for (int i = 0; i < xDim; i++) {
            channels.add(new ArrayList<>());
            for (int j = 0; j < yDim; j++) {
                channels.get(i).add(new ArrayList<>());
            }
        }
    }

    public static void register(){}
}
