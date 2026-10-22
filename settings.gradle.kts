pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.9.8"
}

// The mod is data only (tags, recipes, loot tables, advancements), so nothing is compiled against
// Minecraft or a loader: each node packages the shared resources with its loader's metadata.
// One node per Minecraft release line and loader; the version ranges live in stonecutter.properties.toml.
stonecutter {
    create(rootProject) {
        for (minecraft in listOf("1.21.11", "26.1", "26.2", "26.3")) {
            versions("$minecraft-fabric" to minecraft, "$minecraft-neoforge" to minecraft)
        }
        vcsVersion = "26.3-fabric"
    }
}

rootProject.name = "copper-before-iron"
