package com.forsteri.createendertransmission.entry;

import com.forsteri.createendertransmission.CreateEnderTransmission;
import net.minecraft.network.chat.TranslatableComponent;

import static com.forsteri.createendertransmission.CreateEnderTransmission.registrate;

public class TransmissionLang {
    public static final TranslatableComponent LOADER_LOADED = registrate().addRawLang(CreateEnderTransmission.MOD_ID + ".chunk_loader.loaded", "Chunk loader loaded %1$s chunks nearby");

    static {
        registrate().addRawLang("itemGroup.ender_transmission", "Create Ender Transmission");
        registrate().addRawLang("create.gui.transmitter.channel_title", "Network");

        for (int i = 1; i <= 10; i++) {
            registrate().addRawLang("transmitter.network." + i, String.valueOf(i));
        }
    }
    public static void register() {
    }
}
