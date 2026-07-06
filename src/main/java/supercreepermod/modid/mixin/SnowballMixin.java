package supercreepermod.modid.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.throwableitemprojectile.Snowball;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import supercreepermod.modid.entity.SuperCreeperEntity;

@Mixin(Snowball.class)
public class SnowballMixin {

    @Redirect(
        method = "onHitEntity",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)V"
        )
    )
    private void redirectHurt(Entity entity, DamageSource source, float damage) {
        entity.hurt(source, entity instanceof SuperCreeperEntity ? 3.0F : damage);
    }
}
