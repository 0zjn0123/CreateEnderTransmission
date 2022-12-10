package net.forsteri.createendertransmission.transmitUtil;

public interface ITransmitter {
    default void reloadSettings(){}

    default void afterReload(){}
}
