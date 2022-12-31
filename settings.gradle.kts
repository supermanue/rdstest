rootProject.name = "rdstest"

pluginManagement {
    val newRelicGradleVersion: String by settings
    val kotlinVersion: String by settings
    resolutionStrategy {
        eachPlugin {
            if (requested.id.toString().startsWith("us.datanerd") && requested.version == null) {
                useVersion(newRelicGradleVersion)
            }

            if (requested.id.toString().startsWith("org.jetbrains.kotlin.") && requested.version == null) {
                useVersion(kotlinVersion)
            }
        }
    }
}