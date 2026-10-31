plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
    alias(libs.plugins.klibs.rules)
}

linter {
    unitKind.set("library")
    rootPackage.set("com.makeevrserg.empireprojekt.mobile.wear.messenger.common")
    technologyWords.add("tile")
}

kotlin {
    androidLibrary {}
    applyDefaultHierarchyTemplate()
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlin.coroutines.core)
            implementation(libs.kotlin.serialization.json)
            implementation(projects.modules.services.wearMessenger.api)
        }
        androidMain.dependencies {
            implementation(libs.androidx.compose.wear.foundation)
            implementation(libs.androidx.compose.wear.material)
            implementation(libs.decompose.compose)
            implementation(libs.decompose.core)
            implementation(libs.google.horologist.datalayer)
            implementation(libs.google.horologist.tiles)
            implementation(libs.jetbrains.compose.foundation)
            implementation(libs.jetbrains.compose.material)
            implementation(libs.jetbrains.compose.preview)
            implementation(libs.jetbrains.compose.runtime)
            implementation(libs.jetbrains.compose.ui)
            implementation(libs.klibs.mikro.core)
            implementation(libs.moko.resources.core)
            implementation(libs.wear.tiles)
            implementation(libs.wear.tiles.material)
            implementation(libs.wear.tiles.tooling.preview)
            implementation(libs.wear.tooling.preview)
            implementation(projects.modules.features.theme.api)
            implementation(projects.modules.services.core.resources)
            implementation(projects.modules.services.core.ui.common)
            implementation(projects.modules.services.core.ui.theme)
            implementation(projects.modules.services.wearMessenger.pingWear)
        }
    }
}
