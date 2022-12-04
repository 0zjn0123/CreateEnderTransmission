package net.forsteri.createendertransmission.transmitUtil;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;

import java.util.ArrayList;
import java.util.List;

// Just if you are confused by the name, it's just some "networks" for the transmitter
public enum Networks {
    ENERGY(10, 10),
//    FLUID,
//    ITEM
    ;

    private final int xDim;
    private final int yDim;

    public final List<List<List<KineticTileEntity>>> channels;

    Networks(int xDim, int yDim){
        this.xDim = xDim;
        this.yDim = yDim;
        this.channels = new ArrayList<>();
        for (int i = 0; i < xDim; i++) {
            channels.add(new ArrayList<>());
            for (int j = 0; j < yDim; j++) {
                channels.get(i).add(new ArrayList<>());
            }
        }
    }

    public static void register(){}

    public int getxDim() {
        return xDim;
    }

    public int getyDim() {
        return yDim;
    }
}
