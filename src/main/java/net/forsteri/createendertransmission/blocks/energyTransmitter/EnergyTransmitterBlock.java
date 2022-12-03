package net.forsteri.createendertransmission.blocks.energyTransmitter;

import com.simibubi.create.AllItems;
import com.simibubi.create.content.contraptions.base.DirectionalKineticBlock;
import com.simibubi.create.content.contraptions.base.KineticBlock;
import com.simibubi.create.foundation.block.ITE;
import com.simibubi.create.foundation.gui.ScreenOpener;
import net.forsteri.createendertransmission.blocks.TransmitterScreen;
import net.forsteri.createendertransmission.entry.Blocks;
import net.forsteri.createendertransmission.entry.TileEntities;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.DistExecutor;

public class EnergyTransmitterBlock extends DirectionalKineticBlock implements ITE<EnergyTransmitterTileEntity> {
    public EnergyTransmitterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<EnergyTransmitterTileEntity> getTileEntityClass() {
        return EnergyTransmitterTileEntity.class;
    }

    @Override
    public BlockEntityType<? extends EnergyTransmitterTileEntity> getTileEntityType() {
        return TileEntities.ENERGY_TRANSMITTER_TILE.get();
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return state.getValue(FACING)
                .getAxis();
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() == state.getValue(FACING).getAxis();
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn,
                                 BlockHitResult hit) {
        ItemStack held = player.getMainHandItem();
        if (AllItems.WRENCH.isIn(held))
            return InteractionResult.PASS;
        if (held.getItem() instanceof BlockItem) {
            BlockItem blockItem = (BlockItem) held.getItem();
            if (blockItem.getBlock() instanceof KineticBlock && hasShaftTowards(worldIn, pos, state, hit.getDirection()))
                return InteractionResult.PASS;
        }

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT,
                () -> () -> withTileEntityDo(worldIn, pos, te -> this.displayScreen(te, player)));
        return InteractionResult.SUCCESS;
    }

    @OnlyIn(value = Dist.CLIENT)
    protected void displayScreen(EnergyTransmitterTileEntity te, Player player) {
        if (player instanceof LocalPlayer)
            ScreenOpener.open(new TransmitterScreen(te, Blocks.ENERGY_TRANSMITTER_BLOCK.asStack()));
    }
}
