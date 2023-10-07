package com.forsteri.createendertransmission;

import com.forsteri.createendertransmission.blocks.MatterWorldSavedData;
import com.forsteri.createendertransmission.entry.*;
import com.simibubi.create.foundation.data.CreateRegistrate;
import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.LevelAccessor;

import java.util.concurrent.Executor;

// The value here should match an entry in the META-INF/mods.toml file
public class CreateEnderTransmission implements ModInitializer, DataGeneratorEntrypoint, ClientModInitializer {

    public static final String MOD_ID = "createendertransmission";

    public static MatterWorldSavedData savedData = null;

    @Override
    public void onInitialize() {
        TransmissionBlocks.register();
        TransmissionBlockEntities.register();
        TransmissionPackets.registerPackets();
        TransmissionLang.register();

        REGISTRATE.register();

        ServerPlayConnectionEvents.JOIN.register(CreateEnderTransmission::playerLoggedIn);
        ServerWorldEvents.LOAD.register(CreateEnderTransmission::onLoadWorld);

        TransmissionPackets.channel.initServerListener();
        ResourceConditions.register(new ResourceLocation(MOD_ID, "allow_chunk_loader"), jsonObject -> TransmissionConfig.CHUNK_LOADER.get());
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        ExistingFileHelper helper = ExistingFileHelper.withResourcesFromArg();
        REGISTRATE.setupDatagen(generator.createPack(), helper);
    }

    public static void playerLoggedIn(ServerGamePacketListenerImpl handler, PacketSender sender, MinecraftServer server) {
        CreateEnderTransmission.savedData = MatterWorldSavedData.load(server);
    }

    public static void onLoadWorld(Executor executor, LevelAccessor level) {
        MinecraftServer server = level.getServer();
        if (server == null || server.overworld() != level)
            return;
        CreateEnderTransmission.savedData = MatterWorldSavedData.load(server);
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CreateEnderTransmission.MOD_ID);

    @Override
    public void onInitializeClient() {
        TransmissionPackets.channel.initClientListener();
    }
}
