package net.forsteri.createendertransmission.blocks.energyTransmitter;

import com.simibubi.create.content.contraptions.base.IRotate;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import net.forsteri.createendertransmission.transmitUtil.Networks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;


public class EnergyTransmitterTileEntity extends KineticTileEntity {
    public EnergyTransmitterTileEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public void tick() {
        super.tick();
        if (!getConnectedTransmitters().contains(this)) getConnectedTransmitters().add(this);
    }

    @Override
    public boolean isCustomConnection(KineticTileEntity other, BlockState state, BlockState otherState) {
        if(other instanceof EnergyTransmitterTileEntity){
            return other.getTileData().getInt("channel") == this.getTileData().getInt("channel") &&
                    other.getTileData().getInt("password") == this.getTileData().getInt("password");
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
        return Networks.ENERGY.channels
                .get(this.getTileData().getInt("channel"))
                .get(this.getTileData().getInt("network"));
    }
}
