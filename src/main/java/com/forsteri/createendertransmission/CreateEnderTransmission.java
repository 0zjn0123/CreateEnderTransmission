package com.forsteri.createendertransmission;

import com.forsteri.createendertransmission.blocks.MatterWorldSavedData;
import com.forsteri.createendertransmission.entry.*;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CreateEnderTransmission.MOD_ID)
public class CreateEnderTransmission {

    public static final String MOD_ID = "createendertransmission";

    public CreateEnderTransmission() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        REGISTRATE.registerEventListeners(FMLJavaModLoadingContext.get()
                .getModEventBus());

        TransmissionBlocks.register();
        TransmissionBlockEntities.register();
        TransmissionPackets.registerPackets();
        TransmissionLang.register();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, TransmissionConfig.SPEC, "createendertransmission-server.toml");
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerRecipeSerializers);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Some preinit code
        event.enqueueWork(TransmissionPackets::registerPackets);
    }

    public static MatterWorldSavedData savedData = null;

    @Mod.EventBusSubscriber
    public static class CommonEvents {
        @SubscribeEvent
        public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            Player player = event.getEntity();
            if (player instanceof ServerPlayer serverPlayer) {
                MinecraftServer server = serverPlayer.getServer();
                if (server == null)
                    return;
                CreateEnderTransmission.savedData = MatterWorldSavedData.load(server);
            }
        }

        @SubscribeEvent
        public static void onLoadWorld(LevelEvent.Load event) {
            LevelAccessor level = event.getLevel();
            MinecraftServer server = event.getLevel().getServer();
            if (server == null || server.overworld() != level)
                return;
            CreateEnderTransmission.savedData = MatterWorldSavedData.load(server);
        }
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CreateEnderTransmission.MOD_ID);

    public void registerRecipeSerializers(RegisterEvent event)
    {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.RECIPE_SERIALIZERS))
        {
            CraftingHelper.register(ChunkLoaderRecipeCondition.Serializer.INSTANCE);
        }
    }
}
