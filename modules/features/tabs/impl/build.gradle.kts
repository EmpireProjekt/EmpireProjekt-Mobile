@file:Suppress("UnusedPrivateMember")

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
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
                implementation(libs.decompose.core)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.mikro.platform)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.mppsettings)
                implementation(projects.modules.features.rating.api)
                implementation(projects.modules.features.root.api)
                implementation(projects.modules.features.status.api)
                implementation(projects.modules.features.theme.api)
                implementation(projects.modules.features.towns.api)
                implementation(projects.modules.services.core.common)
            }
        }
    }
}
