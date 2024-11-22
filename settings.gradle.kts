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

rootProject.name = "Anshim"
include(":app")
include(":feature:home")
include(":feature:addbook")
include(":core:common")
include(":core:designsystem")
include(":data:datasource")
include(":data:datastore")
include(":data:remote")
include(":data:repository")
include(":data:room")
