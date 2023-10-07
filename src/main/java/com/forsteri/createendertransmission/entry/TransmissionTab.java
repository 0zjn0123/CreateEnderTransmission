package com.forsteri.createendertransmission.entry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class TransmissionTab {

    private static final DeferredRegister<CreativeModeTab> REGISTER
            = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateEnderTransmission.MOD_ID);
    public static final CreativeModeTab TAB = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TransmissionBlocks.CHUNK_LOADER_BLOCK.get()))
            .title(Component.translatable("itemGroup.ender_transmission"))
            .build();

            new CreativeModeTab(ItemGroupUtil.expandArrayAndGetId(), "ender_transmission") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(TransmissionBlocks.CHUNK_LOADER_BLOCK.get());
        }
    };
}
