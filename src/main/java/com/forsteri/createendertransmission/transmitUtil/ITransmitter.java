package com.forsteri.createendertransmission.transmitUtil;


import io.github.fabricators_of_create.porting_lib.extensions.extensions.BlockEntityExtensions;

public interface ITransmitter extends BlockEntityExtensions {
    default void reloadSettings(){}

    default void afterReload(){}

    default int getChannel(){
        return getCustomData().getInt("channel");
    }

    default String getPassword(){
        return getCustomData().getString("password");
    }
}
