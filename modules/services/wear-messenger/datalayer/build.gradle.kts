plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
    alias(libs.plugins.klibs.rules)
}

linter {
    unitKind.set("technology")
    rootPackage.set("com.makeevrserg.empireprojekt.mobile.wear.messenger.datalayer")
}

kotlin {
    androidLibrary {
        withHostTest {}
    }
    applyDefaultHierarchyTemplate()
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.serialization.json)
            implementation(projects.modules.services.wearMessenger.api)
        }
        androidMain.dependencies {
            implementation(libs.google.horologist.datalayer)
            implementation(libs.kotlin.coroutines.core)
            implementation(libs.kotlin.coroutines.playServices)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
        }
    }
}
