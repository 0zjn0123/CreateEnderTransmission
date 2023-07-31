package net.forsteri.createendertransmission.blocks.itemTransmitter;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class ItemTransmitterInventoryHandler extends ItemStackHandler {
    protected final Supplier<INBTSerializable<CompoundTag>> superWrapper;

    public ItemTransmitterInventoryHandler(Supplier<INBTSerializable<CompoundTag>> superWrapper) {
        super();
        this.superWrapper = superWrapper;
    }

    public ItemStackHandler superWrapper() {
        return ((ItemStackHandler) superWrapper.get());
    }

    @Override
    public @NotNull ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return superWrapper().insertItem(slot, stack, simulate);
    }

    @Override
    public @NotNull ItemStack extractItem(int slot, int amount, boolean simulate) {
        return superWrapper().extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlots() {
        return superWrapper().getSlots();
    }

    @Override
    @Nonnull
    public ItemStack getStackInSlot(int slot)
    {
        return superWrapper().getStackInSlot(slot);
    }

    @Override
    public void setStackInSlot(int slot, @NotNull ItemStack stack) {
        superWrapper().setStackInSlot(slot, stack);
    }

    @Override
    public void setSize(int size) {
        superWrapper().setSize(size);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return superWrapper().isItemValid(slot, stack);
    }

    @Override
    public CompoundTag serializeNBT()
    {
        return superWrapper().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt)
    {
        superWrapper().deserializeNBT(nbt);
    }

    @Override
    protected void validateSlotIndex(int slot) {
        if (slot < 0 || slot >= superWrapper().getSlots())
            throw new RuntimeException("Slot " + slot + " not in valid range - [0," + stacks.size() + ")");
    }
}
