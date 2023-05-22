package net.forsteri.createendertransmission.blocks.energyTransmitter;

import com.mojang.datafixers.util.Pair;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import java.util.ArrayList;
import java.util.List;

// Just if you are confused by the name, it's just some "networks" for the transmitter
public enum EnergyNetwork {
    ENERGY;

    public final List<List<Pair<String, List<KineticBlockEntity>>>> channels;

    EnergyNetwork(){
        this.channels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            channels.add(new ArrayList<>());
        }
    }
}
