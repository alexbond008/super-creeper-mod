package supercreepermod.modid.entity;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import supercreepermod.modid.mixin.CreeperAccessorMixin;

public class SuperCreeperEntity extends Creeper {

    private static final int EXPLOSION_RADIUS = 150;

    public SuperCreeperEntity(EntityType<? extends SuperCreeperEntity> type, Level level) {
        super(type, level);
        ((CreeperAccessorMixin) this).setExplosionRadius(EXPLOSION_RADIUS);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes();
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        ((CreeperAccessorMixin) this).setExplosionRadius(EXPLOSION_RADIUS);
    }

    @Override
    public void aiStep() {
        if (this.level().isClientSide()) {
            for (int i = 0; i < 3; i++) {
                this.level().addParticle(
                    ParticleTypes.SOUL_FIRE_FLAME,
                    this.getRandomX(0.5),
                    this.getRandomY(),
                    this.getRandomZ(0.5),
                    0.0, 0.05, 0.0
                );
            }
        }
        super.aiStep();
    }
}
