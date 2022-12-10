package net.forsteri.createendertransmission.blocks.energyTransmitter;

import com.mojang.datafixers.util.Pair;
import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import net.forsteri.createendertransmission.transmitUtil.ITransmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;


public class EnergyTransmitterTileEntity extends KineticTileEntity implements ITransmitter {
    public EnergyTransmitterTileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public void tick() {
        super.tick();
        if (!getConnectedTransmitters().contains(this)) getConnectedTransmitters().add(this);
        var connectedTransmitters = new ArrayList<>(getConnectedTransmitters());
        for (KineticTileEntity entity: connectedTransmitters){
            if(!(getTileData().getInt("channel") == entity.getTileData().getInt("channel") && getTileData().getString("password").equals(entity.getTileData().getString("password"))))
            {
                detachKinetics();
                setSpeed(0);
                source = null;
                setNetwork(worldPosition.asLong());
                attachKinetics();
            }
        }
    }

    @Override
    public boolean isCustomConnection(KineticTileEntity other, BlockState state, BlockState otherState) {
        if(other instanceof EnergyTransmitterTileEntity){
            return other.getTileData().getInt("channel") == this.getTileData().getInt("channel") &&
                    other.getTileData().getString("password").equals(this.getTileData().getString("password"));
        }
        return false;
    }

    @Override
    public List<BlockPos> addPropagationLocations(IRotate block, BlockState state, List<BlockPos> neighbours) {
        for (KineticTileEntity tile : getConnectedTransmitters()) {
            if (tile != this && !neighbours.contains(tile.getBlockPos())) {
                neighbours.add(tile.getBlockPos());
            }
        }
        if (!canPropagateDiagonally(block, state))
            return neighbours;

        Direction.Axis axis = block.getRotationAxis(state);
        BlockPos.betweenClosedStream(new BlockPos(-1, -1, -1), new BlockPos(1, 1, 1))
                .forEach(offset -> {
                    if (axis.choose(offset.getX(), offset.getY(), offset.getZ()) != 0)
                        return;
                    if (offset.distSqr(BlockPos.ZERO) != 2)
                        return;
                    neighbours.add(worldPosition.offset(offset));
                });
        return neighbours;
    }

    public List<KineticTileEntity> getConnectedTransmitters(){
        for (Pair<String, List<KineticTileEntity>> pair : EnergyNetwork.ENERGY.channels
                .get(this.getTileData().getInt("channel"))){
            if(pair.getFirst().equals(this.getTileData().getString("password"))){
                return pair.getSecond();
            }

        }
        Pair<String, List<KineticTileEntity>> pair = new Pair<>(this.getTileData().getString("password"), new ArrayList<>(List.of(this)));
        EnergyNetwork.ENERGY.channels
                .get(this.getTileData().getInt("channel")).add(pair);
        return pair.getSecond();
    }

    @Override
    public void reloadSettings(){
        for (KineticTileEntity relatedTileEntity : getConnectedTransmitters()) {
            relatedTileEntity.detachKinetics();
            relatedTileEntity.setSpeed(0);
            relatedTileEntity.source = null;
            relatedTileEntity.setNetwork(relatedTileEntity.getBlockPos().asLong());
            relatedTileEntity.attachKinetics();
        }
        getConnectedTransmitters().remove(this);
        sendData();
    }

    @Override
    public void afterReload() {
        for (KineticTileEntity relatedTileEntity : getConnectedTransmitters()) {
            relatedTileEntity.detachKinetics();
            relatedTileEntity.setSpeed(0);
            relatedTileEntity.source = null;
            relatedTileEntity.setNetwork(relatedTileEntity.getBlockPos().asLong());
            relatedTileEntity.attachKinetics();
        }
        detachKinetics();
        setSpeed(0);
        source = null;
        setNetwork(worldPosition.asLong());
        attachKinetics();
    }
}
