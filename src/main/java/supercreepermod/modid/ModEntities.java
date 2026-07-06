package supercreepermod.modid;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import supercreepermod.modid.entity.SuperCreeperEntity;

public class ModEntities {

    public static final ResourceKey<EntityType<?>> SUPER_CREEPER_KEY = ResourceKey.create(
        Registries.ENTITY_TYPE,
        SuperCreeperMod.id("super_creeper")
    );

    public static final EntityType<SuperCreeperEntity> SUPER_CREEPER = EntityType.Builder
        .of(SuperCreeperEntity::new, MobCategory.MONSTER)
        .fireImmune()
        .sized(0.6F, 1.7F)
        .eyeHeight(1.02F)
        .clientTrackingRange(8)
        .build(SUPER_CREEPER_KEY);

    public static void register() {
        Registry.register(BuiltInRegistries.ENTITY_TYPE, SUPER_CREEPER_KEY, SUPER_CREEPER);
        FabricDefaultAttributeRegistry.register(SUPER_CREEPER, SuperCreeperEntity.createAttributes());
    }
}
