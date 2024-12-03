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
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Anshim"
include(
    ":app",

    ":core:common",
    ":core:designsystem",

    ":data:datasource",
    ":data:datastore",
    ":data:remote",
    ":data:repository",
    ":data:room",

    ":domain",

    ":feature:home",
    ":feature:addbook"
)
