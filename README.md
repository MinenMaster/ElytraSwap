![ElytraSwap Logo](https://github.com/MinenMaster/ElytraSwap/blob/main/src/main/resources/assets/elytraswap/elytraswap.png)

# ElytraSwap

[![Available on Modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/mod/endship-elytraswap)
[![View on GuitHub](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy-minimal/available/github_vector.svg)](https://github.com/MinenMaster/ElytraSwap)

A mod that lets you replace the Elytra found in End Ship item frames with any other item... vanilla or modded.

Similar to mods/datapacks like "No Elytra" or "Remove Elytra", but better ;)

## Features

- Replaces the Elytra generated in End Ship item frames with a configurable item
- Works in both singleplayer and on servers. When used on a server, no client-side mod is required for players to join

## Dependencies

- NeoForge 1.21.248

## Configuration

On first launch, ElytraSwap generates `config/elytraswap-common.toml`:

```toml
# The item to place in the End Ship item frame instead of an Elytra (default: 'minecraft:diamond_block')
replacementItem = "minecraft:diamond_block"
```

Change `replacementItem` to any valid item id, then restart the server (or use `/reload` where applicable) for the change to take effect on newly generated End Ships.

> **Note:** existing, already-generated End Ships won't retroactively change. This only affects ships generated after the config is set.

---

## Updating crafting recipes

Changing `replacementItem` swaps what appears in the item frame, but it does **not** automatically update crafting recipes in other mods that require an Elytra as an ingredient. Since every mod defines its recipes differently, ElytraSwap doesn't try to rewrite them automatically - instead, you can override any specific recipe yourself with a small datapack.

Datapack overrides work because Minecraft resolves resources by their full path (`namespace:path`): if a datapack defines a file at the exact same location as one already provided by a mod, the datapack's version wins. This lets you replace just the recipe you care about without touching anything else.

### Example: replacing an Elytra ingredient in another mod's recipe

Suppose another mod (`somemod`) has a recipe at `data/somemod/recipe/some_recipe.json` that requires `minecraft:elytra`. Create a datapack like this in your world's `datapacks` folder:

```
world/datapacks/elytraswap_compat/
├── pack.mcmeta
└── data/
    └── somemod/
        └── recipe/
            └── some_recipe.json
```

**`pack.mcmeta`:**

```json
{
  "pack": {
    "pack_format": 48,
    "description": "ElytraSwap recipe compatibility overrides"
  }
}
```

**`some_recipe.json`** — copy the original recipe file from the mod's jar, then simply replace the `minecraft:elytra` entry with your configured replacement item, e.g.:

```json
{
  "item": "minecraft:diamond_block"
}
```

Enable the datapack with `/reload` (on an existing world) or by starting a new world — check it's active with `/datapack list`.
