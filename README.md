# Super Creeper Mod

A Fabric mod for Minecraft 26.2 that adds the **Super Creeper** — a more powerful, fire-immune variant of the vanilla Creeper.

## Features

- **Forest Green** appearance (`#228B22`) with a custom texture
- **Bloody right eye** and **grey marks** across the body
- **Fire immune** — cannot be damaged by fire or lava
- **Weak to snowballs** — takes 3 damage per snowball hit (like a Blaze)
- **Blue soul fire particles** surround it constantly
- **50× explosion power** — explosion radius of 150 vs the vanilla 3

## Requirements

- Minecraft Java Edition 26.2
- [Fabric Loader 0.19.3+](https://fabricmc.net/use/installer/)
- [Fabric API 0.154.0+26.2](https://modrinth.com/mod/fabric-api)

## Installation

1. Install Fabric Loader for MC 26.2
2. Drop both jars into your mods folder:
   - `fabric-api-0.154.0+26.2.jar`
   - `super-creeper-mod-1.0.0.jar`

**Mods folder location (macOS):** `~/Library/Application Support/minecraft/mods/`

## Spawning

In a world with cheats enabled:

```
/summon super-creeper-mod:super_creeper
```

## Building from Source

Requires Java 25.

```bash
./gradlew build
```

Output jar: `build/libs/super-creeper-mod-1.0.0.jar`

## License

CC0-1.0
