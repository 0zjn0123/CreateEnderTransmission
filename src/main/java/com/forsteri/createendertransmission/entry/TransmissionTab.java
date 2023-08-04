package com.forsteri.createendertransmission.entry;

import io.github.fabricators_of_create.porting_lib.util.ItemGroupUtil;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class TransmissionTab {
    public static final CreativeModeTab TAB = new CreativeModeTab(ItemGroupUtil.expandArrayAndGetId(), "ender_transmission") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(TransmissionBlocks.CHUNK_LOADER_BLOCK.get());
        }
    };
}
