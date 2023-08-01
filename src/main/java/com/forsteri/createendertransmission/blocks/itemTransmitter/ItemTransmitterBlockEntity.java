package com.forsteri.createendertransmission.blocks.itemTransmitter;

import com.forsteri.createendertransmission.blocks.AbstractMatterTransmitterBlockEntity;
import com.forsteri.createendertransmission.blocks.MatterTransmitterNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class ItemTransmitterBlockEntity extends AbstractMatterTransmitterBlockEntity {

    public ItemTransmitterBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    protected MatterTransmitterNetwork getNetwork() {
        return MatterTransmitterNetwork.ITEM;
    }

    @Override
    protected Function<Supplier<INBTSerializable<CompoundTag>>, ?> getCapability() {
        return ItemTransmitterInventoryHandler::new;
    }

    @Override
    protected Predicate<Capability<?>> getMatterCapPredicate() {
        return this::isItemHandlerCap;
    }
}
