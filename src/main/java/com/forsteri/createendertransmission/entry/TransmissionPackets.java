package com.forsteri.createendertransmission.entry;

import com.forsteri.createendertransmission.CreateEnderTransmission;
import com.forsteri.createendertransmission.transmitUtil.ConfigureTransmitterPacket;
import com.simibubi.create.foundation.networking.SimplePacketBase;
import me.pepperbell.simplenetworking.SimpleChannel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Function;

public enum TransmissionPackets {
    CONFIGURE_TRANSMITTER(ConfigureTransmitterPacket.class, ConfigureTransmitterPacket::new, SimplePacketBase.NetworkDirection.PLAY_TO_SERVER);
    public static final ResourceLocation CHANNEL_NAME = new ResourceLocation(CreateEnderTransmission.MOD_ID, "main");
    public static SimpleChannel channel;
    private final PacketType<?> packet;

    <T extends SimplePacketBase> TransmissionPackets(Class<T> type, Function<FriendlyByteBuf, T> factory,
                                                     SimplePacketBase.NetworkDirection direction) {
        packet = new PacketType<>(type, factory, direction);
    }

    public static void registerPackets() {
        channel = new SimpleChannel(CHANNEL_NAME);
        for (TransmissionPackets packet : values())
            packet.packet.register();
    }

    private static class PacketType<T extends SimplePacketBase> {
        private static int index = 0;

        private final Function<FriendlyByteBuf, T> decoder;
        private final Class<T> type;
        private final SimplePacketBase.NetworkDirection direction;

        private PacketType(Class<T> type, Function<FriendlyByteBuf, T> factory, SimplePacketBase.NetworkDirection direction) {
            decoder = factory;
            this.type = type;
            this.direction = direction;
        }

        private void register() {
            switch (direction) {
                case PLAY_TO_CLIENT -> channel.registerS2CPacket(type, index++, decoder);
                case PLAY_TO_SERVER -> channel.registerC2SPacket(type, index++, decoder);
            }
        }
    }
}
