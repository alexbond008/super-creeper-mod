package supercreepermod.modid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import supercreepermod.modid.ModEntities;
import supercreepermod.modid.client.render.SuperCreeperRenderer;

public class SuperCreeperModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.SUPER_CREEPER, SuperCreeperRenderer::new);
    }
}
