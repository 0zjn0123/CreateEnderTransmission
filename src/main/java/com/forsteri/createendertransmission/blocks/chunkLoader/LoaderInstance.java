package com.forsteri.createendertransmission.blocks.chunkLoader;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;
import net.minecraft.core.Direction;

public class LoaderInstance extends SingleRotatingInstance<LoaderBlockEntity> {
    public LoaderInstance(MaterialManager modelManager, LoaderBlockEntity tileEntity) {
        super(modelManager, tileEntity);
    }

    @Override
    protected Instancer<RotatingData> getModel() {
        return getRotatingMaterial().getModel(AllPartialModels.SHAFT_HALF, blockState, Direction.DOWN);
    }
}
