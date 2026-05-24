@file:Suppress("UnusedPrivateMember")

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
}
kotlin {
    androidLibrary {}
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.materialIconsExtended)
                implementation(libs.composeext.shimmer)
                implementation(libs.decompose.compose)
                implementation(libs.decompose.core)
                implementation(libs.jetbrains.compose.foundation)
                implementation(libs.jetbrains.compose.material)
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.preview)
                implementation(libs.jetbrains.compose.runtime)
                implementation(libs.jetbrains.compose.tooling)
                implementation(libs.jetbrains.compose.ui)
                implementation(libs.moko.resources.compose)
                implementation(libs.moko.resources.core)
                implementation(libs.placeholder)
                implementation(projects.modules.services.core.common)
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.core.ui.theme)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.coil-kt:coil-compose:2.7.0")
            }
        }
    }
}
