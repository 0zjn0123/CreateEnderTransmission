package com.forsteri.createendertransmission.blocks.chunkLoader;

import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.foundation.utility.Components;
import com.simibubi.create.foundation.utility.Lang;
import com.simibubi.create.foundation.utility.LangBuilder;
import com.forsteri.createendertransmission.CreateEnderTransmission;
import com.forsteri.createendertransmission.entry.TransmissionLang;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;


public class LoaderBlockEntity extends KineticBlockEntity {
    public LoaderBlockEntity(BlockEntityType<?> typeIn, BlockPos pos, BlockState state) {
        super(typeIn, pos, state);
    }

    @Override
    public void tick() {
        super.tick();
        assert level != null;
        if (level.isClientSide) {
            return;
        }

        ServerLevel serverLevel = (ServerLevel) this.level;

        if (!isSpeedRequirementFulfilled())
            return;

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                serverLevel.setChunkForced(
                        new ChunkPos(getBlockPos()).x + i,
                        new ChunkPos(getBlockPos()).z + j,
                        Math.abs(this.getSpeed()) >= 16 * 8 * Math.max(Math.abs(i), Math.abs(j))
                );
            }
        }
    }

    @Override
    public boolean addToGoggleTooltip(List<Component> tooltip, boolean isPlayerSneaking) {
        boolean superAdded = super.addToGoggleTooltip(tooltip, isPlayerSneaking);

        if (!superAdded) return false;

        if (!isSpeedRequirementFulfilled()) return true;

        int radius = (int) Math.abs(getSpeed()) / 128 * 2 + 1;

        Lang.builder().space().addTo(tooltip);

        new LangBuilder(CreateEnderTransmission.MOD_ID).add(Components.translatable(TransmissionLang.LOADER_LOADED.getKey(),
                radius * radius
                )).style(ChatFormatting.GREEN).forGoggles(tooltip);

        return true;
    }
}
