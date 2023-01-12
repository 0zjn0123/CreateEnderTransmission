package net.forsteri.createendertransmission.mixin;

import com.simibubi.create.content.contraptions.RotationPropagator;
import com.simibubi.create.content.contraptions.base.KineticTileEntity;
import net.forsteri.createendertransmission.blocks.energyTransmitter.EnergyTransmitterTileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RotationPropagator.class, remap = false)
public class KineticTransmissionMixin {
    @Inject(at = @At("TAIL"), method = "getRotationSpeedModifier", remap = false, cancellable = true)
    private static void getRotationSpeedModifier(KineticTileEntity from, KineticTileEntity _to, CallbackInfoReturnable<Float> cir) {
        if(from instanceof EnergyTransmitterTileEntity
                && _to instanceof EnergyTransmitterTileEntity
                && from != _to
                && from.getPersistentData().getInt("channel") == _to.getPersistentData().getInt("channel")
                && from.getPersistentData().getString("password").equals(_to.getPersistentData().getString("password"))
        ) {
            cir.setReturnValue(1f);
        }
    }
}
