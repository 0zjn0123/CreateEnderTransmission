package net.forsteri.createendertransmission.blocks.energyTransmitter;

import com.mojang.datafixers.util.Pair;
import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import net.forsteri.createendertransmission.transmitUtil.ITransmitter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;


public class EnergyTransmitterTileEntity extends KineticTileEntity implements ITransmitter {
    public EnergyTransmitterTileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
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
        var checks = new ArrayList<>(getConnectedTransmitters());
        for (KineticTileEntity tile : checks) {
            if (tile.getBlockPos() != this.getBlockPos() && !neighbours.contains(tile.getBlockPos())) {
                neighbours.add(tile.getBlockPos());
            }
        }
        return super.addPropagationLocations(block, state, neighbours);
    }

    public List<KineticTileEntity> getConnectedTransmitters(){
        for (Pair<String, List<KineticTileEntity>> pair : EnergyNetwork.ENERGY.channels
                .get(this.getTileData().getInt("channel"))){
            if(pair.getFirst().equals(this.getTileData().getString("password"))) {
                if(!pair.getSecond().contains(this)) pair.getSecond().add(this);
                return pair.getSecond();
            }
            pair.getSecond().removeIf(BlockEntity::isRemoved);
            if(pair.getSecond().isEmpty()){
                //noinspection SuspiciousMethodCalls
                EnergyNetwork.ENERGY.channels.remove(pair);
            }
        }
        Pair<String, List<KineticTileEntity>> pair = new Pair<>(this.getTileData().getString("password"), new ArrayList<>(List.of(this)));
        EnergyNetwork.ENERGY.channels
                .get(this.getTileData().getInt("channel")).add(pair);
        return pair.getSecond();
    }

    @Override
    public void reloadSettings(){
        getConnectedTransmitters().remove(this);
        if (level != null && !level.isClientSide) {
            if (hasNetwork())
                getOrCreateNetwork().remove(this);
            detachKinetics();
            for (KineticTileEntity relatedTileEntity : getConnectedTransmitters()) {
                if (relatedTileEntity.hasNetwork())
                    relatedTileEntity.getOrCreateNetwork().remove(relatedTileEntity);
            }
        }

        oldConnectedTransmitters = getConnectedTransmitters();
    }

    public List<KineticTileEntity> oldConnectedTransmitters;

    @Override
    public void afterReload(){
        for (KineticTileEntity relatedTileEntity : oldConnectedTransmitters) {
            relatedTileEntity.detachKinetics();
            relatedTileEntity.attachKinetics();
        }
    }

    @Override
    public float propagateRotationTo(KineticTileEntity target, BlockState stateFrom, BlockState stateTo, BlockPos diff, boolean connectedViaAxes, boolean connectedViaCogs) {
        return (target.getTileData().getInt("channel") == this.getTileData().getInt("channel")
                && target.getTileData().getString("password").equals(this.getTileData().getString("password"))) ? 1f : 0f;
    }

}
