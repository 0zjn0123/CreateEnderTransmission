package com.forsteri.createendertransmission.transmitUtil;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.networking.BlockEntityConfigurationPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class ConfigureTransmitterPacket extends BlockEntityConfigurationPacket<KineticBlockEntity> {

    private int channel;
    private String password;

    public ConfigureTransmitterPacket(BlockPos pos, int channel, String password) {
        super(pos);
        this.channel = channel;
        this.password = password;
    }

    public ConfigureTransmitterPacket(FriendlyByteBuf buffer) {
        super(buffer);
    }

    @Override
    protected void writeSettings(FriendlyByteBuf buffer) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("channel", channel);
        tag.putString("password", password);
        buffer.writeNbt(tag);
    }

    @Override
    protected void readSettings(FriendlyByteBuf buffer) {
        CompoundTag tag = buffer.readNbt();
        if (tag != null) {
            channel = tag.getInt("channel");
            password = tag.getString("password");
        }

    }

    @Override
    protected void applySettings(KineticBlockEntity tileEntity) {
        if (!(tileEntity instanceof ITransmitter transmitter)) return;
        if(
                transmitter.getChannel() != channel ||
                        !transmitter.getPassword().equals(password)
        ) {
            ((ITransmitter) tileEntity).reloadSettings();
            tileEntity.getExtraCustomData().putInt("channel", channel);
            tileEntity.getExtraCustomData().putString("password", password);
            tileEntity.detachKinetics();
            tileEntity.attachKinetics();
            ((ITransmitter) tileEntity).afterReload();
        }
    }

}
