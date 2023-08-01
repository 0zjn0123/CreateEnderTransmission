package com.forsteri.createendertransmission.blocks.energyTransmitter;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Just if you are confused by the name, it's just some "networks" for the transmitter
public enum EnergyNetwork {
    ENERGY;

    public final List<Map<String, List<KineticBlockEntity>>> channels;

    EnergyNetwork(){
        this.channels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            channels.add(new HashMap<>());
        }
    }
}
