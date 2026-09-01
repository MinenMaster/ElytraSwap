package com.dominikmeister.elytraswap;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public final class ElytraSwapItems {

    public static Item getReplacementItem() {
        String configuredId = Config.REPLACEMENT_ITEM.get();

        ResourceLocation id = ResourceLocation.tryParse(configuredId);
        if (id == null) {
            ElytraSwap.LOGGER.warn("[ElytraSwap] '{}' is not a valid item id, falling back to minecraft:diamond_block.", configuredId);
            return Items.DIAMOND_BLOCK;
        }

        return BuiltInRegistries.ITEM.getOptional(id).orElseGet(() -> {
            ElytraSwap.LOGGER.warn("[ElytraSwap] Item '{}' could not be found (is the mod that adds it installed?), falling back to minecraft:diamond_block.", id);
            return Items.DIAMOND_BLOCK;
        });
    }
}