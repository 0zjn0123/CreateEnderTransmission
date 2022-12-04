package net.forsteri.createendertransmission.mixin;

import com.simibubi.create.content.contraptions.RotationPropagator;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import net.forsteri.createendertransmission.blocks.energyTransmitter.EnergyTransmitterTileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RotationPropagator.class)
public class KineticTransmissionMixin {
    @Inject(at = @At("HEAD"), method = "getRotationSpeedModifier(Lcom/simibubi/create/content/contraptions/base/KineticTileEntity;Lcom/simibubi/create/content/contraptions/base/KineticTileEntity;)F", cancellable = true, remap = false)
    private static void getRotationSpeedModifier(KineticTileEntity from, KineticTileEntity _to, CallbackInfoReturnable<Float> cir) {
        if(from instanceof EnergyTransmitterTileEntity && _to instanceof EnergyTransmitterTileEntity && from != _to) {
            cir.setReturnValue(1f);
            cir.cancel();
        }
    }
}
