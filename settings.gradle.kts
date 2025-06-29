pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Maron OS"
include(":app")
include(":core:data")
include(":core:domain")
include(":core:presentation")
include(":features:setup")
include(":features:auth")
include(":features:settings")
include(":features:browser")
include(":features:notes")
include(":features:camera")
include(":features:gallery")
include(":features:calendar")
include(":features:bot")
include(":features:quri")
