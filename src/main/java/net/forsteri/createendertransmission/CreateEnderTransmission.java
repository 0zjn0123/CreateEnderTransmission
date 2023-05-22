package net.forsteri.createendertransmission;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.forsteri.createendertransmission.entry.*;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CreateEnderTransmission.MOD_ID)
public class CreateEnderTransmission {

    // Directly reference a slf4j logger

    public static final String MOD_ID = "createendertransmission";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CreateEnderTransmission() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        Blocks.register();
        TileEntities.register();
        Packets.registerPackets();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "createendertransmission-server.toml");
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(RecipeSerializer.class, this::registerRecipeSerializers);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // Some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        event.enqueueWork(Packets::registerPackets);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // Register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }

    public static CreateRegistrate registrate() {
        return REGISTRATE;
    }

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(CreateEnderTransmission.MOD_ID);

    public void registerRecipeSerializers(RegistryEvent.Register<RecipeSerializer<?>> event)
    {
        CraftingHelper.register(ChunkLoaderRecipeCondition.Serializer.INSTANCE);
    }
}
