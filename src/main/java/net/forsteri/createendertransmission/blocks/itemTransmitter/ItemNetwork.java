package net.forsteri.createendertransmission.blocks.itemTransmitter;

import com.mojang.datafixers.util.Pair;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import java.util.ArrayList;
import java.util.List;

// Just if you are confused by the name, it's just some "networks" for the transmitter
public enum ItemNetwork {
    ITEM(10, 10),
//    FLUID,
//    ITEM
    ;

    public final List<List<Pair<ItemStackHandler, LazyOptional<IItemHandler>>>> channels;

    ItemNetwork(int xDim, int yDim){
        this.channels = new ArrayList<>();
        for (int i = 0; i < xDim; i++) {
            channels.add(new ArrayList<>());
            for (int j = 0; j < yDim; j++) {
                ItemStackHandler inv = new ItemStackHandler(2);
                LazyOptional<IItemHandler> invCap = LazyOptional.of(() -> new CombinedInvWrapper(inv));
                channels.get(i).add(
                        new Pair<>(
                                inv,
                                invCap
                        )
                );
            }
        }
    }

}
