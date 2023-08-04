package com.forsteri.createendertransmission.blocks;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.forsteri.createendertransmission.CreateEnderTransmission;
import com.forsteri.createendertransmission.transmitUtil.ITransmitter;
import io.github.fabricators_of_create.porting_lib.util.INBTSerializable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public abstract class AbstractMatterTransmitterBlockEntity extends KineticBlockEntity implements ITransmitter {
    public AbstractMatterTransmitterBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    protected abstract MatterTransmitterNetwork getNetwork();

    public INBTSerializable<CompoundTag> getInv(){
        Map<String, INBTSerializable<CompoundTag>> channel = getNetwork().channels.get(getChannel());

        if (channel.containsKey(getPassword()))
            return channel.get(getPassword());

        INBTSerializable<CompoundTag> inv = getNetwork().defaultInv.get();
        channel.put(getPassword(), inv);

        CreateEnderTransmission.savedData.setDirty();

        return inv;
    }

    @Override
    public void reloadSettings() {
        ITransmitter.super.reloadSettings();
    }
}
