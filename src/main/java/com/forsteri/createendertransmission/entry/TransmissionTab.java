package com.forsteri.createendertransmission.entry;

import com.forsteri.createendertransmission.CreateEnderTransmission;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TransmissionTab {
    public static final CreativeModeTab TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TransmissionBlocks.CHUNK_LOADER_BLOCK.get()))
            .title(Component.translatable("itemGroup.ender_transmission"))
            .build();

    public static final ResourceKey<CreativeModeTab> TAB_KEY;

    static {
        ResourceLocation id = new ResourceLocation(CreateEnderTransmission.MOD_ID, "ender_transmission");
        TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id);
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB_KEY, TAB);
    }
}
