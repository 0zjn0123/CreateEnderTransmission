package com.forsteri.createendertransmission.entry;

import net.minecraftforge.common.ForgeConfigSpec;

public class TransmissionConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.BooleanValue CHUNK_LOADER;

    static {
        BUILDER.push("Create Ender Transmission Config");
        CHUNK_LOADER = BUILDER.comment("Enable the chunk loader").define("chunkLoader", true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
