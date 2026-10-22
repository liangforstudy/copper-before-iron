// Shared by every node (versions/<minecraft>-<loader>/). No code is compiled: the jar is the shared
// resources, the loot table format for this Minecraft version, and one loader's metadata file.
plugins {
    java
}

val loader = sc.current.project.substringAfterLast('-')
val minecraft = sc.current.version
sc.properties.tags(minecraft)

val modVersion = property("mod.version") as String
version = "$modVersion+$minecraft"
base.archivesName = "${property("mod.archive")}-$loader"

// 26.3 rewrote loot table JSON (single "condition", "modifier" instead of "functions") and renamed
// the recipe_unlocked trigger's "recipe" to "recipes", so those files come in one copy per format.
val dataFormat = if (sc.current.parsed >= "26.3") "26.3" else "1.21.9"
sourceSets.main {
    resources.srcDir(rootProject.file("src/data-format/$dataFormat"))
}

val mcMin: String = sc.properties["mc.min"]
val mcBelow: String = sc.properties["mc.below"]
val metadataProps = mapOf(
    "id" to property("mod.id") as String,
    "name" to property("mod.name") as String,
    "version" to modVersion,
    "fabric_minecraft" to ">=$mcMin <$mcBelow",
    "neoforge_minecraft" to "[$mcMin,$mcBelow)",
)

tasks {
    processResources {
        val props = metadataProps
        inputs.properties(props)

        filesMatching(listOf("fabric.mod.json", "META-INF/neoforge.mods.toml")) { expand(props) }
        when (loader) {
            "fabric" -> exclude("META-INF/neoforge.mods.toml")
            "neoforge" -> exclude("fabric.mod.json")
            else -> error("Unknown loader '$loader' in node ${sc.current.project}")
        }
    }

    jar {
        from(rootProject.file("LICENSE"))
    }

    register<Copy>("buildAndCollect") {
        group = "build"
        description = "Builds the mod jar and copies it to build/libs/{mod version}/"
        from(jar.flatMap { it.archiveFile })
        into(rootProject.layout.buildDirectory.dir("libs/$modVersion"))
    }
}
