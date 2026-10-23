# Copper Before Iron

In vanilla Minecraft most players skip copper and go from stone tools to iron within a few minutes.
This mod puts copper back between stone and iron, so your first iron takes some real work.

## What changes

| | Vanilla | With this mod |
|---|---|---|
| Mining iron ore | Stone pickaxe or better | **Copper pickaxe** or better. A stone pickaxe breaks it but drops nothing. Also applies to Block of Raw Iron. |
| Smelting raw iron or iron ore | Furnace or blast furnace | **Blast furnace only** |
| Smelting iron tools and armor into nuggets | Furnace or blast furnace | **Blast furnace only** |
| Blast furnace recipe | 5 iron ingots, furnace, 3 smooth stone | **5 Bricks (blocks), furnace, 3 Blocks of Copper** |
| Breaking a blast furnace with a pickaxe | Drops the blast furnace | **Drops 2 charcoal** (like a campfire). Silk Touch still drops the blast furnace. |

Before you can smelt your first iron you need 20 bricks (so you need clay) and 27 copper ingots.
Taking a blast furnace from a village doesn't help, because breaking it only gives charcoal.
You can still find iron ingots as loot or in chests, and you can still smelt in a village's blast furnace.

The recipe-book unlock for the blast furnace now triggers when you pick up Bricks or a Block of Copper.

## Installation

Download the jar for your loader and Minecraft version from the
[Releases](https://github.com/liangforstudy/copper-before-iron/releases) page and put it in your
`mods` folder. For multiplayer, install it on the server.

| Loader | Minecraft | Requires |
|---|---|---|
| Fabric (and Quilt) | 1.21.9 – 1.21.11, 26.1 – 26.1.x, 26.2, 26.3 | [Fabric API](https://modrinth.com/mod/fabric-api) |
| NeoForge | 1.21.9 – 1.21.11, 26.1 – 26.1.x, 26.2, 26.3 | — |

Versions before 1.21.9 aren't supported because they don't have copper tools.

## Compatibility

The mod is data only. It overrides these vanilla files and adds no blocks, items or code:

- Block tag `minecraft:incorrect_for_stone_tool` (adds iron ore, deepslate iron ore and raw iron block)
- Recipe `minecraft:blast_furnace` and its recipe-book advancement
- Recipes `minecraft:iron_ingot_from_smelting_raw_iron`, `…_iron_ore`, `…_deepslate_iron_ore` and
  `minecraft:iron_nugget_from_smelting`, plus their advancements (removed)
- Loot table `minecraft:blocks/blast_furnace`

Another mod or data pack that changes the same files may conflict with this one. NeoForge ships its
own blast furnace recipe, so the NeoForge build is set to load after NeoForge so this recipe wins.


## made as a request for:
https://www.reddit.com/r/MinecraftMod/s/pZN7nMCbX0

(I got 5USD in donations)

## Building

The project uses [Stonecutter](https://stonecutter.kikugie.dev/) to build one jar per Minecraft
release line and loader from the same source. Nothing is compiled, so no Minecraft download is needed.

```
./gradlew buildAndCollect
```

The jars are written to `build/libs/<mod version>/`.

- Shared data: `src/main/resources/`
- Files whose JSON format changed in 26.3 (the blast furnace loot table and recipe-book
  advancement): `src/data-format/1.21.9/` (1.21.9 – 26.2) and `src/data-format/26.3/` (26.3+)
- Supported version ranges: `stonecutter.properties.toml`
- Build matrix: `settings.gradle.kts`

To add a Minecraft version, add it to the list in `settings.gradle.kts`, add its range to
`stonecutter.properties.toml`, and check that the vanilla files above haven't changed format.

## License and credit

[MIT](LICENSE). You're free to use, modify and redistribute this mod, including in modpacks, as long
as you keep the copyright and license notice. Please credit **Copper Before Iron by liangforstudy**
and link back to this repository.
