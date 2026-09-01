package com.dominikmeister.elytraswap.mixin;

import com.dominikmeister.elytraswap.ElytraSwapItems;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.structure.structures.EndCityPieces;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EndCityPieces.EndCityPiece.class)
public class EndShipElytraMixin {

    @Redirect(
            method = "handleDataMarker",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/decoration/ItemFrame;setItem(Lnet/minecraft/world/item/ItemStack;Z)V"
            )
    )
    private void elytraswap$replaceEndShipElytraItem(ItemFrame itemFrame, ItemStack itemStack, boolean updateNeighbours) {
        itemFrame.setItem(new ItemStack(ElytraSwapItems.getReplacementItem()), updateNeighbours);
    }
}