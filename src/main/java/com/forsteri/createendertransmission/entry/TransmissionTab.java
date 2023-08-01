package com.forsteri.createendertransmission.entry;

import com.forsteri.createendertransmission.CreateEnderTransmission;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TransmissionTab {
    private static final DeferredRegister<CreativeModeTab> REGISTER
            = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateEnderTransmission.MOD_ID);
    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }

    public static final RegistryObject<CreativeModeTab> TAB =
            REGISTER.register("ender_transmission",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.ender_transmission"))
                            .withTabsBefore(ResourceLocation.of("create:base", ':'))
                            .icon(() -> new ItemStack(TransmissionBlocks.CHUNK_LOADER_BLOCK.get()))
                            .build());
}
