# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
./gradlew build          # compile + package → build/libs/super-creeper-mod-1.0.0.jar
./gradlew compileJava    # compile server-side only
./gradlew compileClientJava  # compile client-side only
./gradlew genSources     # decompile MC sources into .gradle/loom-cache/minecraftMaven/.../minecraft-common-...-sources.jar
```

No test suite. No lint task.

## Stack

- **Minecraft 26.x** — uses **Mojang official mappings** (not Yarn). Class names: `Creeper`, `Blaze`, `Monster`, etc. in `net.minecraft.world.entity.monster.*`
- **Fabric Loader 0.19.3** + **Fabric API 0.154.0+26.2** + **Fabric Loom 1.17**
- **Java 25**, target/source compatibility set to VERSION_25
- Split source sets: `src/main` (server+common) and `src/client` (client-only)

## Key API Differences vs older MC

- `Entity.hurt()` returns **`void`** (not `boolean`)
- `Identifier` is at `net.minecraft.resources.Identifier` (not `net.minecraft.util.Identifier`)
- `EntityType.Builder.build()` requires a `ResourceKey<EntityType<?>>` argument
- Entity registration: `ResourceKey.create(Registries.ENTITY_TYPE, id)` → `Registry.register(BuiltInRegistries.ENTITY_TYPE, key, type)`
- Attribute registration: `FabricDefaultAttributeRegistry.register(entityType, attributes)`
- Renderer registration (client): `EntityRendererRegistry.register(entityType, RendererClass::new)`

## Architecture

### Entity (server-side — `src/main`)

- `ModEntities.java` — declares and registers `SUPER_CREEPER` EntityType; called from `SuperCreeperMod.onInitialize()`
- `entity/SuperCreeperEntity.java` — extends `Creeper`; sets `explosionRadius = 150` via mixin accessor; spawns `SOUL_FIRE_FLAME` particles client-side in `aiStep()`; overrides `readAdditionalSaveData` to re-apply radius after load

### Mixins (`src/main/java/.../mixin/`)

- `CreeperAccessorMixin` — interface `@Mixin(Creeper.class)` with `@Accessor` to expose private `explosionRadius` field; used by `SuperCreeperEntity` to set 50× explosion power
- `SnowballMixin` — `@Redirect` on `Entity.hurt()` inside `Snowball.onHitEntity()` to deal 3 dmg to `SuperCreeperEntity` (vanilla deals 0 to non-Blaze)
- Mixin configs: `super-creeper-mod.mixins.json` (main), `super-creeper-mod.client.mixins.json` (client)

### Renderer (client-side — `src/client`)

- `client/render/SuperCreeperRenderer.java` — extends `MobRenderer<SuperCreeperEntity, CreeperRenderState, CreeperModel>`; reuses vanilla `CreeperModel` and `CreeperPowerLayer`; serves custom texture
- Texture: `src/main/resources/assets/super-creeper-mod/textures/entity/super_creeper.png` — 64×32 px; Forest Green `#228B22` base, bloody right eye `(180,0,0)`, grey body marks

### Mod ID

`"super-creeper-mod"` — entity summon command: `/summon super-creeper-mod:super_creeper`

## Decompiled Sources

After `./gradlew genSources`, MC source is at:
```
.gradle/loom-cache/minecraftMaven/net/minecraft/minecraft-common-<hash>/26.2/minecraft-common-<hash>-26.2-sources.jar
```
Always check these before writing entity/rendering code — MC 26.x API differs significantly from 1.21.x.
