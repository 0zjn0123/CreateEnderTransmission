package com.forsteri.createendertransmission.transmitUtil;

import io.github.fabricators_of_create.porting_lib.extensions.BlockEntityExtensions;

public interface ITransmitter extends BlockEntityExtensions {
    default void reloadSettings(){}

    default void afterReload(){}

    default int getChannel(){
        return getExtraCustomData().getInt("channel");
    }

    default String getPassword(){
        return getExtraCustomData().getString("password");
    }
}
