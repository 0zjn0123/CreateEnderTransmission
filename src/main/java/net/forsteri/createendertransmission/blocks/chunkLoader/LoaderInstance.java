package net.forsteri.createendertransmission.blocks.chunkLoader;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.AllBlockPartials;
import com.simibubi.create.content.contraptions.base.SingleRotatingInstance;
import com.simibubi.create.content.contraptions.base.flwdata.RotatingData;
import net.minecraft.core.Direction;

public class LoaderInstance extends SingleRotatingInstance {
    public LoaderInstance(MaterialManager modelManager, LoaderTileEntity tileEntity) {
        super(modelManager, tileEntity);
    }

    @Override
    protected Instancer<RotatingData> getModel() {
        return getRotatingMaterial().getModel(AllBlockPartials.SHAFT_HALF, blockState, Direction.DOWN);
    }
}
