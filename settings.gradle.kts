pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        repositories {
            maven(url = "https://jitpack.io")
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        repositories {
            maven(url = "https://jitpack.io")
        }
    }
}

rootProject.name = "MOBILE TELEHEALTH SYSTEM"
include(":app")
