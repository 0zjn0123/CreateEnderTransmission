package net.forsteri.createendertransmission.blocks.itemTransmitter;

import com.mojang.datafixers.util.Pair;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import net.forsteri.createendertransmission.transmitUtil.ITransmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

public class ItemTransmitterTileEntity extends KineticTileEntity implements ITransmitter {

    public LazyOptional<IItemHandler> capability;
    public ItemTransmitterTileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
        capability = LazyOptional.of(() -> new ItemTransmitterInventoryHandler(this::getInv));
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
        if (isItemHandlerCap(cap)) return capability.cast();
        return super.getCapability(cap, side);
    }

    public ItemStackHandler getInv(){
        for (Pair<String, ItemStackHandler> pair : ItemNetwork.ITEM.channels
                .get(this.getTileData().getInt("channel"))){
            if(pair.getFirst().equals(this.getTileData().getString("password"))){
                return pair.getSecond();
            }

        }
        Pair<String, ItemStackHandler> pair = new Pair<>(this.getTileData().getString("password"), new ItemStackHandler(2));
        ItemNetwork.ITEM.channels
                .get(this.getTileData().getInt("channel")).add(pair);
        return pair.getSecond();
    }

//    @Override
//    public void setRemoved() {
//        super.setRemoved();
//        capability.invalidate();
//    }

    @Override
    public void reloadSettings() {
        ITransmitter.super.reloadSettings();

    }
}
