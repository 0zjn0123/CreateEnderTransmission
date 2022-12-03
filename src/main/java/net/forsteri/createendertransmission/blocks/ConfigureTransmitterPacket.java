package net.forsteri.createendertransmission.blocks;

import com.simibubi.create.foundation.networking.TileEntityConfigurationPacket;
import com.simibubi.create.foundation.tileEntity.SyncedTileEntity;
import net.forsteri.createendertransmission.blocks.energyTransmitter.EnergyTransmitterTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class ConfigureTransmitterPacket extends TileEntityConfigurationPacket<SyncedTileEntity> {

    private int channel;
    private int password;

    public ConfigureTransmitterPacket(BlockPos pos, int channel, int password) {
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
        tag.putInt("password", password);
        buffer.writeNbt(tag);
    }

    @Override
    protected void readSettings(FriendlyByteBuf buffer) {
        CompoundTag tag = buffer.readNbt();
        if (tag != null) {
            channel = tag.getInt("channel");
            password = tag.getInt("password");
        }

    }

    @Override
    protected void applySettings(SyncedTileEntity syncedTileEntity) {
        syncedTileEntity.getTileData().putInt("channel", channel);
        syncedTileEntity.getTileData().putInt("password", password);
    }

    protected void applySettings(EnergyTransmitterTileEntity energyTransmitterTileEntity) {
        energyTransmitterTileEntity.getTileData().putInt("channel", channel);
        energyTransmitterTileEntity.getTileData().putInt("password", password);
    }
}
