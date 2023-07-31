package net.forsteri.createendertransmission.transmitUtil;

import net.minecraftforge.common.extensions.IForgeBlockEntity;

public interface ITransmitter extends IForgeBlockEntity {
    default void reloadSettings(){}

    default void afterReload(){}

    default int getChannel(){
        return getPersistentData().getInt("channel");
    }

    default String getPassword(){
        return getPersistentData().getString("password");
    }
}
