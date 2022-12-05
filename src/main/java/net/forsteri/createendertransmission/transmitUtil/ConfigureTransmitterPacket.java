package net.forsteri.createendertransmission.transmitUtil;

import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import com.simibubi.create.foundation.networking.TileEntityConfigurationPacket;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

public class ConfigureTransmitterPacket extends TileEntityConfigurationPacket<KineticTileEntity> {

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
    protected void applySettings(KineticTileEntity tileEntity) {
        if(
                tileEntity.getTileData().getInt("channel") != channel ||
                tileEntity.getTileData().getInt("password") != password
        ) {
            for (KineticTileEntity relatedTileEntity : Networks.ENERGY.channels.get(
                    tileEntity.getTileData().getInt("channel")
            ).get(
                    tileEntity.getTileData().getInt("password")
            )) {
                relatedTileEntity.detachKinetics();
                relatedTileEntity.attachKinetics();
            }
            tileEntity.getTileData().putInt("channel", channel);
            tileEntity.getTileData().putInt("password", password);
            tileEntity.detachKinetics();
            tileEntity.attachKinetics();
        }
    }

}
