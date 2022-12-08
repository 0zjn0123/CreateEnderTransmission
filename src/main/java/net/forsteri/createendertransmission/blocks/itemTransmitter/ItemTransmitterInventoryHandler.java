package net.forsteri.createendertransmission.blocks.itemTransmitter;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.items.wrapper.EmptyHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ItemTransmitterInventoryHandler extends CombinedInvWrapper {
    protected final Supplier<ItemStackHandler> superWrapper;

    public ItemTransmitterInventoryHandler(Supplier<ItemStackHandler> superWrapper) {
        super(superWrapper.get());
        this.superWrapper = superWrapper;
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return superWrapper.get().insertItem(slot, stack, simulate);
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        return superWrapper.get().extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlots() {
        return superWrapper.get().getSlots();
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int slot)
    {
        int index = getIndexForSlot(slot);
        IItemHandlerModifiable handler = getHandlerFromIndex(index);
        slot = getSlotFromIndex(slot, index);
        return handler.getStackInSlot(slot);
    }

    protected IItemHandlerModifiable getHandlerFromIndex(int index)
    {
        if (index != 0)
        {
            return (IItemHandlerModifiable) EmptyHandler.INSTANCE;
        }
        return superWrapper.get();
    }
}
