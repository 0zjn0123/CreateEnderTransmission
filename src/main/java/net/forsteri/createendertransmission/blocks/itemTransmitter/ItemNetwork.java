package net.forsteri.createendertransmission.blocks.itemTransmitter;

import com.mojang.datafixers.util.Pair;
import net.minecraftforge.items.ItemStackHandler;

import java.util.ArrayList;
import java.util.List;

// Just if you are confused by the name, it's just some "networks" for the transmitter
public enum ItemNetwork {
    ITEM,
//    FLUID,
//    ITEM
    ;

    public final List<List<Pair<String, ItemStackHandler>>> channels;

    ItemNetwork(){
        this.channels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            channels.add(new ArrayList<>());
//            for (int j = 0; j < yDim; j++) {
//                channels.get(i).add(
//                        new ItemStackHandler(2)
//                );
//            }
        }
    }

}
