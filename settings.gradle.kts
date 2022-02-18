pluginManagement {
    repositories {
        mavenCentral()
        maven(url = "https://maven.quiltmc.org/repository/release/") {
            name = "Quilt"
        }
        maven(url = "https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        maven(url = "https://server.bbkr.space/artifactory/libs-release/") {
            name = "Cotton"
        }
        gradlePluginPortal()
    }
}
