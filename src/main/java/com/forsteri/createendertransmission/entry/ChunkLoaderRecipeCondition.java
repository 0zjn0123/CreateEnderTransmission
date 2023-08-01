package com.forsteri.createendertransmission.entry;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class ChunkLoaderRecipeCondition implements ICondition{

    public static ResourceLocation ID = new ResourceLocation("createendertransmission", "allow_chunk_loader");

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test(IContext context) {
        return TransmissionConfig.CHUNK_LOADER.get();
    }

    public static class Serializer implements IConditionSerializer<ChunkLoaderRecipeCondition>
    {
        public static final ChunkLoaderRecipeCondition.Serializer INSTANCE = new ChunkLoaderRecipeCondition.Serializer();

        @Override
        public void write(JsonObject json, ChunkLoaderRecipeCondition value)
        {
        }

        @Override
        public ChunkLoaderRecipeCondition read(JsonObject json)
        {
            return new ChunkLoaderRecipeCondition();
        }

        @Override
        public ResourceLocation getID()
        {
            return ChunkLoaderRecipeCondition.ID;
        }
    }
}
