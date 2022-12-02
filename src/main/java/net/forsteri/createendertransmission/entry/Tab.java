package net.forsteri.createendertransmission.entry;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Tab {
    public static final CreativeModeTab TAB = new CreativeModeTab("ender_transmission") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(net.forsteri.createendertransmission.entry.Blocks.CHUNK_LOADER_BLOCK.get());
        }
    };
}
